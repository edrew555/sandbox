using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using TopicPublisher;

namespace TopicPublisher
{
    public class Program
    {
        const string TOPIC_NAME = "MyTestTopic";
        const string BROKER = "tcp://localhost:61616";

        private SimpleTopicPublisher publisher;
        private readonly StringBuilder builder = new StringBuilder();
        private delegate void SetTextCallback(string text);



        static void Main(string[] args)
        {
            try
            {
                SimpleTopicPublisher publisher = new SimpleTopicPublisher(TOPIC_NAME, BROKER);
                publisher.SendMessage("hello world");
                publisher.Dispose();

                Console.WriteLine("Message Sent.");
                Console.WriteLine("Press any key to exit...");
                Console.ReadKey();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                Console.WriteLine("Press any key to exit...");
                Console.ReadKey();
            }
        }



      
    }
}