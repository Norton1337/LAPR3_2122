#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "divideByComma.h"
int getSize(){

	FILE* filePointer;
    int bufferLength = 255;
    char buffer[bufferLength];
	int contador = 0;
    filePointer = fopen("containersNew.txt", "r");
	
    while(fgets(buffer, bufferLength, filePointer)) {
        contador++;
    }
    
     //Close file descriptor
    fclose(filePointer);
    return contador;
}
