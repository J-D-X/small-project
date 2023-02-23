package com.kuang.service;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.kuang.pojo.Content;
import com.kuang.utils.HtmlParseUtil;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//业务编写
@Service
public class ContentService {

    @Autowired
    public  RestHighLevelClient client;

    // 1.解析数据放入es索引中
    public  Boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);
        // 把查询出来的数据放入es索引中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2s");
        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("jd_goods2")
                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkResponse.hasFailures();
    }

    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws Exception {
        if(pageNo <= 1){
            pageNo = 1;
        }

        //构造请求
        SearchRequest searchRequest = new SearchRequest("jd_goods2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 构造分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        // 精确匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        // 解析结果
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            list.add(documentFields.getSourceAsMap());
        }
        if(list.size() == 0) {
            parseContent(keyword);
            searchPage(keyword, pageNo, pageSize);
        }
        return list;
    }
    public List<Map<String,Object>> searchPageSortOfPrice(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo <= 1) {
            pageNo = 1;
        }
        SearchRequest searchRequest = new SearchRequest("jd_goods2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60,TimeUnit.SECONDS));
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.DESC));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit element : searchResponse.getHits().getHits()) {
            list.add(element.getSourceAsMap());
        }
        return list;
    }

}
