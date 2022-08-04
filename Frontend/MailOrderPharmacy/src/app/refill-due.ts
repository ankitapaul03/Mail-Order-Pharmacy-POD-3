export class RefillDue{
  
    public id!:number;
    public subID!:number;
    public memberID!:String;
    public refillTime!:number
    public quantity!:number;
  
    constructor(id:number,memberID:string,refillTime:number,quantity:number,subID:number){}
  }

  /*
[
  id: 1
memberID: "admin"
quantity: 1
refillTime: 2

]

  */