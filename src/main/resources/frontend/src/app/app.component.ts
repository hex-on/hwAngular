import {Component, OnChanges, OnInit} from '@angular/core';
import {Globals} from "./globals";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnChanges, OnInit {

  constructor(public globals: Globals, private router: Router) {
  }


  ngOnChanges() {
    if (this.globals.isLoggedIn) {
      this.router.navigate(['/home'])
    } else {
      this.router.navigate(['/login'])
    }
  }

  ngOnInit() {
    if (this.globals.isLoggedIn) {
      this.router.navigate(['/home'])
    } else {
      this.router.navigate(['/login'])
    }
  }

  logInOutBtn() {
    if (this.globals.isLoggedIn) {
      this.globals.isLoggedIn = false;
      localStorage.removeItem("Token");
    }
    this.router.navigate(['/login'])
  }
}
