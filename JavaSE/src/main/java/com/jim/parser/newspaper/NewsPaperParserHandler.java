package com.jim.parser.newspaper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;

public class NewsPaperParserHandler implements Runnable {
	private static final Logger log = Logger.getLogger(NewsPaperParserHandler.class);
	private static Proxy proxy;
	private String baseUrl = "http://newspaper.jfdaily.com/isdb/html/2013-02/25/index_01.htm";
	private String url;

	public static void main(String[] args) throws Exception {
		NewsPaperParserHandler handler = new NewsPaperParserHandler("http://newspaper.jfdaily.com/isdb/html/2013-02/22/index_01.htm");
		handler.parserAllNode();
	}

	public NewsPaperParserHandler(String baseUrl) {
		this.baseUrl = baseUrl;
		proxy = new Proxy(Type.HTTP, new InetSocketAddress("172.20.230.5", 3128));
	}

	public void parserAllNode() throws MalformedURLException, IOException, ParserException {
		URLConnection connection = new URL(getUrl()).openConnection(proxy);

		Parser p = new Parser(connection);
		CssSelectorNodeFilter imgFilter = new CssSelectorNodeFilter("div[class=list]");
		Node[] nodes = p.extractAllNodesThatMatch(imgFilter).toNodeArray();
		log.info("list.length = " + nodes.length);
		if (nodes.length == 0) {
			log.error("not found div class is list");
		}
		nodes = nodes[0].getChildren().toNodeArray();
		log.info("children.length = " + nodes.length);

		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof HeadingTag) {
				log.info("node is label HeadingTag");
				log.info(node.toPlainTextString());
			}
			if (node instanceof LinkTag) {
				log.info("node is link tag");
				log.info(node.toPlainTextString());
			} else {
				log.info("node type = " + node.getClass().getName());
			}
		}
	}

	@Override
	public void run() {

	}

	public String getUrl() {
		url = baseUrl;
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
