using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Threading.Tasks;

namespace ServiceReadMSMQ
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        static void Main()
        {
#if DEBUG
                  ReadMSMQService s = new ReadMSMQService();
            s.onDebug();

#endif

#if !DEBUG
            ServiceBase[] ServicesToRun;
            ServicesToRun = new ServiceBase[]
            {
                new ReadMSMQService()
            };
            ServiceBase.Run(ServicesToRun);
#endif
        }
    }
}
