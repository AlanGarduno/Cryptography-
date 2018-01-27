from Crypto.PublicKey import RSA
from Crypto import Random

def generateLlave(pub, priv ):
    rand = Random.new().read
    key = RSA.generate(1024,rand)
    keyp = open(priv,"w")
    keyp.write(key.exportKey('PEM'))
    keyp.close()
    keyp = open(pub,"w")
    key = key.publickey()
    keyp.write(key.exportKey('PEM'))
    keyp.close


def main():
    public = raw_input("Type public file name:\n")
    private = raw_input("Type private file name:\n")
    generateLlave(public,private)

main()    


#rand=Random.new().read
#keyA=RSA.generate(1024,rand)
#print keyA.can_encrypt()
#print keyA.can_sign()
#alice=open("privAlice.txt","w")
#alice.write(keyA.exportKey('PEM'))
#alice.close()
#alice=open("pubAlice.txt","w")
#keyA=keyA.publickey()
#alice.write(keyA.exportKey('PEM'))
#alice.close()

#rand=Random.new().read
#keyB=RSA.generate(1024,rand)
#print keyB.can_encrypt()
#print keyB.can_sign()
#bob=open("privBob.txt","w")
#bob.write(keyB.exportKey())
#bob.close()
#bob=open("pubBob.txt","w")
#keyB=keyB.publickey()
#bob.write(keyB.exportKey())


