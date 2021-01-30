package com.codervibe.domain;
/*
 * Created by Administrator on 2021/1/30
 * DateTime:2021/01/30 21:16
 * Description:
 * Others:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Content {
    private String img;
    private String price;
    private String title;
}
