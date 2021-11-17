package service.core;

import java.util.Random;

/**
 * Abstract quotation service class
 *
 * @author Rem
 */
public abstract class AbstractQuotationService {
    private int counter = 1000;
    private Random random = new Random();

    /**
     * Generates the reference id of the quotation service company
     *
     * @param prefix - prefix of the reference id
     * @return - reference Id
     */
    protected String generateReference(String prefix) {
        String ref = prefix;
        int length = 100000;
        while (length > 1000) {
            if (counter / length == 0) ref += "0";
            length = length / 10;
        }
        return ref + counter++;
    }

    /**
     * Genearetes the price of the quotatiobn
     *
     * @param min - minimum
     * @param range - range
     * @return - quotation price
     */
    protected double generatePrice(double min, int range) {
        return min + (double) random.nextInt(range);
    }
}
