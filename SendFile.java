import java.io.*;
import java.net.*;

public class SendFile extends Thread {
	private Socket socket = null;
	
	public SendFile(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.print("send file to:");
			String num = sin.readLine();
			os.println(num);
			os.flush();
			System.out.print("input the path of your file:");
			String path = sin.readLine();
			BufferedReader rf = new BufferedReader(new FileReader(path));
			String message = rf.readLine();
			while(message != null){
				System.out.println(message);
				os.println(message);
				os.flush();
				message = rf.readLine();
			}
			socket.close();
			os.close();
			rf.close();
			sin.close();
		}catch(IOException e){
			System.out.println("Error:" + e);
		}
	}

}
