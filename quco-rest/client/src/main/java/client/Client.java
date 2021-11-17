package client;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.CoreConstants;
import service.core.Quotation;

import java.text.NumberFormat;

/**
 * Client class to send cleint info and get all the applications
 *
 * @Author: Mansoor
 *
 */
public class Client {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        for(ClientInfo info : clients) {
            HttpEntity<ClientInfo> request = new HttpEntity<>(info);
            //post for client info using rest template and gets client application having quotation list
            ClientApplication clientApplication =
                    restTemplate.postForObject("http://localhost:8080/applications",
                            request, ClientApplication.class);
            displayProfile(info);
            //displays quotation list
            if(clientApplication != null) {
                for (Quotation quotation : clientApplication.getQuotationList()) {
                    displayQuotation(quotation);
                }
            }
        }
    }

    /**
     * Display the client info nicely.
     *
     * @param info - clientInfo object
     */
    public static void displayProfile(ClientInfo info) {
        System.out.println("|=================================================================================================================|");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println(
                "| Name: " + String.format("%1$-29s", info.getName()) +
                        " | Gender: " + String.format("%1$-27s", (info.getGender()== CoreConstants.MALE ?"Male":"Female")) +
                        " | Age: " + String.format("%1$-30s", info.getAge())+" |");
        System.out.println(
                "| License Number: " + String.format("%1$-19s", info.getLicenseNumber()) +
                        " | No Claims: " + String.format("%1$-24s", info.getNoClaims()+" years") +
                        " | Penalty Points: " + String.format("%1$-19s", info.getPoints())+" |");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("|=================================================================================================================|");
    }

    /**
     * Prints the quotation received from quotation companies
     *
     * @param quotation - quotation object
     */
    public static void displayQuotation(Quotation quotation) {
        System.out.println(
                "| Company: " + String.format("%1$-26s", quotation.getCompany()) +
                        " | Reference: " + String.format("%1$-24s", quotation.getReference()) +
                        " | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.getPrice()))+" |");
        System.out.println("|=================================================================================================================|");
    }

    /**
     * List fo clients for which quotation has to be generated
     */
    public static final ClientInfo[] clients = {
            new ClientInfo("Niki Collier", CoreConstants.FEMALE, 43, 0, 5, "PQR254/1"),
            new ClientInfo("Old Geeza", CoreConstants.MALE, 65, 0, 2, "ABC123/4"),
            new ClientInfo("Hannah Montana", CoreConstants.FEMALE, 16, 10, 0, "HMA304/9"),
            new ClientInfo("Rem Collier", CoreConstants.MALE, 44, 5, 3, "COL123/3"),
            new ClientInfo("Jim Quinn", CoreConstants.MALE, 55, 4, 7, "QUN987/4"),
            new ClientInfo("Donald Duck", CoreConstants.MALE, 35, 5, 2, "XYZ567/9")
    };
}
