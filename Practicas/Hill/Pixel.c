//
// Created by JCVELMON on 07/09/2017.
//

#include "Pixel.h"
#include <stdlib.h>
#include <stdio.h>

Pixel* init(){
    Pixel* aux = (Pixel*)malloc(sizeof(Pixel));
    if(aux == NULL){
        printf("No enouth memory");
        exit(0);
    }
    aux->R=0;
    aux->G=0;
    aux->G=0;

    return aux;
}
Pixel* eHill(Pixel*img){

}