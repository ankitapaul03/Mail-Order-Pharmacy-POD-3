import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllDrugsComponent } from './all-drugs/all-drugs.component';
import { SearchDrugComponent } from './search-drug/search-drug.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { RefillComponent } from './refill/refill.component';
import { RefillStatusComponent } from './refill-status/refill-status.component';
import { HomeComponent } from './home/home.component';
import { LoginModuleComponent } from './login-module/login-module.component';
import { RefillDueDateComponent } from './refill-due-date/refill-due-date.component';


const routes: Routes = [
  {path:'drugs',component:AllDrugsComponent},
  {path:'search',component:SearchDrugComponent},
  {path:'subscriptions',component:SubscriptionsComponent},
  {path:'subscribe',component:SubscribeComponent},
  {path:'refill',component:RefillComponent},
  {path:'refillStatus',component:RefillStatusComponent},
  {path:'home',component:HomeComponent},
  {path:'',redirectTo:'/loginModule',pathMatch:'full'},
  {path : 'loginModule',component:LoginModuleComponent},
  {path:'refillStatus',component:RefillStatusComponent},
  {path:'refilldue',component:RefillDueDateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
