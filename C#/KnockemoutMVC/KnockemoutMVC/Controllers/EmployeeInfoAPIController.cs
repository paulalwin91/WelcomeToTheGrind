namespace KnockemoutMVC.API
{
    using KnockemoutMVC.Database;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    using System.Linq;
    using System.Net;
    using System.Web.Http;
    using System.Web.Http.Description;

    /// <summary>
    /// DOES NOT WORK!!
    /// Defines the <see cref="EmployeeInfoAPIController" />
    /// </summary>
    public class EmployeeInfoAPIController : ApiController
    {
        /// <summary>
        /// Defines the db 
        /// </summary>
        private LocalhostEntities db = new LocalhostEntities();

        /// <summary>
        /// The GetEmployeeInfoes
        /// </summary>
        /// <returns>The <see cref="IQueryable{EmployeeInfo}"/></returns>
        [Route("api/EmployeeInfoAPI/GetEmployeeInfoes")]
        public IQueryable<EmployeeInfo> GetEmployeeInfoes()
        {
            return db.EmployeeInfoes;
        }

        /// <summary>
        /// The GetEmployeeInfo
        /// </summary>
        /// <param name="id">The id<see cref="int"/></param>
        /// <returns>The <see cref="IHttpActionResult"/></returns>
        [ResponseType(typeof(EmployeeInfo))]
        public IHttpActionResult GetEmployeeInfo(int id)
        {
            EmployeeInfo employeeInfo = db.EmployeeInfoes.Find(id);
            if (employeeInfo == null)
            {
                return NotFound();
            }

            return Ok(employeeInfo);
        }

        /// <summary>
        /// The PutEmployeeInfo
        /// </summary>
        /// <param name="id">The id<see cref="int"/></param>
        /// <param name="employeeInfo">The employeeInfo<see cref="EmployeeInfo"/></param>
        /// <returns>The <see cref="IHttpActionResult"/></returns>
        [ResponseType(typeof(void))]
        public IHttpActionResult PutEmployeeInfo(int id, EmployeeInfo employeeInfo)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != employeeInfo.EmpNo)
            {
                return BadRequest();
            }

            db.Entry(employeeInfo).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EmployeeInfoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        /// <summary>
        /// The PostEmployeeInfo
        /// </summary>
        /// <param name="employeeInfo">The employeeInfo<see cref="EmployeeInfo"/></param>
        /// <returns>The <see cref="IHttpActionResult"/></returns>
        [ResponseType(typeof(EmployeeInfo))]
        public IHttpActionResult PostEmployeeInfo(EmployeeInfo employeeInfo)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.EmployeeInfoes.Add(employeeInfo);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = employeeInfo.EmpNo }, employeeInfo);
        }

        /// <summary>
        /// The DeleteEmployeeInfo
        /// </summary>
        /// <param name="id">The id<see cref="int"/></param>
        /// <returns>The <see cref="IHttpActionResult"/></returns>
        [ResponseType(typeof(EmployeeInfo))]
        public IHttpActionResult DeleteEmployeeInfo(int id)
        {
            EmployeeInfo employeeInfo = db.EmployeeInfoes.Find(id);
            if (employeeInfo == null)
            {
                return NotFound();
            }

            db.EmployeeInfoes.Remove(employeeInfo);
            db.SaveChanges();

            return Ok(employeeInfo);
        }

        /// <summary>
        /// The Dispose
        /// </summary>
        /// <param name="disposing">The disposing<see cref="bool"/></param>
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        /// <summary>
        /// The EmployeeInfoExists
        /// </summary>
        /// <param name="id">The id<see cref="int"/></param>
        /// <returns>The <see cref="bool"/></returns>
        private bool EmployeeInfoExists(int id)
        {
            return db.EmployeeInfoes.Count(e => e.EmpNo == id) > 0;
        }
    }
}
