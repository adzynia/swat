package com.swat.staticdata;

public enum PageUrl  {
	
	//TODO: http://cells.org.ua/scrum-selenium/index.php?signIn=1
	ADMIN_LOGIN("http://localhost/scrum/admin/pageLogin.php"), //
	ADMIN_HOME("http://localhost/scrum/admin/pageHome.php"), 
	ADMIN_ADD_MEMBER("http://localhost/scrum/admin/pageEditMember.php"), //
	ADMIN_EDIT_MEMBER("http://localhost/scrum/admin/pageEditMember.php?memberID=%s"), //
	;

	private String urlValue;

	private PageUrl(String urlValue) {
		this.urlValue = urlValue;
	}

	public String getUrl() {
		return urlValue;
	}



}
