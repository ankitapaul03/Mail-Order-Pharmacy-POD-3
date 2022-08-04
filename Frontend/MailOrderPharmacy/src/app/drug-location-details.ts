export class DrugLocationDetails {

    
    constructor(public serialId:string,public location:string,public quantity:Number,public drugID:string)
    {

    }

}
/**
 * private String serialId;
    private String location;
    private int quantity;
    @ManyToOne(cascade = { CascadeType.ALL })
    @JsonIgnore
    @JoinColumn(name = "drugId")
    private DrugDetails drugDetails;
 */