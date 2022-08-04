import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { RefillStatus } from "./refill-status";
import { Refill } from "./refill";
import { RefillDue } from "./refill-due";


@Injectable({
  providedIn: 'root'
})

export class RefillStatusService {
  constructor(private http: HttpClient) { }

  baseUrl: string = "http://localhost:8454/refillapp"

  //making call to api,getting customer by subID
  getAllRefillStatus(id:number): Observable<RefillStatus[]> {
    let token=localStorage.getItem("token")
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      },
    };
    return this.http.get<RefillStatus[]>(`${this.baseUrl}/viewRefillStatus/${localStorage.getItem('subID')}`,header)
  }

  //making call to api,saving employee details in database
  saveRefill(order:Refill){
    let token=localStorage.getItem("token")
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      },
    };
    console.log(order.quantity,order.memberID,order.policyID,order.subID)
    return this.http.post(`${this.baseUrl}/requestAdhocRefill`,order,header)
}

getRefillDue(date:number){
  let token=localStorage.getItem("token")
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      },
    };
    return this.http.get<RefillDue[]>(`${this.baseUrl}/getRefillDuesAsOfDate/admin/${date}`,header)
}

}