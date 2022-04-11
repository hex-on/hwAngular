import {Injectable} from '@angular/core';
import {Employee} from "./models/employee.model";
import {AuthService} from "./services/auth.service";

@Injectable()
export class Globals {
  readonly baseUrl: string = 'http://localhost:8080';
  readonly title: string = 'HomeworkTwo';
  public isLoggedIn: boolean = localStorage.getItem("Token") !== null ? true : false;
}
