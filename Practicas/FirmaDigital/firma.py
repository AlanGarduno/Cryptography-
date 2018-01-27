import os
from Crypto.Hash import SHA
from Crypto.PublicKey import RSA
from Crypto import Random

def main ():
    os.system ("cls")
    
    #Calling the selected option
    digesto()

#Electronic Codebook

def generateKey():
    random_generator = Random.new().read
    key = RSA.generate(1024, random_generator)
    keys = open("k.txt","wb")
    keys.write(key.publickey)


def cipher(key, m):
    

def decipher(key, c):

def digesto ( ):

    #Creating a new Hash object (SHA 1)
    dig = SHA.new ()

    #Opening both files
    original_file = open ("m.txt", "rb")
    encrypted_file = open ("c.txt", "wb")

    #Reading all the massage as a string of bytes
    data = original_file.read ()

    #Digest of the message
    dig.update (data)

    #Converting from _SHA1 to bytes
    data = bytes (dig.hexdigest (), 'utf-8')

    #Writing bytes on binary file
    encrypted_file.write (data)

    original_file.close ()
    encrypted_file.close ()

#Main function
main ()