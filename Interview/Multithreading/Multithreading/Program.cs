using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Multithreading
{
    public class Program
    {
        static void Main(string[] args)
        {
            int[] array = new int[1000];

            for (int i = 0; i < 1000; i++)
                array[i] = i;

            for(int j = 0; j<42;j++)
            {
                Threadit edit = new Threadit();
                ThreadStart strt = new ThreadStart(edit.printMe());
            //    Thread newthrad = new Thread(;

            newthrad.Start();
            }

            Console.ReadKey();
        }

        


    }

    public class Threadit
    {
        public void printMe()
        {
            Console.WriteLine("gotach");
        }

    }


}
