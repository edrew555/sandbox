import java.io.*;
import java.net.*;

public class SubscriberProgram{

	public static void main(String[] args) {
	
		String host = "localhost";
		String port = "61616";
		String topic = "MyTestTopic";
		
		System.out.println("listening for message...");
		
        ActiveMqSubscriber.listen(host, port, topic);		
		
		System.out.println("Press any key to exit.");
		
		try{System.in.read();}
	    catch(Exception e){}
		
    }
	
}