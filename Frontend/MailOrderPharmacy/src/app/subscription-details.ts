export class SubscriptionDetails {
/**
 * this.subscriptionId = subscriptionId;
		this.prescriptionId = prescriptionId;
		this.drugName = drugName;
		this.refillCycle = refillCycle;
		this.quantity = quantity;
		this.memberId = memberId;
		this.subscriptionDate = subscriptionDate;
		this.memberLocation = memberLocation;
		this.subscriptionStatus = subscriptionStatus;
 */

        constructor(public subscriptionId:string,public prescriptionId:string,
            public drugName:string, public refillCycle:number,public quantity:number,
            public memberId:string,public subscriptionDate:Date,public memberLocation:string,public subscriptionStatus:string)
        {

        }

}
