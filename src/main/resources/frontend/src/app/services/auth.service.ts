import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Credentials} from "../models/credentials.model";
import {Globals} from "../globals";
import {Observable} from "rxjs";
import {Employee} from "../models/employee.model";
import {Md5} from "ts-md5";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private globals: Globals) {
  }

  login(credentials: Credentials): Observable<Employee> {
    credentials.password = Md5.hashAsciiStr(credentials.password);
    return this.http.post<Employee>(`${this.globals.baseUrl}/employees/login`, credentials);
  }

}
