using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace FirmaDigital
{
    class Program
    {
        static void Main(string[] args){

            int op;
            byte[] firma;
            byte[] textoPlano;
            String todo;
            byte[] firmaDigital;
            byte[] cipherText;
            RSACryptoServiceProvider rsa = new RSACryptoServiceProvider();
            String plainText;
            String digesto;
            Console.WriteLine("Firma Digital");
            Console.WriteLine("1.Firmar\n2.Compobar");
            op = int.Parse(Console.ReadLine());
            switch (op)
            {
                case 1:
                    plainText = leer();
                    Console.WriteLine(plainText);
                    digesto = Digesto.digestoSHA1(plainText);
                    firma = ASCIIEncoding.Default.GetBytes(digesto);
                    textoPlano = ASCIIEncoding.Default.GetBytes(plainText);
                    cipherText = rsa.Encrypt(textoPlano, true);
                    firmaDigital = rsa.Encrypt(firma, true);
                    todo = cipherText.ToString() + firmaDigital.ToString();
                    File.WriteAllText("C:/Users/JCVELMON/Desktop/Semestre Actual/Cryptography/Practicas/FirmaDigital/c.txt",todo);
                    break;
                case 2:

                    break;


            }
            Console.ReadKey();
        }

        static String leer()
        {
            String data;
            data = File.ReadAllText("C:/Users/JCVELMON/Desktop/Semestre Actual/Cryptography/Practicas/FirmaDigital/m.txt");
            return data; 
        }

            static public byte[] RSAEncrypt(byte[] DataToEncrypt, RSAParameters RSAKeyInfo, bool DoOAEPPadding){
                    try
                    {
                        byte[] encryptedData;
                        //Create a new instance of RSACryptoServiceProvider.
                        using (RSACryptoServiceProvider RSA = new RSACryptoServiceProvider())
                        {

                            //Import the RSA Key information. This only needs
                            //toinclude the public key information.
                            RSA.ImportParameters(RSAKeyInfo);

                            //Encrypt the passed byte array and specify OAEP padding.  
                            //OAEP padding is only available on Microsoft Windows XP or
                            //later.  
                            encryptedData = RSA.Encrypt(DataToEncrypt, DoOAEPPadding);
                        }
                        return encryptedData;
                    }
                    //Catch and display a CryptographicException  
                    //to the console.
                    catch (CryptographicException e)
                    {
                        Console.WriteLine(e.Message);

                        return null;
                    }

    }

    static public byte[] RSADecrypt(byte[] DataToDecrypt, RSAParameters RSAKeyInfo, bool DoOAEPPadding)
    {
        try
        {
            byte[] decryptedData;
            //Create a new instance of RSACryptoServiceProvider.
            using (RSACryptoServiceProvider RSA = new RSACryptoServiceProvider())
            {
                //Import the RSA Key information. This needs
                //to include the private key information.
                RSA.ImportParameters(RSAKeyInfo);

                //Decrypt the passed byte array and specify OAEP padding.  
                //OAEP padding is only available on Microsoft Windows XP or
                //later.  
                decryptedData = RSA.Decrypt(DataToDecrypt, DoOAEPPadding);
            }
            return decryptedData;
        }
        //Catch and display a CryptographicException  
        //to the console.
        catch (CryptographicException e)
        {
            Console.WriteLine(e.ToString());

            return null;
        }

    }
    }
}
