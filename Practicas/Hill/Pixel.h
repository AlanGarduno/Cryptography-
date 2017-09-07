//
// Created by JCVELMON on 07/09/2017.
//

#ifndef HILL_PIXEL_H
#define HILL_PIXEL_H
typedef struct Pixel{
    int R;
    int G;
    int B;
}Pixel;

Pixel* init();
Pixel* eHill(Pixel*img);

#endif //HILL_PIXEL_H
