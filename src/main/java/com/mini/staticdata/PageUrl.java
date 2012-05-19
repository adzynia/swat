package com.mini.staticdata;

public enum PageUrl  {

	BOOK_HOME("http://book.theautomatedtester.co.uk/"), //
	;

	private String urlValue;

	private PageUrl(String urlValue) {
		this.urlValue = urlValue;
	}

	public String getUrl() {
		return urlValue;
	}



}
