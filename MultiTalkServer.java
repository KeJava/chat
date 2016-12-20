import java.io.*;
import java.net.*;
import java.util.*;

public class MultiTalkServer {
	public static int clientnum = 0; //静态成员变量，记录当前在线人数
	public void work() throws IOException{
		ServerSocket serversocket = null;
		Socket socket = null;
		ArrayList<Socket> sockets = new ArrayList<Socket>();
		boolean listening = true;
		try{
			serversocket = new ServerSocket(4700); //在4700端口坚挺客户端的请求
		}catch(IOException e){
			System.out.println("could not listen on port 4700!");
		}
		while(listening){
			socket = serversocket.accept();
			clientnum++;
			System.out.println("the client-" + clientnum + " is connected!");
			sockets.add(socket);
			new ServerThread(socket, sockets, clientnum).start();
		}
		serversocket.close();
	}
	
    public static void main(String args[]) throws IOException{
    	new MultiTalkServer().work();
    }
}
