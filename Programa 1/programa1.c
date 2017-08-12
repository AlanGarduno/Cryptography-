#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[]) {
  FILE* fileptr;
  FILE* fileCopy;
  char message[100];
  char message2[100];
  fileptr = fopen("m.txt","r");
  fileCopy = fopen("c.txt","w+");
  if(fileptr == NULL){
    printf("Error en la apertura del archivo\n");
  }else{
    while (feof(fileptr) == 0) {
      fgets(message,100,fileptr);
    }
    fclose(fileptr);
    strcpy(message2,message);
    fputs(message2,fileCopy);
  }
  fclose(fileCopy);
  return 0;
}
