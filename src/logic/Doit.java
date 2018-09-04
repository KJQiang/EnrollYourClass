package logic;

import ui.*;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Doit{
	String auth;
	String call;
	String lec;
	String dis;
	String host;
	public Doit(String auth,String call,String lec,String dis,String host) {
		this.auth = auth;
		this.call = call;
		this.lec = lec;
		this.dis = dis;
		this.host = host;
		run();
	}
	public void run() {
		while(true) {
			try { 
				Thread.sleep(400); 
			} catch (InterruptedException e) { 
				e.printStackTrace(); 
			}
			if(host.equals("1")) {
				MyThread myThread = new MyThread(auth,call,lec,dis);
				MyThread1 myThread1 = new MyThread1(auth,call,lec,dis);
				MyThread myThread_ = new MyThread(auth,call,lec,dis);
				MyThread1 myThread1_ = new MyThread1(auth,call,lec,dis);
				myThread.start();
				myThread1.start();
				myThread_.start();
				myThread1_.start();
			}else {
				MyThread2 myThread2 = new MyThread2(auth,call,lec,dis);
				MyThread3 myThread3 = new MyThread3(auth,call,lec,dis);
				MyThread2 myThread2_ = new MyThread2(auth,call,lec,dis);
				MyThread3 myThread3_ = new MyThread3(auth,call,lec,dis);
				myThread2_.start();
				myThread3_.start();
				myThread2.start();
				myThread3.start();
			}
		}
	}
}

class MyThread extends Thread{
	String auth;
	String call;
	String lec;
	String dis;
	String res;
	
	public MyThread(String auth,String call,String lec,String dis) {
		this.auth = auth;
		this.call = call;
		this.lec = lec;
		this.dis = dis;
		run();
	}
	
	private void request() {
		String url ="https://webreg1.reg.uci.edu/cgi-bin/wramia";
		Map<String,String> headers = new HashMap<>();
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headers.put("Origin", "https://webreg1.reg.uci.edu");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Content-Type", "application/json");
		headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
		headers.put("Referer", "https://webreg1.reg.uci.edu/cgi-bin/wramia");
		headers.put("Accept-Language", "en-US,en;q=0.9");
		headers.put("Cookie", "ucinetid_auth="+auth);
		Map<String,String> params = new HashMap<>();
		params.put("page", "enrollmentMenu");
		params.put("call", call);
		params.put("button", "Send Request");
		params.put("mode", "add");
		params.put("courseCode", lec);
		params.put("gradeOption", "");
		params.put("varUnits", "");
		params.put("authCode", "");
		params.put("courseCode", lec);
		res = new HttpRequest().doPost(url, headers, params, false);
		res = res + "\n" + params.get("courseCode") + "\n";
		Document doc = Jsoup.parse(res);
		Element rows = doc.select("center").get(1);
		Text.res = Text.res + rows.text() + "\n";
	}
	
	public void run() {
		request();
	}
}
class MyThread1 extends Thread{
	String auth;
	String call;
	String lec;
	String dis;
	String res;
	
	public MyThread1(String auth,String call,String lec,String dis) {
		this.auth = auth;
		this.call = call;
		this.lec = lec;
		this.dis = dis;
		run();
	}
	
	private void request() {
		String url ="https://webreg1.reg.uci.edu/cgi-bin/wramia";
		Map<String,String> headers = new HashMap<>();
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headers.put("Origin", "https://webreg1.reg.uci.edu");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Content-Type", "application/json");
		headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
		headers.put("Referer", "https://webreg1.reg.uci.edu/cgi-bin/wramia");
		headers.put("Accept-Language", "en-US,en;q=0.9");
		headers.put("Cookie", "ucinetid_auth="+auth);
		Map<String,String> params = new HashMap<>();
		params.put("page", "enrollmentMenu");
		params.put("call", call);
		params.put("button", "Send Request");
		params.put("mode", "add");
		params.put("courseCode", dis);
		params.put("gradeOption", "");
		params.put("varUnits", "");
		params.put("authCode", "");
		params.put("courseCode", dis);
		res = new HttpRequest().doPost(url, headers, params, false);
		res = res + "\n" + params.get("courseCode") + "\n";
		Document doc = Jsoup.parse(res);
		Element rows = doc.select("center").get(1);
		Text.res = Text.res + rows.text() + "\n";
	}
	
	public void run() {
		request();
	}
}

class MyThread2 extends Thread{
	String auth;
	String call;
	String lec;
	String dis;
	String res;
	
	public MyThread2(String auth,String call,String lec,String dis) {
		this.auth = auth;
		this.call = call;
		this.lec = lec;
		this.dis = dis;
		run();
	}
	
	private void request() {
		String url ="https://webreg2.reg.uci.edu/cgi-bin/wramia";
		Map<String,String> headers = new HashMap<>();
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headers.put("Origin", "https://webreg2.reg.uci.edu");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Content-Type", "application/json");
		headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
		headers.put("Referer", "https://webreg2.reg.uci.edu/cgi-bin/wramia");
		headers.put("Accept-Language", "en-US,en;q=0.9");
		headers.put("Cookie", "ucinetid_auth="+auth);
		Map<String,String> params = new HashMap<>();
		params.put("page", "enrollmentMenu");
		params.put("call", call);
		params.put("button", "Send Request");
		params.put("mode", "add");
		params.put("courseCode", lec);
		params.put("gradeOption", "");
		params.put("varUnits", "");
		params.put("authCode", "");
		params.put("courseCode", lec);
		res = new HttpRequest().doPost(url, headers, params, false);
		res = res + "\n" + params.get("courseCode") + "\n";
		Document doc = Jsoup.parse(res);
		Element rows = doc.select("center").get(1);
		Text.res = Text.res + rows.text() + "\n";
	}
	
	public void run() {
		request();
	}
}

class MyThread3 extends Thread{
	String auth;
	String call;
	String lec;
	String dis;
	String res;
	
	public MyThread3(String auth,String call,String lec,String dis) {
		this.auth = auth;
		this.call = call;
		this.lec = lec;
		this.dis = dis;
		run();
	}
	
	private void request() {
		String url ="https://webreg2.reg.uci.edu/cgi-bin/wramia";
		Map<String,String> headers = new HashMap<>();
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headers.put("Origin", "https://webreg2.reg.uci.edu");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Content-Type", "application/json");
		headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
		headers.put("Referer", "https://webreg2.reg.uci.edu/cgi-bin/wramia");
		headers.put("Accept-Language", "en-US,en;q=0.9");
		headers.put("Cookie", "ucinetid_auth="+auth);
		Map<String,String> params = new HashMap<>();
		params.put("page", "enrollmentMenu");
		params.put("call", call);
		params.put("button", "Send Request");
		params.put("mode", "add");
		params.put("courseCode", dis);
		params.put("gradeOption", "");
		params.put("varUnits", "");
		params.put("authCode", "");
		params.put("courseCode", dis);
		res = new HttpRequest().doPost(url, headers, params, false);
		res = res + "\n" + params.get("courseCode") + "\n";
		Document doc = Jsoup.parse(res);
		Element rows = doc.select("center").get(1);
		Text.res = Text.res + rows.text() + "\n";
	}
	
	public void run() {
		request();
	}
}