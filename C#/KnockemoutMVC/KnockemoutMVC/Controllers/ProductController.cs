using KnockemoutMVC.DAL.Interface;
using KnockemoutMVC.DAL.Repository;
using KnockemoutMVC.Database;
using System;
using System.Collections.Generic;
using System.Linq;
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
            return Json(item, JsonRequestBehavior.AllowGet);
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
    }
}
