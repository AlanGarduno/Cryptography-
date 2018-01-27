/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_crypto;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.*;
import java.util.*;
/**
 *
 * @author JCVELMON
 */
public class Practica2_Crypto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        DES d = new DES();
        d.read();
    }
    
}
