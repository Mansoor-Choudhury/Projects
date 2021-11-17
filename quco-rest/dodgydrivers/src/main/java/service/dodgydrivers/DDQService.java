package service.dodgydrivers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.core.AbstractQuotationService;
import service.core.ClientInfo;
import service.core.Quotation;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of Quotation Service for Dodgy Drivers Insurance Company
 *
 * @author Rem
 *
 */
@RestController
public class DDQService extends AbstractQuotationService {
    // All references are to be prefixed with an DD (e.g. DD001000)
    public static final String PREFIX = "DD";
    public static final String COMPANY = "Dodgy Drivers Corp.";
    private Map<String, Quotation> quotations = new HashMap<>();

    /**
     * Post method to generate the quotation for Dodgygrivers service
     *
     * @param info - client info
     * @return - quotation
     */
    @RequestMapping(value="/quotations",method= RequestMethod.POST)
    public ResponseEntity<Quotation> createQuotation(@RequestBody ClientInfo info) {
        try {
            Quotation quotation = generateQuotation(info);
            //storing quotation reference in quotations map
            quotations.put(quotation.getReference(), quotation);
            //set url path for retrieval of quotations with reference number
            String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                    build().toUriString() + "/quotations/" + quotation.getReference();
            HttpHeaders headers = new HttpHeaders();
            //set path in header which is sent back to the post method call
            headers.setLocation(new URI(path));
            return new ResponseEntity<>(quotation, headers, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the quotation for the reference id
     *
     * @param reference - reference number
     * @return - quotation
     */
    @RequestMapping(value="/quotations/{reference}",method=RequestMethod.GET)
    public Quotation getResource(@PathVariable("reference") String reference) {
        Quotation quotation = quotations.get(reference);
        //throw no such quotation exception if quotation is null for a specific reference
        if (quotation == null) throw new NoSuchQuotationException();
        return quotation;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class NoSuchQuotationException extends RuntimeException {
        static final long serialVersionUID = -6516152229878843037L;
    }
    /**
     * Quote generation:
     * 30% discount for being male
     * 2% discount per year over 60
     * 20% discount for less than 3 penalty points
     * 50% penalty (i.e. reduction in discount) for more than 60 penalty points
     *
     * @param info - client info object
     * @return - quotation
     */
    public Quotation generateQuotation(ClientInfo info) {
        // Create an initial quotation between 800 and 1000
        double price = generatePrice(800, 200);

        // 5% discount per penalty point (3 points required for qualification)
        int discount = (info.getPoints() > 3) ? 5*info.getPoints():-50;

        // Add a no claims discount
        discount += getNoClaimsDiscount(info);

        // Generate the quotation and send it back
        return new Quotation(COMPANY, generateReference(PREFIX), (price * (100-discount)) / 100);
    }

    /**
     * Returns discount if no claims made
     *
     * @param info - clientInfo object
     * @return - discount
     */
    private int getNoClaimsDiscount(ClientInfo info) {
        return 10*info.getNoClaims();
    }
}
