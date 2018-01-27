package practica_3;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
public class Practica_3 {
    public static SecretKey toSecretKey(String cadena, String a){
        SecretKey key = new SecretKeySpec(cadena.getBytes(),0,cadena.getBytes().length,a);
        return key;
    }
    public static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length]; 
        for(int i=0; i<a.length; i++){
            result[i]=a[i];
        }
        for(int i=a.length,j=0; j<b.length; i++,j++){
            result[i]=b[j];
        }
        return result;
    }
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException, InterruptedException {
        int a,m,e;
        SecretKey key;
        Cipher c;
        String image ="";
        byte[] imageInByte;
        String k ="";
        String path = "C:\\Users\\JCVELMON\\Desktop\\Semestre Actual\\Cryptography\\Practicas\\Practica_3\\src\\practica_3\\";
        Scanner in = new Scanner (System.in);
        Scanner in2 = new Scanner (System.in);
        System.out.println("Choose the Algorithm\n1.DES\n2.AES");
        a = in.nextInt();
        System.out.println("\n");
        System.out.println("Choose an operation mode\n"+"1.ECB\n2.CBC\n3.CFB\n4.OFB\n5.CTR");
        m = in.nextInt();
        System.out.println("\n");
        System.out.println("1.Encrypt\n2.Decrypt\n");
        e = in.nextInt();
        System.out.println("\n");
        System.out.println("Enter the name of the image\n");
        image = in2.nextLine();
        k = in.nextLine();
         switch(a){
             case 1:
              if( e == 1){
                 if( m == 1 ){
                    System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/ECB/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredECB.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN ECB MODE!!");
                 }
                 else if( m == 2){
                    System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredCBC.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN CBC MODE");
                 }
                 else if( m == 3 ){
                                         System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/CFB/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredCFB.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN CFB MODE");
                 }
                 else if( m == 4){
                   System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];

                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/OFB/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredOFB.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN OFB MODE");
                 }
                 else if(m == 5){
                                         System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/GCM/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredCTR.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN CTR MODE");   
                 }
              }
              else{
                 if( m == 1 ){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/ECB/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dECB.bmp"));
                    System.out.println("IMAGE DECRYPTED IN ECB MODE");
                 }
                 else if( m == 2){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/CBC/NoPadding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dCBC.bmp"));
                    System.out.println("IMAGE DECRYPTED IN CBC MODE");
                 }
                 else if( m == 3 ){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/CFB/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dCFB.bmp"));
                    System.out.println("IMAGE DECRYPTED IN CFB MODE");
                 }
                 else if( m == 4){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/OFB/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dOFB.bmp"));
                    System.out.println("IMAGE DECRYPTED IN OFB MODE");
                 }
                 else if(m == 5){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"DES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];

                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("DES/GCM/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dCTR.bmp"));
                    System.out.println("IMAGE DECRYPTED IN CTR MODE");   
                 } 
              }
             break;
             case 2:
              if( e == 1){
                 if( m == 1 ){
                    System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredECB.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN ECB MODE!!");
                 }
                 else if( m == 2){
                    System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredCBC.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN CBC MODE");
                 }
                 else if( m == 3 ){
                                         System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/CFB/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredCFB.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN CFB MODE");
                 }
                 else if( m == 4){
                   System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/OFB/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredOFB.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN OFB MODE");
                 }
                 else if(m == 5){
                                         System.out.println("Key to encypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/GCM/PKCS5Padding");
                    c.init(Cipher.ENCRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"cipheredCTR.bmp"));
                    System.out.println("IMAGE ENCRYPTED IN CTR MODE");   
                 }
              }
              else{
                 if( m == 1 ){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dECB.bmp"));
                    System.out.println("IMAGE DECRYPTED IN ECB MODE");
                 }
                 else if( m == 2){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];

                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/CBC/NoPadding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dCBC.bmp"));
                    System.out.println("IMAGE DECRYPTED IN CBC MODE");
                 }
                 else if( m == 3 ){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/CFB/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dCFB.bmp"));
                    System.out.println("IMAGE DECRYPTED IN CFB MODE");
                 }
                 else if( m == 4){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/OFB/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dOFB.bmp"));
                    System.out.println("IMAGE DECRYPTED IN OFB MODE");
                 }
                 else if(m == 5){
                    System.out.println("Key to decrypt:\n");
                    k = in.nextLine();
                    key = toSecretKey(k,"AES");
                    BufferedImage originalImage = ImageIO.read(new File(path+image));
                    byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00,0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};
                    IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(originalImage, "bmp", baos);
                    baos.flush();
                    imageInByte = baos.toByteArray();
                    baos.close();
                    byte[] header=new byte[54];
                    byte[] body=new byte[imageInByte.length-54];
                    for(int i=0; i<54; i++){
                        header[i]=imageInByte[i];
                    }
                    for(int i=0,j=54; j<imageInByte.length; i++,j++){
                        body[i]=imageInByte[j];
                    }
                    c = Cipher.getInstance("AES/GCM/PKCS5Padding");
                    c.init(Cipher.DECRYPT_MODE, key,ivectorSpecv);
                    byte[] imageb = c.doFinal(body);
                    byte[] imageC = concatenateByteArrays(header,imageb);
                    InputStream im = new ByteArrayInputStream(imageC);
                    BufferedImage bImageFromConvert = ImageIO.read(im);
                    ImageIO.write(bImageFromConvert, "bmp", new File(path+"dCTR.bmp"));
                    System.out.println("IMAGE DECRYPTED IN CTR MODE");   
                 } 
              }
             break;
         }   
    }
}
