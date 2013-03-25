package com.swat;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * @author andrii.dzynia
 */
public class HttpActions {

  private static DefaultHttpClient httpClient;

  public static void removeMember(String name) throws IOException {

    int responseCode = login();
    ensure302Code(responseCode);

    responseCode = deleteUser(name);
    ensure200Code(responseCode);
  }

  private static void ensure200Code(int responseCode) throws HttpResponseException {
    if (responseCode != 200) {
      throw new HttpResponseException(responseCode, format("Unexpected http code [%d], [%d] expected", responseCode, 200));
    }
  }

  private static int deleteUser(String name) throws IOException {
    HttpGet deleteUserRequest = new HttpGet("http://cells.org.ua/scrum-selenium/admin/pageDeleteMember.php?memberID=" + name);
    HttpResponse response1 = getHttpClient().execute(deleteUserRequest);
    return response1.getStatusLine().getStatusCode();
  }

  private static void ensure302Code(int responseCode) throws HttpResponseException {
    if (responseCode != 302) {
      throw new HttpResponseException(responseCode, format("Unexpected http code [%d], [%d] expected", responseCode, 302));
    }
  }

  private static int login() throws IOException {
    HttpPost loginRequest = new HttpPost("http://cells.org.ua/scrum-selenium/admin/pageHome.php");
    List<NameValuePair> credentials = new ArrayList<NameValuePair>();
    credentials.add(new BasicNameValuePair("username", "admin"));
    credentials.add(new BasicNameValuePair("password", "admin"));

    loginRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
    HttpResponse response = getHttpClient().execute(loginRequest);
    return response.getStatusLine().getStatusCode();
  }

  private static DefaultHttpClient getHttpClient() {
    if (httpClient == null) {
      PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
      cm.setMaxTotal(100);
      httpClient = new DefaultHttpClient(cm);
    }
    return httpClient;
  }

  public static void main(String[] args) throws IOException {
    //Test Remove function
    HttpActions.removeMember("tester151762463");
  }


}
