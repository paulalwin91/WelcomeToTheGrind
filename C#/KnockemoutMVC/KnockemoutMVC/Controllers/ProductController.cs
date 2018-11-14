using KnockemoutMVC.DAL.Interface;
using KnockemoutMVC.DAL.Repository;
using KnockemoutMVC.Database;
using KnockemoutMVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Messaging;
using System.ServiceProcess;
using System.Web;
using System.Web.Mvc;

namespace KnockemoutMVC.Controllers
{
    /// <summary>
    /// https://www.codeproject.com/Tips/1072693/CRUD-in-ASP-NET-MVC-with-KnockOut-JS
    /// Basic Crud Operations
    /// </summary>
    public class ProductController : Controller
    {
        static readonly IProductRepository repository = new ProductRepository();

        public ActionResult Products()
        {
            return View();
        }

        public JsonResult GetAllProducts()
        {
            return Json(repository.GetAll(), JsonRequestBehavior.AllowGet);
        }

        public JsonResult AddProduct(Product item)
        {
            item = repository.Add(item);
            writeToQueue(item.Name);
            return Json(item, JsonRequestBehavior.AllowGet);
        }

        private void writeToQueue(string name)
        {
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
            momText.Label = "Record inserted From web";
            momText.Body = $"Record has been inserted into the Proucts table from web with name {name}";
            messageQueue.Send(momText);
        }

        public JsonResult EditProduct(int id, Product product)
        {
            product.Id = id;
            if (repository.Update(product))
            {
                return Json(repository.GetAll(), JsonRequestBehavior.AllowGet);
            }

            return Json(null);
        }

        public JsonResult DeleteProduct(int id)
        {

            if (repository.Delete(id))
            {
                return Json(new { Status = true }, JsonRequestBehavior.AllowGet);
            }

            return Json(new { Status = false }, JsonRequestBehavior.AllowGet);

        }

        public void StartService() {

            try
            {
                //Impersonate obj = new Impersonate();

                //if (obj.impersonateValidUser())
                //{
                    ServiceController svcController = new ServiceController("Service1");
                    if (svcController != null)
                    {
                        try
                        {
                            if (svcController.Status != ServiceControllerStatus.Running &&
                                svcController.Status != ServiceControllerStatus.StartPending)
                                svcController.Start();
                        }
                        catch (Exception Ex) { throw Ex; }
                    }
                //    obj.undoImpersonation();
                //}
            }
            catch (Exception s)
            {

                throw s;
            }
           
            
        }

        public void EndService()
        {
            ServiceController svcController = new ServiceController("Service1");
            if (svcController != null)
            {
                try
                {
                    if (svcController.Status == ServiceControllerStatus.Running &&
                        svcController.CanStop)
                    {
                        svcController.Stop();
                        svcController.WaitForStatus(ServiceControllerStatus.Stopped, TimeSpan.FromSeconds(10));
                    }
                }
                catch (Exception Ex) { throw Ex; }
            }
        }


    }
}
