package maykish.colin.simpleciv.network;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Connection {

	String baseURL = "http://128.153.222.152:9000/";
	
	DefaultHttpClient client;
	
	public Connection(){
		client = new DefaultHttpClient();
	}
	
	public String sendRequest(String command){
		HttpGet get = new HttpGet("http://128.153.222.152:9000/" + command);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		HttpResponse response;
		
		String responseString = null;
		
		try {
			response = client.execute(get);
			response.getEntity().writeTo(out);
			out.close();
			responseString = out.toString();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseString;
	}
	
	// TODO: combine these into one function
	
	public String sendPostRequest(String command, String data){
		HttpPost post = new HttpPost("http://128.153.222.152:9000/" + command);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String responseString = null;
		
		try {
			post.setEntity(new StringEntity(data));
			
			HttpResponse response = client.execute(post);
			response.getEntity().writeTo(out);
			out.close();
			responseString = out.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseString;
	}
}
