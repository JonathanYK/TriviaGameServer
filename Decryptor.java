package GeneralPackage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class Decryptor {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
  
    public static String Decrypt(String strToDecrypt) {
    	MessageDigest sha = null;
    	
            try {
	            	String secret = "secretKey";
	                key = secret.getBytes("UTF-8");
	                sha = MessageDigest.getInstance("SHA-1");
	                key = sha.digest(key);
	                key = Arrays.copyOf(key, 16);
	                secretKey = new SecretKeySpec(key, "AES");
	                
	             
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
                
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            catch (Exception e) {
            	System.out.println("Error while decrypting: " + e.toString());
            }
            return null;
    }

    
   /* public static String Encrypt(String strToEncrypt) {
    	MessageDigest sha = null;
    		try {
       
                String secret = "secretKey";
                key = secret.getBytes("UTF-8");
                sha = MessageDigest.getInstance("SHA-1");
                key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                secretKey = new SecretKeySpec(key, "AES");
 
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
    
    		catch (NoSuchAlgorithmException e) {
    			e.printStackTrace();
    		}
    		catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
    		catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
    		}
        return null;
    }
 

    public static void main(String[] args) {
     
        String originalString = "enter string to encrypt";
        
        String encryptedString = Decryptor.Encrypt(originalString);
        String decryptedString = Decryptor.Decrypt(encryptedString);
        System.out.println(originalString);
        System.out.println(encryptedString);
    }*/
}
