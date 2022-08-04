import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { AuthService } from './auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MailOrderPharmacy';

constructor(private router:Router,public serve:AuthService)
{
  
}



  logout() {
    localStorage.removeItem("token");

    console.log('removed')
    this.router.navigate(['/']);
  }
  



}



