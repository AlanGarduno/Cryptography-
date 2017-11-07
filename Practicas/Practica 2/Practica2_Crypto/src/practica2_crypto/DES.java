/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_crypto;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author JCVELMON
 */
public class DES {
    
    BufferedImage Original;
    BufferedImage Encrypted;
    KeyGenerator keyGenerator;
    SecretKey key;
    byte[] bits;
    byte[] bitsC;
    Cipher cipher;
    
    public DES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
        keyGenerator = KeyGenerator.getInstance("DES");
        key = keyGenerator.generateKey();
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        bits = new byte[8];
        
    }
    
    public void read() throws IllegalBlockSizeException, BadPaddingException{
        try{
            
            InputStream input = new FileInputStream("C:\\Users\\JCVELMON\\Desktop\\Semestre Actual\\Cryptography\\Practicas\\Practica 2\\Practica2_Crypto\\src\\practica2_crypto\\Heart.bmp");
            ImageInputStream imageInput = ImageIO.createImageInputStream(input);
            Original = ImageIO.read(imageInput);
            for(int x=0; x < Original.getWidth();x++){
                for(int y =0;y <Original.getHeight(); y++){
                    int rgb = Original.getRGB(x, y);
                    Color color = new Color(rgb);
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    for(int i = 0;i<3;i++){
                        if( i == 0)
                            bits[i] = (byte) r;
                        else if( i == 1)
                            bits[i] = (byte) g;
                        else if( i == 2)
                            bits[i] = (byte) b;
                        else
                             bits[i] = (byte) 0;
                    }
                    bitsC = cipher.doFinal(bits);
                    System.out.println("r:"+r+" g:"+g+" b:"+b);
                    System.out.println(Arrays.toString(bits));
                }
                System.out.println("");
            }
            System.out.println("Si jalo ");
        }catch(IOException e){
            System.out.print(e);
    }
 }
 /*   
    public void write(){
        
    }
    
    public void ECB(){
        
    }
    
    public void CBC(){
        
    }*/
}
