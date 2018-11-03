using System.Collections.Generic;
using System.Linq;

namespace Delegated
{

    // A Delegate Type 
    public delegate IEnumerable<int> listManipulator(IEnumerable<int> list);

    public delegate void LetSee();
    class Program
    {

        static void Main(string[] args)
        {
            //            newmethod();
            AnotherMethod();
            System.Console.ReadKey();
        }

        private static void AnotherMethod()
        {
            LetSee ss = Foo;
            DeliWrapper(ss);
        }

        private static void DeliWrapper(LetSee printIt)
        {
            printIt();
        }

        private static void NewMethod()
        {
            int[] myArr = { 10, 2, 0, 5, 7, 10, 3, 2, 1, 16, 21 };
            IEnumerable<int> mylist = myArr.ToList();

            //A Delegate Instance!
            listManipulator lessThan10 = (lst) =>
            {
                return lst.Where(p => p < 10);
            };


            listManipulator lessThan10and15 = (lst) =>
            {
                return lst.Where(x => x > 10 && x < 15);
            };


            listManipulator lessThan15and20 = (lst) =>
            {
                return lst.Where(j => j > 15 && j < 20);
            };

            manipulateList(mylist, lessThan10);
            manipulateList(mylist, lessThan10and15);
            manipulateList(mylist, lessThan15and20);
        }

        public static IEnumerable<int> manipulateList(IEnumerable<int> lst, listManipulator funcLst)
        {

            foreach (var item in funcLst(lst))
            {
                System.Console.WriteLine(item);
            }
            System.Console.WriteLine("End");
            return funcLst(lst);
        }

        public static void Foo()
        {
            System.Console.WriteLine("I like to Move it Move t, you like you?!");
        }
    }
}
