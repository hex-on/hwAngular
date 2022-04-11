import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Globals} from "../globals";
import {TaskModel} from "../models/task.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private globals: Globals) {
  }


  getTasks(token: string): Observable<Array<TaskModel>> {
    let list: Array<TaskModel> = new Array<TaskModel>();
    return this.http.get<Array<TaskModel>>(`${this.globals.baseUrl}/tasks/all/` + token);
    // return list;
  }

  nextStatus(task: TaskModel) {
    task.status++;
    this.http.put(`${this.globals.baseUrl}/tasks/update/` + task.id, task).subscribe();
  }

  addTask(newTask: TaskModel) {
    let token = localStorage.getItem("Token");
    this.http.post(`${this.globals.baseUrl}/tasks/add/` + token, newTask).subscribe();
  }
}
