package chatPipeline;

import java.awt.Button;
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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

public class ClientUI extends Frame implements Runnable,ActionListener {
	public static TextField txtMessage;
	public static TextArea taContent;
	public JButton btnSend;
	boolean isConnected;
	private PipedOutputStream out = new PipedOutputStream();
	private PipedInputStream in = new PipedInputStream();
	static String msgOut;
	String tem;
	boolean isSent= false;

	public ClientUI(String clientTitle,PipedOutputStream out,PipedInputStream in) throws Exception {
		super(clientTitle);
		this.out = out;
		this.in= in;

		System.out.println("Client creation");
		Panel panelTop = new Panel();
		panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel panelContent = new JPanel();
		taContent = new TextArea(30, 50);
		taContent.setEditable(false);
		
		taContent.setBackground(new Color(204, 255, 255));
		taContent.setText("Client is started!!!\n--------------------------\n");
		Font myFont1 = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		taContent.setFont(myFont1);
		panelContent.add(taContent);
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		Panel panelBottom = new Panel();
		panelBottom.setLayout(new FlowLayout(FlowLayout.LEFT));
		txtMessage = new TextField(45);
		btnSend = new JButton("Send");
		panelBottom.add(txtMessage);
		panelBottom.add(btnSend);

		add(panelTop, "North");
		add(panelContent);
		add(panelBottom, "South");

		addEventForButton();
		int w = this.getSize().width;
	    int h = this.getSize().height;
	    this.setLocation(w,h);
		setVisible(true);
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		setResizable(false);
	}

	public void addEventForButton() {
		btnSend.addActionListener(this);
	}

	public void sendMessageToServer()  {
		msgOut = txtMessage.getText()+"";
		pushMsgToScreen(msgOut);
		txtMessage.setText("");
	}

	public static void pushMsgToScreen(String msg) {
		taContent.append("\nClient: " + msg);
	}

	public static void receiveDataFromServer(String msg) {
		taContent.append("\nServer: "+msg );
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Send") {
			isSent = true;
			try {
				writeMessage(txtMessage.getText());
				sendMessageToServer();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
	
	//----------------------------------------------
	@Override
	public void run() {
		while(true){
			readMessage();
		}
	}	
// xu ly o day
	private void writeMessage(String tem) {
		try {
			if(isSent){
			out.write(tem.getBytes());
			} 
			isSent = false;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void readMessage() {
		byte[] buf = new byte[1024];
		
		try {
			int len = in.read(buf);
			System.out.println("Server: " + new String(buf, 0, len));
			receiveDataFromServer(new String(buf, 0, len));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}
