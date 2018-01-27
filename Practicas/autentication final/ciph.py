from Crypto.PublicKey import RSA
from Crypto.Hash import SHA
from Crypto.Signature import PKCS1_v1_5

#se obtiene la llave publica 
def get_public_key(publicKey):
    bob=open(publicKey,"r")
    pubKey=RSA.importKey(bob.read())
    bob.close()
    return pubKey

#se obtiene la llave privada
def get_private_key(privateKey):
    alice=open(privateKey)
    privKey=RSA.importKey(alice.read())
    alice.close()
    return privKey

def get_message(fileName):
    msg=open("m.txt","r")
    mens=msg.read()
    msg.close()
    return mens

def main():
    public = raw_input("Type the public file name:\n")
    private = raw_input("Type the priate file name:\n")
    #fileName = raw_input("Enter the file name:\n")
    #se obtiene el mensaje
    mens = get_message("m.txt")
    #se genera el digesto
    hash1=SHA.new(mens).hexdigest()
    #print hash1
    #se cifra con la llave privada de alice
    #firma=privKey.encrypt(hash1,32)
    #print "firma:\n"+str(firma)
    privKey = get_private_key(private)
    pubKey = get_public_key(public)
    #otra forma de firma
    firma=privKey.sign(hash1,123)
    #print firma

    #se cifra el mensaje con la llave publica de bob
    msg_ciph=pubKey.encrypt(mens,32)
    #print "mensaje cifrado:\n"+str(msg_ciph)

    #se guarda en el archivo
    enc=open("cf.txt","w")
    enc.write(str(msg_ciph)+"\n-------------------------------\n"+str(firma))
    enc.close()
    print "Message ciphered and signed\n"

main()