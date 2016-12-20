import java.io.*;
import java.net.*;
import java.util.*;
public class ServerThread extends Thread{
	private
		Socket socket = null;
	    ArrayList<Socket> sockets = null;
	    int clientnum = 0;
	public ServerThread(Socket socket, ArrayList<Socket> sockets, int clientnum){
		    this.socket = socket;
		    this.sockets = sockets;
		    this.clientnum = clientnum;
	    }
	
	//文件转输功能
	public void file(){
		try{
//			System.out.println("file started!");
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String num = is.readLine();
			int id = Integer.parseInt(num)-1;
//			while(id > sockets.size()){
//	    		System.out.println("the client is not existed!");
//	    		num = is.readLine();
//	    		id = Integer.parseInt(num)-1;
//	    	}
			PrintWriter os = null;
			String readline = is.readLine();
			while(readline != null){
				os = new PrintWriter(new OutputStreamWriter(sockets.get(id).getOutputStream()));
				System.out.println(readline);
				os.println(readline);
				os.flush();
				readline = is.readLine();
			}
			os.close();
			is.close();
		}catch(IOException e){
			System.out.println("Error:" + e);
		}
	}
	
	//消息转输功能
	public void chat(){
		try{
//			System.out.println("chat started!");
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	PrintWriter os = null;
            String num = is.readLine();
	    	int id = Integer.parseInt(num)-1;
            String line = is.readLine();
            while(!line.equals("bye")){
//		    	while(id > sockets.size()){
//		    		System.out.println("the client is not existed!");
//		    		num = is.readLine();
//		    		id = Integer.parseInt(num)-1;
//		    	}
            	os = new PrintWriter(new OutputStreamWriter(sockets.get(id).getOutputStream()));
            	os.println("client" + clientnum + " says: " + line);;
            	os.flush();
                num = is.readLine();
    	    	id = Integer.parseInt(num)-1;
            	line = is.readLine();
            }
        	os.println("client" + clientnum + " says: " + line);;
        	os.flush();
            os.close();
            is.close();
    	}catch(IOException e){
    		System.out.println("Error:" + e);
    	}
	}
	
	//功能调用
    public void run(){
	    	try{
	            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            String choice = is.readLine();
//	            System.out.println(choice);
	            if(choice.equals("c")){
	            	chat();
	            }
	            else if(choice.equals("s")){
	                file();
	            }
	            is.close();
	    	}catch(IOException e){
	    		System.out.println("Error:" + e);
	    	}
	    }
}
