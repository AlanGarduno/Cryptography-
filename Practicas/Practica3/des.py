#!/usr/bin/python
import sys
from Crypto.Cipher import DES                      # funciones para cargar y manipular imágenes
from PIL import Image

#Se declara el cifrador
#
#cipher = DES.new('12345678',DES.MODE_ECB)
#Lectura de la imagen
#file = open("Heart.bmp","rb")
#data = file.read(720)
#file.close()
#Copiando la cabecera de la imagen 
#ciphered = open("ciphered.bmp","wb")
#cipherImage = cipher.encrypt(data)
#ciphered.write(data)
#Escritura de la imagen cifrada
#ciphered.close()

def main():
    print "Enter the path from the original image\n"
    org = raw_input()
    print "\nEnter the name of the cipher image\n"
    cipher = raw_input()
    print "\nSelect an option\n"
    print "\n1.Enrypt\n2.Decrypt\n"
    mode = raw_input()
    print "\nDES\n1.ECB\n2.CBC\n3.CFB\n4.OFB\n5.CTR"
    opc = raw_input()
    if  opc == '1':
        des_ecb(org,cipher,mode)
    elif opc == '2':
        des_cbc(org,cipher,mode)
    elif opc == '3':
        des_cfb(org,cipher,mode)
    elif opc == '4':
        des_ofb(org,cipher,mode)
    elif opc == '5':
        des_ctr(org,cipher,mode)
    else:
        print "Invalid option\n"
        exit()

def des_ecb(org,cp,mode):
    print "DES in ECB mode\n"
    cipher = DES.new('12345678',DES.MODE_ECB)
    if mode == '1' :
        file = Image.open(org)
        w,j = file.size
        pixel_values = list(file.getdata())
        print pixel_values
    elif mode == '2':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.decrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
        


def des_cbc(org,cp,mode):
    print "DES in CBC mode\n"
    IV = "9999"
    cipher = DES.new('12345678',DES.MODE_CBC,IV)
    if mode == '1':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.encrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
    elif mode == '2':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.decrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
        

def des_cfb(org,cp,mode):
    print "DES in CBF mode"
    IV = "9999"
    cipher = DES.new('12345678',DES.MODE_CFB,IV)
    if mode == '1':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.encrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
    elif mode == '2':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.decrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()


def des_ofb(org,cp,mode):
    print "DES in OFB mode"
    IV = "9999"
    cipher = DES.new('12345678',DES.MODE_OFB,IV)
    if mode == '1':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.encrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
    elif mode == '2':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.decrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()

def des_ctr(org,cp,mode):
    print "DES in CTR mode"
    cipher = DES.new('12345678',DES.MODE_CTR)
    if mode == '1':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.encrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
    elif mode == '2':
        file = open(org,"rb")
        data = file.read()
        file.close()
        cipher.decrypt(data)
        ciphered = open(cp,"wb")
        ciphered.write(data)
        ciphered.close()
        



main()