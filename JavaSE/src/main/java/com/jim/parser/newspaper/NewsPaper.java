package com.jim.parser.newspaper;

import java.util.List;

public class NewsPaper {
	private String version;
	private List<News> newsList;

	public String getVersion() {
		return version;
	}

	public NewsPaper(String version, List<News> newsList) {
		this.version = version;
		this.newsList = newsList;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
}
