var ViewModel = function () {
    var self = this;
    self.books = ko.observableArray();
    self.error = ko.observable();
    self.detail = ko.observable();
    self.authors = ko.observableArray();

    self.newBookVM = ko.observable({
        Author: ko.observable(),
        Genre: ko.observable(),
        Price: ko.observable(),
        Title: ko.observable(),
        Year: ko.observable()
    })


    var booksUri = '/api/books/';
    var authorsUri = '/api/authors/';



    function ajaxHelper(uri, method, data) {
        self.error(''); // Clear error message
        return $.ajax({
            type: method,
            url: uri,
            dataType: 'json',
            contentType: 'application/json',
            data: data ? JSON.stringify(data) : null
        }).fail(function (jqXHR, textStatus, errorThrown) {
            self.error(errorThrown);
        });
    }

    function getAllBooks() {
        ajaxHelper(booksUri, 'GET').done(function (data) {
            self.books(data);
        });
    }

    function getAuthors() {
        ajaxHelper(authorsUri, 'GET').done(function (data) {
            self.authors(data);
        });
    }


    self.getBookDetail = function (item) {
        ajaxHelper(booksUri + item.Id, 'GET').done(function (data) {
            self.detail(data);
        });
    }

    self.clearNewBook = function () {
        self.newBookVM().Author(null);
        self.newBookVM().Genre(null);
        self.newBookVM().Price(null);
        self.newBookVM().Title(null);
        self.newBookVM().Year(null);
    }

    self.addBook = function () {
        var book = {
            AuthorId: self.newBookVM().Author().Id,
            Genre: self.newBookVM().Genre(),
            Price: self.newBookVM().Price(),
            Title: self.newBookVM().Title(),
            Year: self.newBookVM().Year()
        };

        ajaxHelper(booksUri, 'POST', book).done(function (item) {
            self.books.push(item);
            self.clearNewBook();
        });
    }




    // Fetch the initial data.
    getAllBooks();
    // Fetch these mofos for the add books dropbox
    getAuthors();
};
var model = new ViewModel()
ko.applyBindings(model);