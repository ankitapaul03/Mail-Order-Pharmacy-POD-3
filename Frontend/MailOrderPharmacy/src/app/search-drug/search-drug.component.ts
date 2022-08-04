import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DrugDetails } from '../drug-details';
import { DrugLocationDetails } from '../drug-location-details';
import { DrugsService } from '../drugs.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-search-drug',
  templateUrl: './search-drug.component.html',
  styleUrls: ['./search-drug.component.css']
})
export class SearchDrugComponent implements OnInit {
  drugLocation: DrugLocationDetails[] = [];
  constructor(private route: ActivatedRoute, private eservice: DrugsService, private router:Router,private authService:AuthService) { 
   
    if(!this.authService.isLoggedIn()){
      this.router.navigate([""]);
    } 
  }
  token!: string
  date!: Date
  drugs: DrugDetails = new DrugDetails('', '', '', this.date, this.date, this.drugLocation)
  drug: DrugDetails = new DrugDetails('', '', '', this.date, this.date, this.drugLocation)
  fetched: boolean = false
  errorMsg: string = ''
  ngOnInit(): void {

    this.fetched=false
  }

  handleSubmitforID() {
    this.errorMsg = ''
    console.log('search by ID');
    console.log(this.drugs.drugId);
    this.getDrugById()
  }

  getDrugById() {
    this.eservice.getDrugById(this.drugs.drugId).subscribe(data => {
      this.fetched = true
      this.drugs = data

      console.log(this.drugs.drugName)
    }, error => {
      this.fetched = false
      this.errorMsg = "Drug with given details Not found"
    })
  }


  handleSubmitforName() {
    this.errorMsg = ''
    console.log('search by Name');
    console.log(this.drug.drugName);
    this.getDrugByName()


  }
  getDrugByName() {
    this.eservice.getDrugByName(this.drug.drugName).subscribe(data => {
      this.fetched = true
      this.drugs = data


    }, error => {
      this.fetched = false
      this.errorMsg = "Drug with given details Not found"
    })
  }

}


