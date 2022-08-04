import { DrugLocationDetails } from "./drug-location-details";

export class DrugDetails {
   
  
    public drugId!:string;
    public drugName!:string;
    public manufacturer!:string;
    public manufactureDate!:Date;
    public expiryDate!:Date;
    public druglocationQuantities!:DrugLocationDetails[]
    constructor(drugId:string,drugName:string,manufacturer:string,manufactureDate:Date,expiryDate:Date,druglocationQuantities:DrugLocationDetails[])
    {

    }
    
}
