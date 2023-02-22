package com.kuang.utils;


import com.kuang.pojo.Content;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        new HtmlParseUtil().parseJD("vue").forEach(System.out::println);
    }
    public List<Content> parseJD(String keywords) throws Exception {
        //        url:https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        // 解析页面(jsoup返回的document就是js页面对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 所有在JS中能够使用的方法在这里都能使用
        Element element = document.getElementById("J_goodsList");
        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        // 获取元素中的所有信息
        ArrayList<Content> list = new ArrayList<>();
        for (Element el:elements) {

            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String name = el.getElementsByClass("p-name").eq(0).text();
            String shop = el.getElementsByClass("curr-shop hd-shopname").eq(0).text();
            System.out.println("=========================================================");
            System.out.println(img);
            System.out.println(price);
            System.out.println(name);
            System.out.println(shop);

            Content content = new Content(name, price, img,shop);
            list.add(content);
        }
        return list;
    }
}
