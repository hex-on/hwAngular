import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../globals";
import {Employee} from "../models/employee.model";
import {Observable} from "rxjs";
import {error} from "@angular/compiler/src/util";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient, private globals: Globals,) {
  }

  getUser(): Employee {
    let newEmployee = new Employee();
    let token = localStorage.getItem("Token");
    this.http.get<Employee>(`${this.globals.baseUrl}/employees/gettoken/` + token)
      .subscribe(employee => {
        newEmployee.id = employee.id;
        newEmployee.lastName = employee.lastName;
        newEmployee.firstName = employee.firstName;
        newEmployee.email = employee.email;
        newEmployee.salary = employee.salary;
        newEmployee.isAdmin = employee.isAdmin;
      });
    return newEmployee;
  }

  getEmployeeList(): Array<Employee> {
    let list: Array<Employee> = new Array<Employee>();
    this.http.get<Array<Employee>>(`${this.globals.baseUrl}/employees/all/`)
      .subscribe(data => {
        data.forEach(val => list.push(val))
      });
    return list;
  }

  updateEmployee(employee: Employee) {
    this.http.put<Employee>(`${this.globals.baseUrl}/employees/update/` + employee.id, employee)
      .subscribe();
  }

  getEmployeesByName(): Array<Employee> {
    let list: Array<Employee> = new Array<Employee>();
    return list;
  }
}
