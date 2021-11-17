package service.broker;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Broker class to send client info to respective quotation services and get list of quotations
 *
 * @Author: Mansoor
 *
 */
@RestController
public class Broker {
    private static Long uniqueApplicationNumber = 1000L;
    private static HashMap<Long, ClientApplication> cache = new HashMap<>();

    /**
     * gets all the quotation list for post method
     *
     * @param info - client info
     * @return - client application with quotations
     */
    @RequestMapping(value="/applications",method= RequestMethod.POST)
    public ResponseEntity<ClientApplication> brokerService(@RequestBody ClientInfo info) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<ClientInfo> request = new HttpEntity<>(info);
            ClientApplication clientApplication = new ClientApplication(info,new ArrayList<>());
            //reads all client urls and adds the quotation to list
            for(String url : getURLs()) {
                //post for quotation class as per url using rest template
                Quotation quotation =
                        restTemplate.postForObject(url,
                                request, Quotation.class);
                clientApplication.addQuotationToList(quotation);
            }
            //adds client application to the unique application number
            cache.put(++uniqueApplicationNumber,clientApplication);
            return new ResponseEntity<>(cache.get(uniqueApplicationNumber),new HttpHeaders(), HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the client application for the application number
     *
     * @param uniqueApplicationNumber - unique application number
     * @return - client application
     */
    @RequestMapping(value="/applications/{application-number}",method=RequestMethod.GET)
    public ResponseEntity<ClientApplication> getApplications(@PathVariable("application-number") Long uniqueApplicationNumber) {
        return new ResponseEntity<>(cache.get(uniqueApplicationNumber),new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * get method to return list of all client info and quotation list for respective client application
     *
     * @return list all client applications
     */
    @RequestMapping(value="/applications",method=RequestMethod.GET)
    public ResponseEntity<ArrayList<ClientApplication>> getClientApplications() {
        return new ResponseEntity<>(new ArrayList(cache.values()), new HttpHeaders(), HttpStatus.FOUND);
    }


    private String[] getURLs() {
        String[] URLs = new String[3];
        URLs[0] = "http://auldfellas:8081/quotations";
        URLs[1] = "http://dodgydrivers:8082/quotations";
        URLs[2] = "http://girlpower:8083/quotations";
        return URLs;
    }
}