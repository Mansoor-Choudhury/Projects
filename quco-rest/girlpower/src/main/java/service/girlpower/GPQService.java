package service.girlpower;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.core.AbstractQuotationService;
import service.core.ClientInfo;
import service.core.CoreConstants;
import service.core.Quotation;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Girlpower quotation generation class
 *
 * @Author: Mansoor
 *
 */
@RestController
public class GPQService extends AbstractQuotationService {
    private static final String PREFIX = "GP";
    private static final String COMPANY = "Girl Power Inc.";
    private Map<String, Quotation> quotations = new HashMap<>();

    /**
     * Calls getQuotation core method to calculate quotation for Girlpower
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
     * 50% discount for being female
     * 20% discount for no penalty points
     * 15% discount for < 3 penalty points
     * no discount for 3-5 penalty points
     * 100% penalty for > 5 penalty points
     * 5% discount per year no claims
     *
     * @param info- claiminfo object
     */
    public Quotation generateQuotation(ClientInfo info) {
        // Create an initial quotation between 600 and 1000
        double price = generatePrice(600, 400);

        // Automatic 50% discount for being female
        int discount = (info.getGender() == CoreConstants.FEMALE) ? 50:0;

        // Add a points discount
        discount += getPointsDiscount(info);

        // Add a no claims discount
        discount += getNoClaimsDiscount(info);

        // Generate the quotation and send it back
        return new Quotation(COMPANY, generateReference(PREFIX), (price * (100-discount)) / 100);
    }

    /**
     * calculates discout if no claims made
     *
     * @param info - client info object
     * @return - returns discount
     */
    private int getNoClaimsDiscount(ClientInfo info) {
        return 5*info.getNoClaims();
    }

    /**
     * Returns discount points
     *
     * @param info - clientInfo object
     * @return - discount points
     */
    private int getPointsDiscount(ClientInfo info) {
        if (info.getPoints() == 0) return 20;
        if (info.getPoints() < 3) return 15;
        if (info.getPoints() < 6) return 0;
        return -100;
    }
}
