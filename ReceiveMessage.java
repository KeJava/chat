import java.io.*;
import java.net.*;

public class ReceiveMessage extends Thread {
	Socket socket = null;
	public ReceiveMessage(Socket socket){
		this.socket = socket;
	}
	public void run(){
		try{
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = is.readLine();
			while(!message.equals("bye")){
				System.out.println(message);
				message = is.readLine();
			}
			System.out.println("bye");
			is.close();
		}catch(IOException e){
			System.out.println("Error:" + e);
		}
	}

}
