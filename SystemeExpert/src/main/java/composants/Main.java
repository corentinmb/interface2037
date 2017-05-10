package composants;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpStatus;

public class Main {

	public static void main(String[] args) {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://localhost:8080/questions/last");
		try {
			HttpResponse response = httpclient.execute(httpget);
			
			//System.out.println(response);
			if(response.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
				System.out.println("STATUS 200");
				String responseString = new BasicResponseHandler().handleResponse(response);
				System.out.println(responseString);
			}else if (response.getStatusLine().getStatusCode() == HttpStatus.NO_CONTENT.value()) {
				System.out.println("Pas de question en attente");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e);
		}
		
	}

}
