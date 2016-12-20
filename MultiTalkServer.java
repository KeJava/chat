import java.io.*;
import java.net.*;
import java.util.*;

public class MultiTalkServer {
	public static int clientnum = 0; //��̬��Ա��������¼��ǰ��������
	public void work() throws IOException{
		ServerSocket serversocket = null;
		Socket socket = null;
		ArrayList<Socket> sockets = new ArrayList<Socket>();
		boolean listening = true;
		try{
			serversocket = new ServerSocket(4700); //��4700�˿ڼ�ͦ�ͻ��˵�����
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
