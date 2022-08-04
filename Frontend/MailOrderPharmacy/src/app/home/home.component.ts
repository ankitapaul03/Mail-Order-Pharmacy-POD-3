import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor( private route:Router,private authService:AuthService) { 
   
    // if(!this.authService.isLoggedIn()){
    //   this.route.navigate([""]);
    // }
  }

  ngOnInit(): void {
  }

}
