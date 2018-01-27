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
			{4, 5, 6},  //key
			{11, 9, 8}
		},
		{
			{90, 167, 1},  //Iverse of the key mod 256
			{74, 179, 254},
			{177, 81, 1}
		}
    };
    
FILE * open(char * original, char * encrypted, int tipo);
void copy_information(FILE * original, FILE * encrypted, bmp * image);