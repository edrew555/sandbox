import java.io.*;
import java.net.*;

public class PublisherProgram{

	public static void main(String[] args) {
	
		String host = "localhost";
		String port = "61616";
		String topic = "MyTestTopic";
		String msg = "hello world - from java publisher";
		
        ActiveMqPublisher.send(host, port, topic, msg);		
		
    }
	
}