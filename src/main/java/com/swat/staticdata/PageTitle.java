package com.swat.staticdata;

public enum PageTitle {
	
	EMPTY(""),//
	ADMIN_LOGIN("Sign In First!"), //
	ADMIN_HOME("Membership Management"), ;

	private String titleValue;

	private PageTitle(String titleValue) {
		this.titleValue = titleValue;
	}

	public String getTitleValue() {
		return titleValue;
	}

}
