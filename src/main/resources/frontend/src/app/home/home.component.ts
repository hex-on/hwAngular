import {AfterContentInit, Component, OnChanges, OnInit} from '@angular/core';
import {Employee} from "../models/employee.model";
import {HttpClient} from "@angular/common/http";
import {Globals} from "../globals";
import {EmployeeService} from "../services/employee.service";
import {TaskService} from "../services/task.service";
import {TaskModel} from "../models/task.model";
import {Router} from "@angular/router";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  employee: Employee;
  tasks: Array<TaskModel>;
  tasksNew: Array<TaskModel>;
  tasksInProgress: Array<TaskModel>;
  tasksDone: Array<TaskModel>;
  newTask: TaskModel;

  constructor(private http: HttpClient, public globals: Globals, private employeeService: EmployeeService, private taskService: TaskService, private router: Router) {
    this.employee = new Employee();
    this.newTask = new TaskModel();
    this.tasks = new Array<TaskModel>();
    this.tasksNew = new Array<TaskModel>();
    this.tasksInProgress = new Array<TaskModel>();
    this.tasksDone = new Array<TaskModel>();
  }

  ngOnInit(): void {
    this.employee = this.employeeService.getUser();
    // @ts-ignore
    this.tasks = this.taskService.getTasks(localStorage.getItem("Token"))
      .subscribe(data => {
        this.tasks = data;
        data.forEach(task => {
          if(task.status === 0){
            this.tasksNew.push(task);
          }
          if (task.status === 1){
            this.tasksInProgress.push(task);
          }
          if (task.status === 2){
            this.tasksDone.push(task);
          }
        })
      });
    ;
  }

  updateTask(task: TaskModel) {
    this.taskService.nextStatus(task);
    window.location.reload();
  }

  addNewTask(){
    this.taskService.addTask(this.newTask);
    window.location.reload();
  }
}
