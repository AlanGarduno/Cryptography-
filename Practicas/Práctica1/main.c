#include<stdio.h>
#include<stdlib.h>
#include "modes.c"

int main( void ){
    FILE *org, *ciph;
    int op,mode;
    char*path = (char*)malloc(sizeof(char));  
    char *cipher =(char*)malloc(sizeof(char));
    bmp image;
    printf("Enter the path of the original image\n");
    scanf("%s",path);
    printf("Enter the name of the ouput file\n");
    scanf("%s",cipher);
    org = open_file(path,cipher,1);
    ciph = open_file(path,cipher,2);
    read_head(org,ciph,&image);
    printf("1.Encrypt\n2.Decrypt\n");
    scanf("%d",&op);
    switch( op ){
        case 1:
            printf("Select the mode\n1.ECB \n2.CBC \n3.CFB \n4.OFB\n5.CTR\n");
            scanf("%d",&mode);
            if(mode == 1)
                ECB(org,ciph,&image,1);
            else if( mode == 2)
                CBC(org,ciph,&image,1);
            else if(mode == 3)
                CFB(org,ciph,&image,1);
            else if(mode == 4)
                OFB(org,ciph,&image,1);
            else if(mode == 5)
                CTR(org,ciph,&image,1,3);
            else
                {printf("No aviable option\n"); exit(0);}
        break;
        case 2:
            printf("Select the mode\n1.ECB \n2.CBC \n3.CFB \n4.OFT\n5.CTR\n");
            scanf("%d",&mode);
            if(mode == 1)
                ECB(org,ciph,&image,0);
            else if( mode == 2)
                CBC(org,ciph,&image,0);
            else if(mode == 3)
                CFB(org,ciph,&image,0);
            else if(mode == 4)
                OFB(org,ciph,&image,0);
            else if(mode == 5)
                CTR(org,ciph,&image,0,3);
            else
                {printf("No aviable option\n"); exit(0);}
        break;
        default:
            printf("No option aviable\n");
            exit(0);
        break;
    }
    return 0;
}