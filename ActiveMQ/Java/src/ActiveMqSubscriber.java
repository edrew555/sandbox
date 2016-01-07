import java.io.*;
 
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqSubscriber implements Runnable, ExceptionListener{
	
	MessageConsumer consumer = null;
	Connection connection = null;
	Session session = null;
	
	String host = "localhost";
	String port = "61616";
	String topic = "MyTestTopic";
	
	ActiveMqSubscriber()
	{}
	
	ActiveMqSubscriber(String _topic)
	{
		topic = _topic;
	}
	
    ActiveMqSubscriber(String _host, String _port, String _topic)
	{
		host = _host;
		port = _port;
		topic = _topic;
	}
	
	public void setup()
	{
		
		try{
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+host+":"+port);

		// Create a Connection
		connection = connectionFactory.createConnection();
		connection.start();

		connection.setExceptionListener(this);

		// Create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createTopic(topic);

		// Create a MessageConsumer from the Session to the Topic or Queue
		consumer = session.createConsumer(destination);
		}
		catch (Exception e) 
			{
				System.out.println("Caught: " + e);
				//e.printStackTrace();	
			}	
	}
	
	public void run( )  {		
	
		if(consumer==null || connection==null || session==null)
			setup();
	
		while(1==1)
		{
		try {			

			// Wait for a message
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Received: " + text);
			} else {
				System.out.println("Received: " + message);
			}


			} 
			catch (Exception e) 
			{
				System.out.println("Caught: " + e);
				//e.printStackTrace();
				System.exit(-1);
			}		
		}
	}
	
	public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occurred.  Shutting down client.");
			System.exit(-1);
        }
	
	public void shutdown()
	{
	
		try{
		if (consumer!=null)
			consumer.close();
		if (session!=null)
			session.close();
		if(connection!=null)
			connection.close();		
		}
		catch (Exception e) 
			{
				System.out.println("Did not shut down gracefully.");
				//System.out.println("Caught: " + e);
				//e.printStackTrace();
			}	
	}
}
	

