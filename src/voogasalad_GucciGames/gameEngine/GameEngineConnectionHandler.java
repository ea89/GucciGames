package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameEngineConnectionHandler extends Thread {

	private String name;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private volatile GameEngineServer myServer; // get the instance of a server

	/**
	 * Constructs a handler thread, squirreling away the socket. All the
	 * interesting work is done in the run method.
	 */
	public GameEngineConnectionHandler(Socket socket, GameEngineServer server) {
		System.out.println("connectionFormed");
		this.socket = socket;
		this.myServer = server;
	}

	/**
	 * Services this thread's client by repeatedly requesting a screen name
	 * until a unique one has been submitted, then acknowledges the name and
	 * registers the output stream for the client in a global set, then
	 * repeatedly gets inputs and broadcasts them.
	 */
	public void run() {
		try {

			// Create character streams for the socket.
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			myServer.getWriters().add(out);
			System.out.println("just added a print writer");
			System.out.println(myServer.getWriters().size() + " is size right after add");
			// Accept messages from this client and broadcast them.
			// Ignore other clients that cannot be broadcasted to.
			while (true) {
				System.out.println("waiting for client input");
				String input = in.readLine();
				if (input == null) {
					return;
				}

				if (input.startsWith("GAMEDATA")) {

					System.out.println("OMG SOME DATA ON SERVER");

					input = in.readLine();
					int lengthXML = Integer.parseInt(input);

					StringBuilder myBuilder = new StringBuilder();

					for (int i = 0; i < lengthXML; i++) {
						myBuilder.append((char) in.read());
					}

					myServer.updateGameEngine(myBuilder.toString());

					for (PrintWriter writer : myServer.getWriters()) {

						System.out.println("connection handler thinks the size is" + myBuilder.toString().length());
						writer.print("GAMEDATA\n" + lengthXML + "\n" + myBuilder.toString() + "\n");
						writer.flush();
					}
				}

				if (input.startsWith("CHAT")) {

					System.out.println("OMG SOME DATA ON SERVER");

					input = in.readLine();
					int lengthXML = Integer.parseInt(input);

					StringBuilder myBuilder = new StringBuilder();

					for (int i = 0; i < lengthXML; i++) {
						myBuilder.append((char) in.read());
					}

					myServer.updateChat(myBuilder.toString());

					for (PrintWriter writer : myServer.getWriters()) {

						System.out.println("connection handler thinks the size is" + myBuilder.toString().length());
						writer.print("CHAT\n" + lengthXML + "\n" + myBuilder.toString() + "\n");
						writer.flush();
					}
				}

			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			finish();
		}
	}

	public void finish() {
		if (out != null) {
			myServer.getWriters().remove(out);
		}
		try {
			socket.close();
			System.out.println("close thread");
		} catch (IOException e) {
		}
	}

}
