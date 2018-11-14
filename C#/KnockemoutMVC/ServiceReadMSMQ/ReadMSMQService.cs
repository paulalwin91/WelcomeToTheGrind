namespace ServiceReadMSMQ
{
    using KnockemoutMVC.Database;
    using System;
    using System.IO;
    using System.Messaging;
    using System.ServiceProcess;
    using System.Text;
    using System.Threading;
    using System.Threading.Tasks;

    /// <summary>
    /// Defines the <see cref="ReadMSMQService" />
    /// </summary>
    public partial class ReadMSMQService : ServiceBase
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="ReadMSMQService"/> class.
        /// </summary>
        public ReadMSMQService()
        {
            InitializeComponent();
        }

        /// <summary>
        /// The onDebug
        /// </summary>
        public void onDebug()
        {
            OnStart(null);
        }

        /// <summary>
        /// The OnStart
        /// </summary>
        /// <param name="args">The args<see cref="string[]"/></param>
        protected override void OnStart(string[] args)
        {
            Task readFromQueueTask = Task.Run(() => StartPollingMSMQ());
            Task writeToQueueTask =  Task.Run(() => QWriter());
            Task.WaitAll(readFromQueueTask, writeToQueueTask);
            //Task.WaitAll(writeToQueueTask);
            //Task.WaitAll(readFromQueueTask);
            //StartPollingMSMQ();
        }

        private void QWriter() {
            MessageQueue messageQueue;
            

            if (MessageQueue.Exists(@".\Private$\MyQueue"))
            {
                messageQueue = new MessageQueue(@".\Private$\MyQueue");
            }
            else
            {
                messageQueue = MessageQueue.Create(@".\Private$\MyQueue");
            }
            Message momText = new Message();
            momText.Formatter = new BinaryMessageFormatter();
            momText.Label = "Test Message";
            int i = 10;
            while (i > 0)
            {
                string messageToWrite = $"The time now is {DateTime.Now.ToLongDateString()}";                               
                using (var context = new LocalhostEntities())
                {
                    context.Products.Add(new Product
                    {
                        Category = "Electronic",
                        Name = "Service",
                        Price = 10
                    
                    });
                    context.SaveChanges();
                }
                momText.Body = $"Inserted from Service at {DateTime.Now.Ticks}";
                messageQueue.Send(momText);
                Thread.Sleep(5000);
                i--; 
            }
        }
      

        /// <summary>
        /// The OnStop
        /// </summary>
        protected override void OnStop()
        {
        }

        /// <summary>
        /// The StartPollingMSMQ
        /// </summary>
        private void StartPollingMSMQ()
        {
            // NOTE: This code should check if the queue exists, instead of just assuming it does.
            //       Left out for berevity.
            MessageQueue messageQueue;

            if (MessageQueue.Exists(@".\Private$\MyQueue"))
            {
                messageQueue = new MessageQueue(@".\Private$\MyQueue");
            }
            else
            {
                messageQueue = MessageQueue.Create(@".\Private$\MyQueue");
            }

            int i = 0;
            while (true)
            {
                try
                {
                    // This blocks/hangs here until a message is received.
                    Message message = messageQueue.Receive(new TimeSpan(0, 0, 1));
                    message.Formatter = new BinaryMessageFormatter();
                    // Woot! we have something.. now process it...
                    fileGenerator(Convert.ToString(message.Body), i++);
                    // Now repeat for eva and eva and boomski...
                }
                catch (Exception)
                {


                }
                Console.WriteLine("ss");

            }
        }

        /// <summary>
        /// The fileGenerator
        /// </summary>
        private void fileGenerator(string message,int i)
        {
            string fileName = @".\QueMessage" + i + ".txt";

            try
            {
                // Check if file already exists. If yes, delete it. 
                if (File.Exists(fileName))
                {
                    File.Delete(fileName);
                }

                // Create a new file 
                using (FileStream fs = File.Create(fileName))
                {
                    // Add some text to file
                    Byte[] title = new UTF8Encoding(true).GetBytes(message);
                    fs.Write(title, 0, title.Length);
                }
            }
            catch (Exception Ex)
            {
                Console.WriteLine(Ex.ToString());
            }
        }
    }
}
