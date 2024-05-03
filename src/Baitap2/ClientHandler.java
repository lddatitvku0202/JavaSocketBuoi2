package Baitap2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private String username;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Enter your username: ");
            username = in.readLine();
            Server.broadcast(username + " has joined the chat.");

            String message;
            while ((message = in.readLine()) != null) {
                Server.broadcast(username + ": " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Server.removeClient(this);
            Server.broadcast(username + " has left the chat.");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}