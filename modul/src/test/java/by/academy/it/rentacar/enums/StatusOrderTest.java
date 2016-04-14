package by.academy.it.rentacar.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit-test for enumeration StatusOrder
 *
 * Created by Nata on 12.04.2016.
 */
public class StatusOrderTest {
    /** testStringToEnum()
     * receives a number and must return to the status of the order
     *
     * @throws Exception
     */
    @Test
    public void testStringToEnum() throws Exception {
        try {
            Assert.assertEquals("Valid", StatusOrder.AWAITING, StatusOrder.stringToEnum(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals("Valid", StatusOrder.APPROVED, StatusOrder.stringToEnum(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals("Valid", StatusOrder.REJECTED, StatusOrder.stringToEnum(2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals("Valid", StatusOrder.CLOSED, StatusOrder.stringToEnum(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
