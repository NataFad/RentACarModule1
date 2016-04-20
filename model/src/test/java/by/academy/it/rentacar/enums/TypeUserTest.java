package by.academy.it.rentacar.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for enumeration TypeUser.
 */
public class TypeUserTest
{
    /** testStringToEnum()
     * receives a number and must return to the type of user
     *
      * @throws Exception
     */
   @Test
    public void testStringToEnum() throws Exception {
        try {
            Assert.assertEquals("Valid", TypeUser.GUEST, TypeUser.stringToEnum(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals("Valid", TypeUser.USER, TypeUser.stringToEnum(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
           Assert.assertEquals("Valid", TypeUser.ADMINISTRATOR, TypeUser.stringToEnum(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}