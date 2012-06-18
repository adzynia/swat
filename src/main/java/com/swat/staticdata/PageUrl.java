package com.swat.staticdata;

public enum PageUrl  {

	ADMIN_LOGIN("http://localhost/scrum/admin/pageLogin.php"), //
	ADMIN_HOME("http://localhost/scrum/admin/pageHome.php"), //
	;

	private String urlValue;

	private PageUrl(String urlValue) {
		this.urlValue = urlValue;
	}

	public String getUrl() {
		return urlValue;
	}



}
