package com.swat.staticdata;

public enum PageUrl  {
	
	ADMIN_LOGIN("http://cells.org.ua/scrum-selenium/admin/pageLogin.php"), //
	ADMIN_HOME("http://cells.org.ua/scrum-selenium/admin/pageHome.php"), 
	ADMIN_ADD_MEMBER("http://cells.org.ua/scrum-selenium/admin/pageEditMember.php"), //
	ADMIN_EDIT_MEMBER("http://cells.org.ua/scrum-selenium/admin/pageEditMember.php?memberID=%s"), // 
	ADMIN_VIEW_MEMBER_PAGE("http://cells.org.ua/scrum-selenium/admin/pageViewMembers.php"), //
	;

	private String urlValue;

	private PageUrl(String urlValue) {
		this.urlValue = urlValue;
	}

	public String getUrl() {
		return urlValue;
	}



}
