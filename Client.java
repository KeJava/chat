import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	private
	    Socket socket = null;
	
	public Client(Socket socket){
		this.socket = socket;
	}
	
	public void chat() throws IOException{
		new ReceiveMessage(socket).start();
		new SendMessage(socket).start();
	}
	public void sendFile() throws IOException{
		new SendFile(socket).start();
	}
	public void receiveFile() throws IOException{
		new ReceiveFile(socket).start();
	}
	
	public static void main(String args[]) throws IOException{
//		String ip = args[0];
//		Socket socket = new Socket(ip, 4700);
		Socket socket = new Socket("127.0.0.1", 4700);
		Client client = new Client(socket);
		PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.print("Please input c(chat), s(send file), r(receive file), q(quit):");
		Scanner sin = new Scanner(System.in);
		String choice = sin.nextLine();
//        while(!choice.equals("q")){
//    	   switch(choice){
//  		       case "c":{
//  		 		    os.println(choice);
//  				    os.flush();
//		    	    client.chat();
//		    	    break;
//		       }
//		       case "s":{
//		   		    os.println(choice);
//				    os.flush();
//		    	    client.sendFile();
//		        	break;
//	           }
//	           case "r":{
//	    	    	client.receiveFile();
//	    	    	break;
//	    	   }
//    	    }
//    	   choice = sin.nextLine();
//    	   os.println(choice);
//        }
 	   switch(choice){
	       case "c":{
	   		os.println(choice);
			os.flush();
    	    client.chat();
    	    break;
       }
           case "s":{
   	    	os.println(choice);
   		    os.flush();
         	client.sendFile();
        	break;
       }
           case "r":{
	    	client.receiveFile();
	    	break;
	   }
    }while(!choice.equals("q"));
	}

}
