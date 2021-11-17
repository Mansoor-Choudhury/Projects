package service.core;
import java.util.ArrayList;

/**
 * Client application class store client info and quotation list
 */
public class ClientApplication {

    public ClientApplication(){
    }
    public ClientApplication(ClientInfo info, ArrayList<Quotation> quotationList){
        this.info = info;
        this.quotationList = quotationList;
    }

    private ClientInfo info;
    private ArrayList<Quotation> quotationList;

    public ArrayList<Quotation> getQuotationList(){
        return quotationList;
    }

    public void setQuotationList(ArrayList<Quotation> quotationList){
        this.quotationList = quotationList;
    }

    public void addQuotationToList(Quotation quotation){
        quotationList.add(quotation);
    }

    public ClientInfo getInfo() {
        return info;
    }
    public void setInfo(ClientInfo info){
        this.info = info;
    }
}