package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Doit;

public class EnrollYourClass {
	static JFrame window = new JFrame("EnrollYourClass--CheckFor(Web1 or Web2)");
	static JPanel panel = new JPanel(null);
	static JTextField auth = new JTextField("Enter Auth here");
	static JTextField call = new JTextField("Enter Call here");
	static JTextField lec = new JTextField("Enter Lecture Code here");
	static JTextField dis = new JTextField("Enter Discussion Code Here");
	static JTextField host = new JTextField("Enter host here");
	static JButton submit = new JButton("Submit");
	static JButton stop = new JButton("Stop");
	static JLabel hint = new JLabel("检查单：是否登录超过15min，是否正处在提交页面");
	static JLabel count = new JLabel("请求次数：");
	static JScrollPane sp = new JScrollPane();
	static JTextArea ta = new JTextArea();
	
	static Thread logic;
	
	public static void createWindow() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setContentPane(panel);
		auth.setBounds(10,10,100,50);
		call.setBounds(120,10,100,50);
		lec.setBounds(230,10,100,50);
		dis.setBounds(340,10,100,50);
		host.setBounds(450,10,100,50);
		submit.setBounds(560, 10, 100, 50);
		stop.setBounds(670,10,100,50);
		hint.setBounds(50,60,700,50);
		count.setBounds(50,100,700,50);
		sp.setBounds(50,150,700,400);
		ta.setBounds(50,150,700,400);
		sp.setViewportView(ta);
		panel.add(sp);
		panel.add(auth);
		panel.add(call);
		panel.add(lec);
		panel.add(dis);
		panel.add(host);
		panel.add(submit);
		panel.add(stop);
		panel.add(hint);
		panel.add(count);
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					ta.setText(Text.res);
					count.setText("请求次数："+Text.count);
					panel.updateUI();
					try {
						Thread.currentThread();
						Thread.sleep(500);
					}catch(Exception e) {}
				}
			}
		}).start();
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submit(auth.getText(),call.getText(),lec.getText(),dis.getText(),host.getText());
			}
		});
		stop.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				logic.stop();
			}
		});
	}
	
	public static void submit(String auth,String call,String lec,String dis,String host) {
		logic = new Thread(new Runnable() {
			public void run() {
				Doit doit = new Doit(auth,call,lec,dis,host);
			}
		});
		logic.start();
	}
	
	public static void main(String[] args) {
		createWindow();
		window.setVisible(true);
	}
}

