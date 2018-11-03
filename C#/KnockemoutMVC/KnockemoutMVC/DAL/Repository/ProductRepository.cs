using KnockemoutMVC.DAL.Interface;
using KnockemoutMVC.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace KnockemoutMVC.DAL.Repository
{
    public class ProductRepository : IProductRepository
    {
        LocalhostEntities ProductDB = new LocalhostEntities();
        public Product Add(Product item)
        {
            if (item == null)
            {
                throw new ArgumentNullException("item");
            }

            // TO DO : Code to save record into database
            ProductDB.Products.Add(item);
            ProductDB.SaveChanges();
            return item;
        }

        public bool Delete(int id)
        {
            // TO DO : Code to remove the records from database

            Product products = ProductDB.Products.Find(id);
            ProductDB.Products.Remove(products);
            ProductDB.SaveChanges();

            return true;
        }

        public Product Get(int id)
        {         
            return ProductDB.Products.Find(id);
        }

        public IEnumerable<Product> GetAll()
        {
            return ProductDB.Products.ToList();
        }

        public bool Update(Product item)
        {
            if (item == null)
            {
                throw new ArgumentNullException("item");
            }

            // TO DO : Code to update record into database

            var products = ProductDB.Products.Single(a => a.Id == item.Id);
            products.Name = item.Name;
            products.Category = item.Category;
            products.Price = item.Price;
            ProductDB.SaveChanges();
            return true;
        }
    }
}