package by.academy.it.rentacar.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit-test for enumeration Transmission
 *
 * Created by Nata on 12.04.2016.
 */
public class TransmissionTest {
    /** testStringToEnum()
     * receives a number and must return to the type of the transmission
     *
     * @throws Exception
     */
    @Test
    public void testStringToEnum() throws Exception {
        try {
            Assert.assertEquals("Valid", Transmission.AUTO, Transmission.stringToEnum(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals("Valid", Transmission.MANUAL, Transmission.stringToEnum(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
