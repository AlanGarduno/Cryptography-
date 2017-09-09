//
// Created by JCVELMON on 07/09/2017.
//

#ifndef HILL_READIMAGE_H
#define HILL_READIMAGE_H

#include <stdint.h>

typedef struct bmpFileHeader{
    uint32_t size; //Tamaño del archivo (total)
    uint16_t resv1; //Reservado del sistema
    uint16_t resv2; //Reservado del sistema
    uint32_t offset; //offset de los datos de la imagen
}bmpFileHeader;

typedef struct bmpInfoHeader
{
    uint32_t headersize;      /* Tamaño de la cabecera */
    uint32_t width;               /* Ancho */
    uint32_t height;          /* Alto */
    uint16_t planes;                  /* Planos de color (Siempre 1) */
    uint16_t bpp;             /* bits por pixel */
    uint32_t compress;        /* compresión */
    uint32_t imgsize;     /* tamaño de los datos de imagen */
    uint32_t bpmx;                /* Resolución X en bits por metro */
    uint32_t bpmy;                /* Resolución Y en bits por metro */
    uint32_t colors;              /* colors used en la paleta */
    uint32_t imxtcolors;      /* Colores importantes. 0 si son todos */
} bmpInfoHeader;


unsigned char *LoadBMP(char *filename, bmpInfoHeader *bInfoHeader);
void DisplayInfo(bmpInfoHeader *info);
void TextDisplay(bmpInfoHeader *info, unsigned char *img);

#endif //HILL_READIMAGE_H
