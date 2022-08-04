import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RefillDue } from '../refill-due';
import { RefillStatusService } from '../refill-status.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-refill-due-date',
  templateUrl: './refill-due-date.component.html',
  styleUrls: ['./refill-due-date.component.css']
})
export class RefillDueDateComponent implements OnInit {

  constructor(private rsevice:RefillStatusService,private route:Router,private authService:AuthService) {
    if(!this.authService.isLoggedIn()){
      this.route.navigate([""]);
    }
   }

  //due:RefillDue= new RefillDue(0,"",0,0,0)
  due:RefillDue[]=[]
  date:number = 0
  dates:string=''
  fetched: boolean = false
  errorMsg:string=''
  
  ngOnInit(): void {
    this.fetched=false
  }

  handleSubmit(){
    this.dates=(this.date+'').replace(/[^0-9]/g,'')
    this.date=parseInt(this.dates)
    console.log(this.date)
    console.log(typeof this.date)
    this.rsevice.getRefillDue(this.date).subscribe(
      (      data: RefillDue[]) =>{
        
        
        this.fetched = true
        this.due=data
        this.errorMsg=" "
        if(this.due.length==0)
        {
          console.log(this.due)
          this.fetched = false
        this.errorMsg = "No Dues found"
        }

        console.log(this.due)
      }, error => {
        this.fetched = false
        this.errorMsg = "No Dues found"
      })
      
  }
}
