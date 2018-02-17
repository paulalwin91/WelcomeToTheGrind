using System;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {

        public static void Main()
        {
            long totalSize = 0;

            String[] args = Environment.GetCommandLineArgs();
            if (args.Length == 1)
            {
                Console.WriteLine("There are no command line arguments.");
                Console.ReadLine();
                return;
            }
            if (!File.Exists(args[1]))
            {
                Console.WriteLine("The directory does not exist.");
                Console.ReadLine();
                return;
            }

            var linecount = File.ReadLines(args[1]).Count();


            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            Parallel.For(0, linecount,
                            i =>
                            {
                                Interlocked.Add(ref totalSize, 1);
                                //totalSize = totalSize + 1;
                            });

            stopwatch.Stop();
            long parralleltime = stopwatch.ElapsedMilliseconds;
            stopwatch.Reset();
            long totalSeqSize = 0;
            stopwatch.Start();

            foreach (var line in File.ReadLines(args[1]))
            {
                totalSeqSize = totalSeqSize + 1;
            }

            stopwatch.Stop();
            long SequentialTime = stopwatch.ElapsedMilliseconds;
            stopwatch.Reset();

            stopwatch.Start();
            long localTtotalparll = 0;
            long totalLocal = 0;


            Parallel.For<long>(0, linecount, () => 0, (j, loop, subTotal) =>
            {
                subTotal += 1;
                return subTotal;
            },
            (x) => Interlocked.Add(ref totalLocal, x)
            );


            stopwatch.Stop();
            localTtotalparll = stopwatch.ElapsedMilliseconds;

            Console.WriteLine("Directory '{0}':", args[1]);
            Console.WriteLine("{0:N0} files, {1:N0} bytes, {2:N0} time", "1", totalSize, parralleltime);
            Console.WriteLine("{0:N0} Seq files, {1:N0} bytes, {2:N0} time", "1", totalSeqSize, SequentialTime);
            Console.WriteLine("{0:N0} total, {1:N0} time", totalLocal, localTtotalparll);
            JustChecking.pain();
            Console.ReadLine();
        }


    }
}
