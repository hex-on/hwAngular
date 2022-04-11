import {Component, OnInit} from '@angular/core';
import {Globals} from "../globals";
import {Credentials} from "../models/credentials.model";
import {Employee} from "../models/employee.model";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  public credentials: Credentials;
  public employee: Employee;
  public errorMessage: string;

  constructor(public globals: Globals, private auth: AuthService, private router: Router) {
    this.credentials = new Credentials();
    this.employee = new Employee();
    this.errorMessage = "";
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.auth.login(this.credentials).subscribe(data => {
      this.employee = data;
      localStorage.setItem("Token", this.employee.token);
      this.globals.isLoggedIn = true;
      this.router.navigate(['/home']);
    }, error => {
      const errorAlert = document.getElementById("loginFormErrorAlert");
      this.errorMessage = "Invalid credentials!";
      console.log(error);
      // @ts-ignore
      errorAlert.classList.remove("visually-hidden");
    });
  }
}
