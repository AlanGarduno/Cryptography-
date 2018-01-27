from Crypto.PublicKey import RSA
from Crypto.Hash import SHA
import ast
from Crypto.Signature import PKCS1_v1_5

def main():

	public = raw_input("Type the public file name:\n")	
	private = raw_input("Type the priate file name:\n")
	#fileName = raw_input("Enter the file name:\n")

#se obtienen la llave publica
	alice=open(public,"r")
	pubKey=RSA.importKey(alice.read())
	alice.close()

	#print pubKey

	#se obtiene la llave privada
	bob=open(private)
	privKey=RSA.importKey(bob.read())
	bob.close()
	#print privKey

	#se lee el mensaje
	msg=open("cf.txt","r")
	msg_ciph=msg.readline()
	#print msg_ciph

	#se obtiene la firma
	msg.readline()
	firma=msg.readline()
	#print firma
	msg.close()

	#se verifica la firma
	mens=privKey.decrypt(ast.literal_eval(msg_ciph))
	hash1=SHA.new(mens).hexdigest()
	#print hash1

	if(pubKey.verify(hash1,ast.literal_eval(firma))):
		print "The message is autenthic, you can trust"
		print "Message is: "+mens
	else:
		print "Something is wrong, this message has been modifiqued"

main()