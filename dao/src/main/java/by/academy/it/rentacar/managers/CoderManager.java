package by.academy.it.rentacar.managers;

import org.apache.log4j.Logger;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**Class CoderManager
 * 
 * Class CoderManager codes the password of the user.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class CoderManager {
    
    private static String ALGORITHM = "MD5";
    private static int POSITIVE = 1;

    /**Method getHashCode()
     * codes the password of the user to hashcode to help algorithm md5
     *
     * @param password
     * @return string
     */
    public static String getHashCode(String password) {
        try {
            MessageDigest dig = MessageDigest.getInstance(ALGORITHM);
            BigInteger hash = new BigInteger(POSITIVE, dig.digest(password.getBytes()));
            //converting to string of 32 symbols, which represents hex number
            return String.format("%032x", hash);
            
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(CoderManager.class).error("NoSuchAlgorithm " + ALGORITHM);
        }
        throw new RuntimeException("Exception at CoderManager: NoSuchAlgorithm");
    }
}
