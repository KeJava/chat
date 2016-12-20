import java.io.*;
import java.net.*;

public class SendMessage extends Thread{
	private
	      Socket socket = null;	   
    public SendMessage(Socket socket){
    	   this.socket = socket;
	}
	   
	public void run(){
		try{
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.print("send message to:");
			String num = sin.readLine();
			os.println(num);
			os.flush();
			String message = sin.readLine();
			while(!message.equals("bye")){
				os.println(message);
				os.flush();
				System.out.print("send message to:");
				num = sin.readLine();		
				os.println(num);
				os.flush();
				message = sin.readLine();
			}
			os.print(message);
			os.flush();
			os.close();
			sin.close();
		}catch(IOException e){
			System.out.println("Error:" + e);
		}
	}
}
