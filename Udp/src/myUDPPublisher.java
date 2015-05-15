
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class myUDPPublisher
{

	static String DEFAULT_PORT = "4607";
	static String DEFAULT_HOST = "localhost";	

	public static void main(String[] args)
	{		
		try{
		
			 String port = System.getProperty("udp.pub.port", DEFAULT_PORT);
			 String host = System.getProperty("udp.pub.host", DEFAULT_HOST);
			 
			 String filepath = System.getProperty("udp.pub.msg", "" );

			 if(!filepath.equals("") && !filepath.equals("${udp.pub.msg}"))
			 {
				 System.out.println("filepath: "+filepath);
				 String msg="";
				 
				 try{
					msg = new String(Files.readAllBytes(Paths.get(filepath)));
					}
				 catch(Exception e)
				 {
					System.out.println("Could not read file to send.");
					System.exit(-1);
				 }
				  System.out.println("msg:      "+msg);
				  System.out.println("Sending contents of file to "+host+" on port: "+port);
				  
			      DatagramSocket clientSocket = new DatagramSocket();
			      InetAddress IPAddress = InetAddress.getByName(DEFAULT_HOST);
			      byte[] sendData = new byte[1024];
			      byte[] receiveData = new byte[1024];

			      sendData = msg.getBytes();
			      DatagramPacket sendPacket =
				  new DatagramPacket(sendData, sendData.length, 
						     IPAddress, Integer.parseInt(port) );
			      clientSocket.send(sendPacket); 
			      clientSocket.close();
			 }
			 else
			 {				
				 System.out.println("Will publish to "+host+" on port: "+port);			
			
				 while(true){
					  BufferedReader inFromUser =
					  new BufferedReader(new InputStreamReader
								 (System.in));
					  DatagramSocket clientSocket = new DatagramSocket();
					  InetAddress IPAddress = InetAddress.getByName(DEFAULT_HOST);
					  byte[] sendData = new byte[1024];
					  byte[] receiveData = new byte[1024];
					  System.out.println("Enter message to transmit: ");
					  String sentence = inFromUser.readLine();
					  sendData = sentence.getBytes();
					  DatagramPacket sendPacket =
					  new DatagramPacket(sendData, sendData.length, 
								 IPAddress, Integer.parseInt(port) );
					  clientSocket.send(sendPacket); 
					  clientSocket.close();
				 }
			}
		     
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
		System.exit(0);
			      
	}
}
