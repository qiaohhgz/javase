package com.jim.demo.jsoup;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/17/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsoupSelector {
    public static void main(String[] args) throws IOException {
        Document doc = DocumentFactory.createDocumentHandler(SourceType.FILE).getDocument();

        doc.select("div");//使用标签名来定位，例如 a
        doc.select("#id");//使用元素 id 定位，例如 #logo
        doc.select("ns|tag");//使用命名空间的标签定位，例如 fb:name 来查找 <fb:name> 元素
        doc.select(".class");//使用元素的 class 属性定位，例如 .head
        doc.select("[attribute]");//使用元素的属性进行定位，例如 [href] 表示检索具有 href 属性的所有元素
        doc.select("[^attr]");//使用元素的属性名前缀进行定位，例如 [^data-] 用来查找 HTML5 的 dataset 属性
        doc.select("[attr=value]");//使用属性值进行定位，例如 [width=500] 定位所有 width 属性值为 500 的元素
        doc.select("[attr^=value], [attr$=value], [attr*=value]");//这三个语法分别代表，属性以 value 开头、结尾以及包含
        doc.select("[attr~=regex]");//使用正则表达式进行属性值的过滤，例如 img[src~=(?i)\.(png|jpe?g)]
        doc.select("*");//定位所有元素
    }

}
