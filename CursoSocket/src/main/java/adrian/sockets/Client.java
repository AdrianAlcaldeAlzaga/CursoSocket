package adrian.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{
	
	private int port;
	private String message;
	
	public Client(int port, String message) {
		this.port = port;
		this.message = message;
	}

	@Override
	public void run() {
		final String host = "127.0.0.1";
		DataInputStream in;
		DataOutputStream out;
		
		try {
			Socket sc = new Socket(host, port); 
			
			// Recogemos los datos en base al socket del cliente
			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());
			
			out.writeUTF(message);
			
			
			
			sc.close();
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
