#include <stdio.h>
#include <stdlib.h>
#include "cipher.c"

int main(void)
{
	int op;
	int alpha,beta;
	printf("1.Encrypt\n2.Decrypt\n");
	scanf("%d",&op);
	printf("Write alpha value\n");
	scanf("%d",&alpha);
	if(gcd(alpha,26) != 1){
		while(gcd(alpha,26) != 1){
			printf("The value of alpha is not valid Write other\n");
			scanf("%d",&alpha);
		}
	}
	printf("Write beta value\n");
	scanf("%d",&beta);

	switch(op){
		case 1:
			encrypt(alpha,beta);
		break;
		case 2:
			decrypt(alpha,beta);
		break;

	}
	return 0;
}