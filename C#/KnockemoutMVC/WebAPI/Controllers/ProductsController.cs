namespace WebAPI.Controllers
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Web.Http;
    using WebAPI.Models;

    /// <summary>
    /// Defines the <see cref="ProductsController" />
    /// </summary>
    public class ProductsController : ApiController
    {
        /// <summary>
        /// Defines the products
        /// </summary>
        internal Product[] products = new Product[]
       {
            new Product { Id = 1, Name = "Tomato Soup", Category = "Groceries", Price = 1 },
            new Product { Id = 2, Name = "Yo-yo", Category = "Toys", Price = 3.75M },
            new Product { Id = 3, Name = "Hammer", Category = "Hardware", Price = 16.99M }
       };

        public IEnumerable<Product> GetAllProducts()
        {
            return products;
        }

        public IHttpActionResult GetProduct(int id)
        {
            var product = products.FirstOrDefault((p) => p.Id == id);
            if (product == null)
            {
                return NotFound();
            }
            return Ok(product);
        }
    }
}
