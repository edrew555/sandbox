using System;
using TopicSubscriber;

namespace TopicSubscriber
{
    class Program
    {
        const string TOPIC_NAME = "MyTestTopic";
        const string BROKER = "tcp://localhost:61616";
        const string CLIENT_ID = "Subscriber1";
        const string CONSUMER_ID = "Subscriber1";

        static void Main(string[] args)
        {
            try
            {
                using (SimpleTopicSubscriber subscriber = new SimpleTopicSubscriber(TOPIC_NAME, BROKER, CLIENT_ID, CONSUMER_ID))
                {
                    subscriber.OnMessageReceived += new MessageReceivedDelegate(subscriber_OnMessageReceived);
                    
                    Console.WriteLine("Listening for Messages.");
                    Console.WriteLine("Press any key to exit...");
                    Console.ReadKey();

                    subscriber.Dispose();
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                Console.WriteLine("Press any key to exit...");
                Console.ReadKey();
            }


        }

        static void subscriber_OnMessageReceived(string message)
        {
            Console.WriteLine(message);
        }
    }
}