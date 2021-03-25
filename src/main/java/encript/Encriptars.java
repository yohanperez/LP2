package encript;


import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Encriptars {
    
  private static final String SECRET_KEY = "run";  
  private static final String SALT = "procesoencript"; //llave
 
  public static String encrypt(String encriptar) {
    try {
      byte[] rk = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
      IvParameterSpec ivpar = new IvParameterSpec(rk); 
      SecretKeyFactory Sfactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec Kspec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
      SecretKey SK = Sfactory.generateSecret(Kspec);
      SecretKeySpec secretKey = new SecretKeySpec(SK.getEncoded(), "AES");
 
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivpar);
      return Base64.getEncoder()
          .encodeToString(cipher.doFinal(encriptar.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }
}
