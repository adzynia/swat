package com.mini.staticdata;

public enum PageTitle {

	BOOK_HOME("Selenium: Beginners Guide"), //
	EMPTY(""), ;

	private String titleValue;

	private PageTitle(String titleValue) {
		this.titleValue = titleValue;
	}

	public String getTitleValue() {
		return titleValue;
	}

}
