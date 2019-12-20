package chatPipeline;

import java.awt.Button;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;


public class ServerUI extends Frame implements Runnable,ActionListener {
	public TextField  txtMessage;
	JPanel panelContent;
	public static TextArea taContent;
	public JButton btnSend;
	boolean isConnected;
	static ScriptEngineManager sem;
	static ScriptEngine engine;
	private PipedInputStream in = new PipedInputStream();
	private PipedOutputStream out = new PipedOutputStream();
	Font myFont1 = new Font(Font.MONOSPACED, Font.PLAIN, 14);
	Font myFont2 = new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 16);
	public ServerUI(String clientTitle, PipedInputStream in,PipedOutputStream out) throws Exception{
		super(clientTitle);
		
		this.in = in;
		this.out = out;
		sem = new ScriptEngineManager();
		engine = sem.getEngineByName("JavaScript");
		System.out.println("Server is starting...");
		Panel panelTop = new Panel();
		panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));		
		panelContent = new JPanel();		
		taContent = new TextArea(30,50);
		taContent.setEditable(false);
		taContent.setText("Server is started!!!\n--------------------------\n");
		taContent.setBackground(new Color(255, 204, 204));
		panelContent.add(taContent);
		
		taContent.setFont(myFont1);
		Panel panelBottom  = new Panel();
		panelBottom.setLayout(new FlowLayout(FlowLayout.LEFT));
		txtMessage = new TextField(45);
		btnSend = new JButton("Send");
		panelBottom.add(txtMessage);
		panelBottom.add(btnSend);
		
		add(panelTop,"North");
		add(panelContent);
		add(panelBottom,"South");
		
		addEventForButton();
		setVisible(true);
		pack();
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});	
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 int w = this.getSize().width;
	     int h = this.getSize().height;
	     int x = (dim.width-w)/2;
	     int y = (dim.height-h)/2;
	        // Move the window
	        this.setLocation(x, y-60);
		setResizable(false);
	
	}
	public void addEventForButton(){
		btnSend.addActionListener(this);
	}
	
	public void sendMessageToClient() {
		String msgOut = txtMessage.getText();
		pushMsgToScreen(msgOut);// in ra màn hình
		txtMessage.setText("");
	}
	
	public void pushMsgToScreen(String msg){
		taContent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	
		taContent.append("\nServer: "+msg);	
				
	}
	public void receiveDataFromClient(String msg) {
		taContent.append("\nClient: "+msg);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()=="Send"){
			
			try {
				writeMessage(txtMessage.getText());
				sendMessageToClient();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	@Override
	public void run() {
		while(true){
		readMessage();
		}
	}

	private void readMessage() {
		byte[] buf = new byte[1024];
		
		try {
			int len = in.read(buf);
			System.out.println("Client: " + new String(buf, 0, len));
			receiveDataFromClient(new String(buf, 0, len));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	private void writeMessage(String tem) throws IOException 
	{	
		
		out.write(tem.toUpperCase().getBytes());

	}
	
}
