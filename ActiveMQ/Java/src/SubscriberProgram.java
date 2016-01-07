import java.io.*;
import java.net.*;

public class SubscriberProgram{

	public static void main(String[] args) {
	
		String topic = "MyTestTopic";
		
		System.out.println("listening for message...");
		thread(new ActiveMqSubscriber("MyTestTopic"), false);
				
		
		System.out.println("Press enter to exit.");		
		try{
			System.in.read();
			System.out.println("done");			
			System.exit(0);
			}
	    catch(Exception e){System.exit(0);}		
		
    }
	   public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}