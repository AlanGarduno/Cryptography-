#include<stdio.h>
#include<stdlib.h>

typedef struct BMP
{
	char type [2];															
	int file_size;															
	int reserved;														
	int offset;																
	int bitmap_size;														
	int width;																
	int height;																
	short no_planes;														
	short bits_per_pixel;													
	int compression;														
	int image_size;															
	int horizontal_res;													
	int vertical_res;													
	int no_colors;															
	int important_colors;												
}bmp;

typedef struct llave
{
	unsigned char Ek [3][3];
	unsigned char Dk [3][3];
}llave;

llave key =														
	{
		{
			{1, 2, 3},
			{4, 5, 6},
			{11, 9, 8}
		},
		{
			{90, 167, 1},
			{74, 179, 254},
			{177, 81, 1}
		}
    };

void hill(unsigned char * BMP, unsigned char * Pixel, int mode);
void ECB(FILE* org, FILE* ciph, bmp* image, int mode);
void CBC(FILE* org, FILE* ciph, bmp* image, int mode);
void CFB(FILE* org, FILE* ciph, bmp* image, int mode);
void OFB(FILE* org, FILE* ciph, bmp* image, int mode);
void CTR(FILE* org, FILE* ciph, bmp* image, int counter);

 