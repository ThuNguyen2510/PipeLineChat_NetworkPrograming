package chatPipeline;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedTest {

	public static void main(String[] args) throws Throwable {
		PipedOutputStream c_out = new PipedOutputStream();
		PipedInputStream s_in = new PipedInputStream();
		PipedOutputStream s_out = new PipedOutputStream();
		PipedInputStream c_in = new PipedInputStream();
		Thread client = new Thread(new ClientUI("Client Chat!",c_out,c_in));
		Thread server = new Thread(new ServerUI("Server Chat!",s_in,s_out));		
		try {
			c_out.connect(s_in);
			s_out.connect(c_in);
			client.start();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
