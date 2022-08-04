export class RefillStatus{
  
    public refillID!:number;
    public subID!:number;
    public memberID!:String;
    public payStatus!:boolean;
    public refillDate!:Date;
    public quantity!:number;
  
    constructor(refillID:number,subID:number,memberID:string,payStatus:boolean,refillDate:Date,quantity:number){}
  }