import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionDetails } from '../prescription-details';
import { SubscribeService } from '../subscribe.service';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {
  msg !: string;
  todayDate: Date=new Date();
  prescriptionDetails : PrescriptionDetails={ 'prescriptionId' : 1,
    'memberId' : 'admin',
  'memberLocation' : '',
  'policyNumber' : '',
  'insuranceProvider' : '',
  'prescriptionDate' : this.todayDate,
  'drugName' : '',
  'dosageDefinition' : 'morning',
  'quantity' : 0,
  'courseDuration' : 0,
  'doctorName' : '',
};
  constructor(private route: ActivatedRoute, private service: SubscribeService, private router:Router,private authService:AuthService) { 
   
    if(!this.authService.isLoggedIn()){
      this.router.navigate([""]);
    } 
   }

  ngOnInit(): void {

  }
  public cities:string[]=['Bangalore','Chennai','Hyderabad','Pune']
  public meds:string[]=['Dolo-500','Cephalexin','Aspirin','Azithromycin','Ibuprofen']
  public insurances:string[]=['Aditya Birla','Bajaj Allianz','Bharti AXA','IFFCO Tokio']
handleSubmit()
{
  console.log(this.prescriptionDetails);
  this.service.savePrescription(this.prescriptionDetails).subscribe(data=>
    {
     // this.msg=data
      console.log('in data')
     
    },error => {
      this.msg = "You have successfully subscribed to "+this.prescriptionDetails.drugName;
      //this.router.navigate(['subscriptions'])
    })
}

}