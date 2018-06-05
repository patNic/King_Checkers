package network;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender{
	private Socket socket;
	public Sender(Socket socket) {
		this.socket = socket;
	}
	
	public void send(Object obj) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(obj);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
}
