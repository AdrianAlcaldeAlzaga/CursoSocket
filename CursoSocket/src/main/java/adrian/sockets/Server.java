package adrian.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Server extends Observable implements Runnable{
	
	private int port;
	
	public Server(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		ServerSocket server = null;
		Socket sc = null;
		DataInputStream in;
		DataOutputStream out;
		
		try {
			server = new ServerSocket(port);
			System.out.println("Servidor iniciado");
			
			// Escucha peticiones de los clientes
			while (true) {
				
				// Acepta el socket del cliente
				sc = server.accept();
				System.out.println("Cliente conectado");
				
				// Recogemos los datos en base al socket del cliente
				in = new DataInputStream(sc.getInputStream());
				
				String message = in.readUTF();
				
				System.out.println(message);
				
				this.setChanged();
				this.notifyObservers(message);
				this.clearChanged();
				
				sc.close();
				System.out.println("Cliente desconectado");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
