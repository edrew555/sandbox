
import java.io.*;
import java.net.*;

class myUDPSubscriber
{
	static String DEFAULT_PORT = "4607";
	static String DEFAULT_HOST = "localhost";
	
	static int BUFFER_SIZE = 4096;
	
	public static void main(String[] args) 
	{	
        Thread thr1 = new Thread( ExitThread.exitRunnable );
        thr1.start();
		
		try {
			String port = System.getProperty("udp.sub.port", DEFAULT_PORT);
			String host = System.getProperty("udp.sub.host", DEFAULT_HOST);
			System.out.println("Listening to "+host+" on port: "+port);
			
	        DatagramSocket s = new DatagramSocket(null);
	        InetSocketAddress address = new InetSocketAddress(host, Integer.parseInt(port) );

	        s.bind(address);
		
			
			while(true)
			{
				try {
				
				        byte buffer[] = new byte[BUFFER_SIZE];
				        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				
				        System.out.println("Waiting...");
				        s.receive(packet);
				        System.out.println("Received!\n");
				        
				        String strPacket = new String( packet.getData(), 0, packet.getLength(), "US-ASCII");
				        System.out.println( strPacket );
						System.out.println ("");
						
				    } catch (Exception e) {
				        e.printStackTrace();
				        System.exit(0);
				        }
			}
			
		}
		catch (Exception e) {
	        e.printStackTrace();
	        System.exit(0);
	        }
		
	}
}
