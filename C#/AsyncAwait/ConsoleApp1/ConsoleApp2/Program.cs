using System;
using System.Diagnostics;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleApp2
{
    class Program
    {
        static void Main(string[] args)
        {
            // myOwnMainHmpf();
            basicCheck();
            Console.ReadLine();
        }

        private static void basicCheck()
        {
            //SyncMethod3();
            //AMethod3();
            Method4();
            //ParMethod4();
            Console.ReadLine();
        }

        private static void Method4()
        {
            long stotal = 0;
            Console.WriteLine("inside method 4 ");
            for (long i = 0; i < 1000000000000; i++)
            {
                stotal = stotal + (i / 10) + (i * 20) % 20 + 10;
            }
            Console.WriteLine("exising method 4 ");


        }

        private static void ParMethod4()
        {
            long stotal = 0;
            Console.WriteLine("inside method par 4 ");

            Parallel.For<long>(0, 1000000000000, () => 0, (i, loops, subtotal) =>
            {
                subtotal = subtotal + (i / 10) + (i * 20) % 20 + 10;
                return subtotal;
            },
            (x) => { Interlocked.Add(ref stotal, x); }
            );
            Console.WriteLine("exising method par 4 ");
        }

        private async static void AMethod3()
        {
            await Task.Run(() =>
            {
                Console.WriteLine("inside method 3 ");
                for (int i = 0; i < 1000; i++)
                {
                    Thread.Sleep(1);
                }
                Console.WriteLine("exising method 3 ");
            });
        }

        private static void SyncMethod3()
        {

            Console.WriteLine("inside method 3 ");
            for (int i = 0; i < 1000; i++)
            {
                Thread.Sleep(1);
            }
            Console.WriteLine("exising method 3 ");
        }

        static async void myOwnMainHmpf()
        {

            Console.WriteLine("Mommy when can I start?!! :/ I want to start toute de suite!");
            Console.WriteLine();
            Console.WriteLine("Mais mon fils, jai du chose travail maintenent!");
            Console.WriteLine();
            Console.WriteLine("Mais mommo!");
            Console.WriteLine();
            Console.WriteLine("mommy puts her job in a task and schedules it later before the ends of the day! Bonne Idee eh?");
            Console.WriteLine();

            Task t = Task.Run(() =>
           {
               Console.WriteLine("Mommys 'some work' begins");
               Console.WriteLine("Look after all the effort!!! I mined gold worth - " + Method1() + " shumbas!!!");
           });


            Console.WriteLine("tout de suite!Mon fils!!!");

            Console.WriteLine("-------------------begins---------------");
            await t; //This is a handle to the task decalred above, now if you dont put this all the thing in the method will execute and then this task will be called!
            Console.WriteLine("-------------------end---------------");

            Task<int> t2 = Method2();
            int result = await t2;  // This awaits for t2 to finish and returns the result to result!

            Console.WriteLine();
            Console.WriteLine("Now is everybody happy?!:/" + result);
            Console.ReadLine();

        }

        static async Task<int> Method2()
        {

            int[] nums = Enumerable.Range(0, 100000).ToArray();
            int myTtoal = 0;

            await Task.Run(() =>
            {
                Console.WriteLine("EMthos 2 starts");
                Parallel.For(0, nums.Length, () => 0, (i, loop, stotal) =>
                {
                    stotal = stotal + (i / 10) + (i * 20) % 20 + 10;
                    return stotal;
                },
                    (x) => { Interlocked.Add(ref myTtoal, x); }
                );
                Console.WriteLine("EMthos 2 ends");
            }
            );
            return myTtoal;
        }

        static long Method1()
        {
            long total = 0;
            long smarttotal = 0;
            int[] nums = Enumerable.Range(0, 1000).ToArray();

            //Normal Mother
            Console.WriteLine("Super important work begins for a 'normal' mother.");
            Stopwatch sp = new Stopwatch();
            sp.Start();
            for (int i = 0; i < nums.Length; i++)
            {
                total = total + nums[i];
                Thread.Sleep(1);
            }
            sp.Stop();
            Console.WriteLine(String.Format("Super important 'normal mother' work ends. Time {0}, total {1} ", sp.ElapsedMilliseconds, total));
            sp.Reset();

            //Smart Mother
            Console.WriteLine("Super important work begins for a 'Smart' mother.");
            sp.Start();

            Parallel.For(0, nums.Length, () => 0, (j, loop, subtotal) =>
            {
                Thread.Sleep(1);
                subtotal = subtotal + nums[j];
                return subtotal;
            }, (x) =>
            {
                Interlocked.Add(ref smarttotal, x);
            });


            sp.Stop();
            Console.WriteLine(String.Format("Super important smart work ends. Time {0}, total {1} ", sp.ElapsedMilliseconds, smarttotal));
            sp.Reset();
            return total;
        }
    }
}
