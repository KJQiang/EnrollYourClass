package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpRequest {
	public HttpRequest() {
		super();
	}
	public String doGet(String url,Map<String,String> headers) {
		try {
			HttpClient client = new DefaultHttpClient();
			SSLSkip ssl = new SSLSkip();
			ssl.enableSSL(client);
			HttpGet request = new HttpGet(url);
			for(String item : headers.keySet()) {
				request.setHeader(item, headers.get(item));
			}
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				return result;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String doGet(String url,Map<String,String> headers,boolean getHeaders) {
		try {
			HttpClient client = new DefaultHttpClient();
			SSLSkip ssl = new SSLSkip();
			ssl.enableSSL(client);
			HttpGet request = new HttpGet(url);
			for(String item : headers.keySet()) {
				request.setHeader(item, headers.get(item));
			}
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if(getHeaders) {
					org.apache.http.Header[] resheaders = response.getAllHeaders();
					String res = "";
					for(int i = 0; i < resheaders.length; i++) {
						res = res + "~" + resheaders[i].getName() + ":" + resheaders[i].getValue();
					}
					return res;
				}
				String result = EntityUtils.toString(response.getEntity());
				return result;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String doPost(String url, Map<String,String> headers, Map<String,String> params, boolean getHeader) {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			SSLSkip ssl = new SSLSkip();
			ssl.enableSSL(client);
			HttpPost request = new HttpPost(url);
			
			for(String key : headers.keySet()) {
				request.setHeader(key, headers.get(key));
			}
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for(String key : params.keySet()) {
				nvps.add(new BasicNameValuePair(key,params.get(key)));
			}
			request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if(code == 200) {
				if(getHeader == false) {
					in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8"));
				}else {
					org.apache.http.Header[] resheaders = response.getAllHeaders();
					String res = "";
					for(int i = 0; i < resheaders.length; i++) {
						res = res + "~" + resheaders[i].getName() + ":" + resheaders[i].getValue();
					}
					return res;
				}
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while((line = in.readLine())!= null) {
					sb.append(line+ NL);
				}
				in.close();
				return sb.toString();
			}else {
				System.out.print("访问失败，状态码"+code);
				return null;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}

class SSLSkip {
	 
	public void enableSSL(HttpClient httpclient) {
 
	    // 调用ssl
 
	    try {
 
	        SSLContext sslcontext = SSLContext.getInstance("TLS");
 
            TrustManager tm = new X509TrustManager() {
 
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
 
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
 
				public X509Certificate[] getAcceptedIssuers1() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}  
               
            };
	        
	        sslcontext.init(null, new TrustManager[]{tm}, null);
 
	        @SuppressWarnings("deprecation")
 
	        SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
 
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
 
	        Scheme https = new Scheme("https", sf, 443);
 
	        httpclient.getConnectionManager().getSchemeRegistry()
 
	                .register(https);
 
	    } catch (Exception e) {
 
	        e.printStackTrace();
 
	    }
 
	}
	
}

