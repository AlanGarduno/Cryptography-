#include<stdlib.h>
#include<stdio.h>
#include "Structures.h "

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
	
	//We open the file in binary mode to write
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