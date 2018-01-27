#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cipher.h"


//Code reference (2)
int alg_euc_ext(int n1,int n2) {
    int array[3],x=0,y=0,d=0,x2 = 1,x1 = 0,y2 = 0,y1 = 1,q = 0, r = 0;
    int flag=1;
		int aux;
		int in=n1;
    if(n2==0){
        array[0]=n1;
        array[1]=1;
        array[2]=0;
    }
    else{
        while(n2>0){
            q = (n1/n2);
            r = n1 - q*n2;
            x = x2-q*x1;
            y = y2 - q*y1;
            n1 = n2;
            n2 = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
	if(flag%2 != 0){
		printf("%d = %d(%d) + %d        \n   1 = %d(%d) - %d(%d)  \n",n1*q+r,n1,q,r,x1,y2,x2,y1 );
	}else{
		printf("%d = %d(%d) + %d        \n   1 = %d(%d) - %d(%d)  \n",n1*q+r,n1,q,r,x2,y1,x1,y2 );
		}
		flag++;
	}
        array[0] = n1;   // mcd (n1,n2)
        array[1] = x2;   // x
        array[2] = y2;   // y
    }

	aux = multiplicativeInverse(in);
    return aux; //Alfa inverso

}

int invAd(int beta){
	int tmp = 0;
	while(beta < 26){
		tmp++;
		beta++;
	}
		if((tmp%26) == 0)
			printf("%d\n",tmp );
			return tmp;  
	printf("%d\n",tmp );
	return tmp;
}

int multiplicativeInverse (int alpha)
{
	int x, inverse;
	for(inverse = 0; inverse < 26; inverse++)
    {
        x = (alpha * inverse) % 26;
        if(x == 1)
            return inverse;
    }
}

//Code reference (4)
int gcd (int alpha, int alphabet)									
{
	int temp, inverse, x;
	if (alpha > alphabet)
	{
		temp = alphabet;
		alphabet = alpha;
		alpha = temp;
	}
	if (alpha != 0)									
	{
        
        gcd (alpha, alphabet % alpha);
    }else
    {
     	return alphabet;
    }
}

void encrypt(int alpha, int beta){
	int i,c;
	char *plainText = (char*)malloc(sizeof(char));
	char *cipherText = (char*)malloc(sizeof(char));
	plainText = read('e');
	printf("This program will encrypt the following message: %s\n",plainText );
	for (i = 0; i <strlen(plainText);i++)
	{
		c = plainText[i] - 97; 
		c = c*alpha;
		c = c + beta;
		c = c%26;
		cipherText[i] = c + 65; //To upper case
	}
	cipherText[i] = '\0';
	write(cipherText);
}
void decrypt(int alpha, int beta){
	int i,c, inverse, additive;
	char *plainText = (char*)malloc(sizeof(char));
	char *cipherText = (char*)malloc(sizeof(char));
	cipherText = read('d');
	printf("Euclides Extended Algorithm\n");
	inverse = alg_euc_ext(alpha,26);
	additive = invAd(beta);
	printf("This program will decrypt the following message: %s\n",cipherText);
	for (i = 0; i < strlen(cipherText); ++i)
	{
		c = cipherText[i] - 65;
		c = c * inverse;
		c = c - additive;
		c = c % 26;
		plainText[i] = c + 97; //to lower case
	}
	plainText[i] = '\0';
	printf("Decrypted message is: %s\n",plainText);



}

char * read(char mode){
	FILE *file;
	int i = 0;
	char c, *texInFile = (char*)malloc(sizeof(char));
	if(mode == 'e'){
		file = fopen("m.txt","r");
		if(file == NULL){
			printf("Unable to read the m.txt file\n");
			exit(0);
		}
		else
			printf("File has been opened \n");
		c = fgetc(file);
		while(c != EOF)
		{
			if (c != 32 && c != '\n')									
			{	
				if ((c >= 'a' && c <= 'z'))
					texInFile [i ++] = c;							
				else
				{														
				printf("File m.txt has to contain only lower case characters, otherwise the cipher won't work wwell\n");
				exit (0);
				}
			}
			c = fgetc(file);
		}
		
		texInFile[i] = '\0'; // Null character
		fclose(file);
		return texInFile;

	}
	else{
		file = fopen("c.txt","r");
		if(file == NULL){
			printf("Unable to read the c.txt file\n");
			exit(0);
		}
		else
			printf("File has been opened \n");
		c = fgetc(file);
		while(c != EOF){
			if (c != 32 && c != '\n')									
			{	
				if ((c >= 'A' && c <= 'Z'))
					texInFile [i ++] = c;							
				else
				{														
				printf("File c.txt has to contain only upper case characters, otherwise the decipher won't work wwell\n");
				exit (0);
				}
			}
			c = fgetc(file);
		}
		texInFile[i] = '\0'; // Null character
		fclose(file);
		return texInFile;

	}
}

void write(char * text){
	FILE * file;									
	file = fopen ("c.txt", "w");						
	if (file == NULL){
		printf("Error opening: 'c.txt'\n");
		exit(0);
	}
	fprintf(file, "%s", text);					
	printf("File encrypted succesfully.\n\n\n");
	fclose (file);	
}