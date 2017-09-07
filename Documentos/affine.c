#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>


int main(int argc, char** argv)
{
    int a = 3,
        b = 13,
        i = 0,
        m = 26, //m = 27 para usar Ñ
        len = 12;
    char* plaintext;
    plaintext = (char*) malloc(len);
    strcpy(plaintext,"cryptography");

    char ciphertext, x;

    i = 0;
    while(i < len)
    {
        x = toupper(plaintext[i]) - 64;//A=1; Cambiar 64 por 65 para A=0.
        //if(x > 14) x++;//Para usar 'Ñ''
        ciphertext = ((a * x + b) % m) + 64; //Quitar "+ 64" si se usa 'Ñ' / Cambiar 64 por 65 para A=0
        printf("%c", ciphertext); //Cambiar "%c" por "%i\n" cuando se usa 'Ñ'
        i++;
    }

    free(plaintext);
    return 0;
}
