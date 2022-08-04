export class PrescriptionDetails {

    public prescriptionId !:  number;
    public memberId !: string;
	public memberLocation !: string;
	public policyNumber !:string;
	public insuranceProvider !: string;
	public prescriptionDate !: Date;
	public drugName !: string;
	public dosageDefinition !: string;
	public quantity !: number;
	public courseDuration !: number; 
	public doctorName !: string;
    constructor(){}
}
