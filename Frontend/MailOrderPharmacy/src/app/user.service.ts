import { Injectable } from '@angular/core';
import { HttpClient,HttpClientModule,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginUrl: string;
  private validateUrl: string;

  constructor(private http:HttpClient) {
    this.loginUrl='http://localhost:8090/authapp/login';
    this.validateUrl='http://localhost:8090/authapp/validate';
   }
   generateToken(user : User): Observable<Object>{
    return this.http.post(this.loginUrl, user,{responseType:'text' as 'json'});
   }
   validateToken(token: string){
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    }
    return this.http.get(`${this.validateUrl}`, header);

  }
  loginMember(user: User) {
    return this.http.post(`${this.loginUrl}`, user);
  }
}