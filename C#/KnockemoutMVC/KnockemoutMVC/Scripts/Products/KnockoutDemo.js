function formatCurrency(value) {
    return "₹ " + value.toFixed(2);
}


//This is the viewmodel for the view 
function ProductViewModel() {


    /////Properties //////
    // Initialize the view-model
    var self = this;
    self.Products = ko.observableArray();
    self.Total = ko.computed(function () {
        var sum = 0;
        $.each(self.Products(), function (k, v) {
            sum += v.Price;
        })
        return sum;
    });

    self.Id = ko.observable("");
    self.Name = ko.observable("");
    self.Price = ko.observable("");
    self.Category = ko.observable("");
    self.CurrentProduct = {
        Id: self.Id,
        Name: self.Name,
        Price: self.Price,
        Category: self.Category
    };
    self.Product = ko.observable(null); //NOTE

    /////Functions //////
    //Get all the products first!
    self.initialize = function () {
        $.ajax({
            url: 'Product/GetAllProducts',
            cache: false,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            data: {},
            success: function (data) {
                self.Products(data); //Put the response in ObservableArray
            },
            error: function myfunction(x, qa) {
                debugger
            }
        });
    }
    //Add New Item
    self.create = function () {
        self.Product(self.CurrentProduct)

        if (self.Product().Name() != "" &&
            self.Product().Price() != "" && self.Product().Category() != "") {
            $.ajax({
                url: 'Product/AddProduct',
                cache: false,
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: ko.toJSON(self.getCurrentProduct()),
                //data: ,
                success: function (data) {
                    self.Products.push(data);
                    self.Name("");
                    self.Price("");
                    self.Category("");
                    self.Product(null);
                }
            }).fail(
                function (xhr, textStatus, err) {
                    alert(err);
                });
        }
        else {
            alert('Please Enter All the Values !!');
        }
    }

    self.getCurrentProduct = function () {
        return {
            Name: self.Name(),
            Price: self.Price(),
            Category: self.Category()
        }
    }
    // Reset product details
    self.reset = function () {
        self.Name("");
        self.Price("");
        self.Category("");
    }
    // edit 
    self.edit = function (Product) { // NOTE
        self.Product(Product);
    }
    //Update 
    self.update = function () {
        var Product = self.Product();
        debugger
        $.ajax({
            url: 'Product/EditProduct',
            cache: false,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: ko.toJSON(Product),
            success: function (data) {
                self.Products.removeAll();
                self.Products(data); //Put the response in ObservableArray
                self.Product(null);
                alert("Record Updated Successfully");
            }
        })
            .fail(
                function (xhr, textStatus, err) {
                    alert(err);
                });
    }
    // Cancel product details
    self.cancel = function () {
        self.Product(null);
    }
    //delete
    self.delete = function (Product) {
        if (confirm(`Are you sure to Delete ${Product.Name} product ??`)) {
            var id = Product.Id;

            $.ajax({
                url: 'Product/DeleteProduct/' + id,
                cache: false,
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: id,
                success: function (data) {
                    self.Products.remove(Product);
                }
            }).fail(
                function (xhr, textStatus, err) {
                    self.status(err);
                });
        }
    }

    self.start = function () {
        $.ajax({
            url: 'Product/StartService',
            //context: document.body
        }).done(function () {
            //$(this).addClass("done");
        });
    }

    self.end = function () {
        $.ajax({
            url: 'Product/EndService/',
            //context: document.body
        }).done(function () {
            //$(this).addClass("done");
        });
    }

}

var viewModel = new ProductViewModel();
viewModel.initialize();
ko.applyBindings(viewModel);

