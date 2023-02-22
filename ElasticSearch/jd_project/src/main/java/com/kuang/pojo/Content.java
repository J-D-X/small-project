package com.kuang.pojo;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Content {
    private String title;
    private String price;
    private String img;
//    private String commit;
    private String shop;
}
