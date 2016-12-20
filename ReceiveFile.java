import java.io.*;
import java.net.*;

public class ReceiveFile extends Thread {
	private Socket socket = null;
	
	public ReceiveFile(Socket socket){
		this.socket = socket;
	}
	
	
	public void run(){
		try{
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.print("input your path of file:");
			String path = sin.readLine();
			BufferedWriter wf = new BufferedWriter(new FileWriter(path));
//			BufferedWriter wf = new BufferedWriter(new FileWriter("E:/b.txt"));
			String message = is.readLine();
			while(message != null){
//				System.out.println(message);
				wf.write(message);
				wf.write("\r\n");
			    wf.flush();
			    message = is.readLine();
			}
			System.out.println("received  file!");
			socket.close();
			wf.close();
			is.close();
			sin.close();
		}catch(IOException e){
			System.out.println("Error:" + e);
		}
	}

}
