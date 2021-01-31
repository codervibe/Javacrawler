package com.codervibe;
/*
 * Created by Administrator on 2021/1/30
 * DateTime:2021/01/30 21:27
 * Description:
 * Others:
 */

import com.codervibe.untils.HtmlParseUntil;
import org.junit.Test;

public class pareTest {
    @Test
    public void test() throws Exception {
        new HtmlParseUntil().parseJD("java").forEach(System.out::println);
    }


}
