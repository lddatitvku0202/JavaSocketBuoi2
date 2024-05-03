package Baitap1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

public class ClientHandler implements Runnable {
	private Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;

	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			for (int i = 1; i <= 1000; i++) {
				out.println(i);
				Thread.sleep(1000);
			}
			out.close();
			clientSocket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}