import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DrugDetails } from './drug-details';


@Injectable({
  providedIn: 'root'
})
export class DrugsService {

  constructor(private http:HttpClient) { }

  baseUrl:string='http://localhost:8081/drugdetailapp'

  getAllDrugs():Observable<DrugDetails[]>
  {
      return this.http.get<DrugDetails[]>(`${this.baseUrl}/getAllDrugs`);
  }

  
/**
 * getTransactionsByAccId(accId:any,token:any){
    const header = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    return this.http.get(this.getTransactionsUrl + accId, header);
   }
  }
 */
  getDrugById(id:string)
  {
    let token=localStorage.getItem("token")
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      },
    };
    return this.http.get<DrugDetails>(`${this.baseUrl}/searchDrugsById/${id}`,header);
  }

  getDrugByName(name:string)
  {
    let token=localStorage.getItem("token")
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      },
    };
    return this.http.get<DrugDetails>(`${this.baseUrl}/searchDrugsByName/${name}`,header);
  }
 

}
