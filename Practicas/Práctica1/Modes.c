#include<stdlib.h>
#include "Modes.h"

int i, j;
unsigned char BGR [3], pixel [3], aux [3];

void hill(unsigned char * BMP, unsigned char * Pixel, int mode){
    int i;
	for (i = 0; i < 3; i ++)
	{
		if (mode == 0)				
			pixel [i] = ((BGR [0] * key.Dk [0][i]) + (BGR [1] * key.Dk [1][i]) + (BGR [2] * key.Dk [2][i])) % 256;
		else
			pixel [i] = ((BGR [0] * key.Ek [0][i]) + (BGR [1] * key.Ek [1][i]) + (BGR [2] * key.Ek [2][i])) % 256;
	}
	return;
}

void ECB(FILE * org, FILE * ciph, bmp * image, int mode){
    for(i = 0; i < (image->image_size); i++){
        fread(&BGR, sizeof(char),3,org);
        hill((unsigned char*)BGR, (unsigned char*)pixel,mode);
        fwrite(&pixel, sizeof(char), 3, ciph);
    }

}
void CBC(FILE * org, FILE * ciph, bmp * image, int mode){
    if (mode == 1)
	{
        printf ("\n Type Co:\n");
        scanf ("%u %u %u", &pixel [0], &pixel [1], &pixel [2]);
		for (i = 0; i < (image -> image_size); i ++)
		{
			fread (&BGR, sizeof (char), 3, org);
			for (j = 0; j < 3; j ++)
				BGR [j] = (pixel [j] ^ BGR [j]);								
			hill ((unsigned char * ) BGR, (unsigned char * ) pixel, mode);
			fwrite (&pixel, sizeof (char), 3, ciph);
		}
	}else
	{
        printf ("\n Type Co:\n");
        scanf ("%u %u %u", &pixel [0], &pixel [1], &pixel [2]);
		for (i = 0; i < (image -> image_size); i ++)
		{
			fread (&BGR, sizeof (char), 3, org);
			hill ((unsigned char * ) BGR, (unsigned char * ) aux, mode);
			for (j = 0; j < 3; j ++)
				pixel [j] = (pixel [j] ^ aux [j]);								
			fwrite (&pixel, sizeof (char), 3, ciph);
			for (j = 0; j < 3; j ++)
				pixel [j] = BGR [j];
		}
	}
}
void CFB(FILE * org, FILE * ciph, bmp * image, int mode){
     unsigned char cfb[3];
	if (mode == 1)
	{
        printf ("\n Type Co:\n");
        scanf ("%u %u %u", &pixel [0], &pixel [1], &pixel [2]);
		for (i = 0; i < (image -> image_size); i ++)
		{
			fread (&BGR, sizeof (char), 3, org);
			hill ((unsigned char * )cfb, (unsigned char * )aux, mode);
			for (j = 0; j < 3; j ++)
				cfb [j] = (aux [j] ^ BGR [j]);								
			fwrite (&cfb, sizeof (char), 3, ciph);
		}
	}else
	{
        printf ("\n Type Co:\n");
        scanf ("%u %u %u", &pixel [0], &pixel [1], &pixel [2]);
		for (i = 0; i < (image -> image_size); i ++)
		{
			hill ((unsigned char * )pixel, (unsigned char * ) aux, 1);
			fread (&BGR, sizeof (char), 3, org);
			for (j = 0; j < 3; j ++)
				cfb [j] = (aux [j] ^ BGR [j]);								
			fwrite (&cfb, sizeof (char), 3, ciph);
		}
	}
}
void OFB(FILE * org, FILE * ciph, bmp * image, int mode){
    unsigned char aux2 [3];
    printf ("\n Type Co:\n");
    scanf ("%u %u %u", &pixel [0], &pixel [1], &pixel [2]);
	hill ((unsigned char * ) pixel, (unsigned char * ) aux, 1);
	for (i = 0; i < 3; i ++)
		aux2 [i] = aux [i];
	for (i = 0; i < (image -> image_size); i ++)
	{
		fread (&BGR, sizeof (char), 3, org);
		for (j = 0; j < 3; j ++)
			pixel [j] = (aux2 [j] ^ BGR [j]);								
		fwrite (&pixel, sizeof (char), 3, ciph);
		hill ((unsigned char * ) aux, (unsigned char * ) aux2, 1);
	}

}
void CTR(FILE * org, FILE * ciph, bmp * image, int counter, int mode){
    unsigned char aux2 [3];
    int k;
    printf ("\n Type Co:\n");
    scanf ("%u %u %u", &pixel [0], &pixel [1], &pixel [2]);
    hill ((unsigned char * ) pixel, (unsigned char * ) aux, 1);
    for( k = 0; k < counter; k++){
        for (i = 0; i < 3; i ++)
            aux2 [i] = aux [i];
        for (i = 0; i < (image -> image_size); i ++)
        {
            fread (&BGR, sizeof (char), 3, org);
            for (j = 0; j < 3; j ++)
                pixel [j] = (aux2 [j] ^ BGR [j]);								//We realize XOR between pixel and BGR from Image
            fwrite (&pixel, sizeof (char), 3, ciph);
            hill ((unsigned char * ) aux, (unsigned char * ) aux2, 1);
        }
    }

}

FILE * open(char * original, char * encrypted, int tipo)
{
	FILE * pt1, * pt2;
	//We open the file in binary mode to read
	pt1 = fopen (original, "rb");
	if (pt1 == NULL)
	{
		printf("Error while opening file: '%s'.\n", original);
		exit(0);
	}
	pt2 = fopen (encrypted, "wb");
	if (pt2 == NULL)
	{
		printf("Error while creating file: '%s'.\n", encrypted);
		exit(1);
	}
	if (tipo == 1)
	{
		printf("File '%s' opened correctly.\n", original);
		return pt1;
	}
	else
	{
		printf("File '%s' created correctly.\n", encrypted);
		return pt2;
	}
}

void copy_information (FILE * original, FILE * encrypted, bmp * image)
{
	//Type (must be 'BM')
	fread (&image -> type, sizeof (char), 2, original);
	fwrite (&image -> type, sizeof (char), 2, encrypted);

	//Size of the file
	fread (&image -> file_size, sizeof (int), 1, original);
	fwrite (&image -> file_size, sizeof (int), 1, encrypted);

	//Reserved bytes
	fread (&image -> reserved, sizeof (int), 1, original);
	fwrite (&image -> reserved, sizeof (int), 1, encrypted);

	//Offset
	fread (&image -> offset, sizeof (int), 1, original);
	fwrite (&image -> offset, sizeof (int), 1, encrypted);

	//Size of the bitmap
	fread (&image -> bitmap_size, sizeof (int), 1, original);
	fwrite (&image -> bitmap_size, sizeof (int), 1, encrypted);

	//Width
	fread (&image -> width, sizeof (int), 1, original);
	fwrite (&image -> width, sizeof (int), 1, encrypted);

	//Height
	fread (&image -> height, sizeof (int), 1, original);
	fwrite (&image -> height, sizeof (int), 1, encrypted);

	//Number of planes
	fread (&image -> no_planes, sizeof (short),1, original);
	fwrite (&image -> no_planes, sizeof (short),1, encrypted);

	//Bits per pixel
	fread (&image -> bits_per_pixel, sizeof (short),1, original);
	fwrite (&image -> bits_per_pixel, sizeof (short),1, encrypted);

	//Type of compression (must be 0)
	fread (&image -> compression, sizeof (int), 1, original);
	fwrite (&image -> compression, sizeof (int), 1, encrypted);

	//Size of the image
	fread (&image -> image_size, sizeof (int), 1, original);
	fwrite (&image -> image_size, sizeof (int), 1, encrypted);

	//Horizontal resolution
	fread (&image -> horizontal_res, sizeof (int), 1, original);
	fwrite (&image -> horizontal_res, sizeof (int), 1, encrypted);

	//Vertical resolution
	fread (&image -> vertical_res, sizeof (int), 1, original);
	fwrite (&image -> vertical_res, sizeof (int), 1, encrypted);

	//Number of colors
	fread (&image -> no_colors, sizeof (int), 1, original);
	fwrite (&image -> no_colors, sizeof (int), 1, encrypted);

	//Number of important colors
	fread (&image -> important_colors, sizeof (int), 1, original);
	fwrite (&image -> important_colors, sizeof (int), 1, encrypted);

	//We check if the selected file is a bitmap
	if (image -> type [0] != 'B' || image -> type [1] != 'M')	
	{
		printf ("The image must be a bitmap.\n"); 
		exit (1);
	}
	if (image -> bits_per_pixel != 24) 
	{
		printf ("The image must be 24-bits.\n"); 
		exit (1);
	}
}