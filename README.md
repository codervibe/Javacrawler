# java爬虫 

* 使用java 爬取 JD 上的图片
* 废话不多说，直接上代码
pom.xml 引包
~~~xml 
   <dependencies>
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.8.3</version>
        </dependency>
        <!-- 文件下载 -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.5</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.5</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.8</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.5</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
  ~~~
  * 新建一个对象类
~~~java 
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
    //注解使用插件注解可以自动生成相应的方法
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  
  public class Content {
    private String img;
    private String price;
    private String title;
  }



~~~
  * 新建一个工具类 
  * 工具类代码
~~~java 
package com.codervibe.untils;
/*
* Created by Administrator on 2021/1/30
* DateTime:2021/01/30 14:55
* Description:
* Others:
  */

import com.codervibe.domain.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

 /**
   * @author Administrator
   */
  public class HtmlParseUntil {
  //这里的Content是一个对象，是扒下来的图片的内容的对象
  public List<Content> parseJD(String keywords) throws Exception {
  //获取请求 https://search.jd.com/Search?keyword=java
  //前提需要联网！！
  String url = "https://search.jd.com/Search?keyword=" + keywords;
  //解析网页 jsoup 返回document 这个document就是浏览器JS的document对象
  //所有在js中的方法在这里都可以使用
  Document document = Jsoup.parse(new URL(url), 60000);
  Element element = document.getElementById("J_goodsList");
  System.out.println(element.html());
  Elements elements = element.getElementsByTag("li");
  ArrayList<Content> goodsList = new ArrayList<>();

       //获取里面的每个元素 这里的el 是刚刚获取到的所有li
       for (Element el : elements) {
           //图片
           String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
           //价格
           String price = el.getElementsByClass("p-price").eq(0).text();
           //书名
           String title = el.getElementsByClass("p-name").eq(0).text();


           Content content = new Content();
           content.setTitle(title);
           content.setImg(img);
           content.setPrice(price);

           goodsList.add(content);

       }
       return goodsList;

  }
  }

~~~
  
* 测试代码使用的是junit测试
* 新建一个测试类 com.codervibe.pareTest
~~~java 
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

~~~
