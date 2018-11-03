

var EmployeeInformationSystem = {};
//The Model with the validation Rules
EmployeeInformationSystem.EmployeeViewModel = function (emp) {
    var EmployeeInfoModel = ko.validatedObservable({
        EmpNo: ko.observable(emp.EmpNo),
        EmpName: ko.observable(emp.EmpName).extend({ required: true }),
        Salary: ko.observable(emp.Salary).extend({ required: true }),
        DeptName: ko.observable(emp.DeptName).extend({ required: true }),
        Designation: ko.observable(emp.Designation).extend({ required: true })
    });

    return EmployeeInfoModel;
};


// Bind the EmployeeInfo
EmployeeInformationSystem.bindModel = function (emp) {

    // Create the view model
    EmployeeInformationSystem.EmpViewModel =
        EmployeeInformationSystem.EmployeeViewModel(emp);

    //The Validation initialization
    ko.validation.init({ messagesOnModified: false, errorClass: 'errorStyle', insertMessages: true });
    ko.applyBindings(this.EmpViewModel);
};

//Save the Information
EmployeeInformationSystem.saveEmployee = function () {
    if (EmployeeInformationSystem.EmpViewModel.isValid()) {
        $.ajax({
            url: "/api/EmployeeInfoAPI",
            type: "POST",
            data: ko.toJSON(this),
            datatype: "json",
            contentType: 'application/json'
        }).done(function (res) {
            alert("Record Added Successfully" + res.EmpNo);
        }).error(function (err) {
            alert("Error " + err.status);
        });
    } else {
        alert("Please enter the valid data");
    }
};



$(document).ready(function () {
    EmployeeInformationSystem.bindModel({ EmpNo: 0, EmpName: "", Salary: 0, DeptName: "", Designation: "" });
});