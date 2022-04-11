import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {EmployeeService} from "../services/employee.service";
import {Employee} from "../models/employee.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employeelist',
  templateUrl: './employeelist.component.html',
  styleUrls: ['./employeelist.component.css']
})
export class EmployeelistComponent implements OnInit {
  employeeList: Array<Employee>;
  searchInput: string;
  loggedInUser:Employee;

  constructor(private employeeService: EmployeeService) {
    this.loggedInUser = new Employee();
    this.employeeList = [];
    this.searchInput = "";
  }

  ngOnInit(): void {
    this.employeeList = this.employeeService.getEmployeeList();
    this.loggedInUser = this.employeeService.getUser()
  }


  inputUpdate(employee: Employee) {
    this.employeeService.updateEmployee(employee);
    const alert = document.getElementById("employeeUpdatedSuccessAlert");
    if (alert) {
      alert.style.display = "block";
      alert.clientHeight;
      alert.style.opacity = String(1);
      setTimeout(() => {
        alert.style.opacity = String(0);
        alert.clientHeight;
        alert.style.display = "none";
      }, 5000);
    }
  }
}
