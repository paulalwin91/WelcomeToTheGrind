function formatCurrency(value) {
    return "₹ " + value.toFixed(2);
}

function ProductViewModel() {

}

var viewModel = new ProductViewModel();
ko.applyBindings(viewModel);