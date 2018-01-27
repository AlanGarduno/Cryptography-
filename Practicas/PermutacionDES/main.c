#include<stdio.h>
#include<stdlib.h>

int main(void){
    char cad[4][8] ={{1,26,15,14,13,22,7,8},
                  {29,2,16,31,21,6,23,9 },
                  {28,17,3,20,5,24,12,10},
                  {27,18,19,4,0,25,30,11}};
    char * mess = (char*)malloc(sizeof(4));
    printf("Escribe una cadena\n");
    scanf("%s",mess);
    printf("%x %x %x %x",mess[0],mess[1],mess[2],mess[3]);
}