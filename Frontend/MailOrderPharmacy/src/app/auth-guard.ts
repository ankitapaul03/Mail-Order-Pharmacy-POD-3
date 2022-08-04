import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";

export class AuthGuard {
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if(this.serive.isLoggedIn()){
          return true;
        }else{
          this.router.navigate(['/']);
        }
        return false;
      }
      constructor(private router: Router, private serive :AuthService){
    
      
    }
}