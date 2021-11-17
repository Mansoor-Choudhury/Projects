package service.core;

/**
 * Class to store the quotations returned by the quotation services
 *
 * @author Mansoor
 *
 */
public class Quotation {
    public Quotation(){}
    public Quotation(String company, String reference, double price) {
        this.company = company;
        this.reference = reference;
        this.price = price;
    }

    private String company;
    private String reference;
    private double price;

    public void setCompany(String company){
        this.company = company;
    }
    public String getCompany(){
        return company;
    }

    public void setReference(String reference){
        this.reference = reference;
    }
    public String getReference(){
        return reference;
    }

    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }
}
