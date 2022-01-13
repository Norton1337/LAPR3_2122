#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "divideByComma.h"
int getMax(int *x, int *y, int *z){

	FILE* filePointer;
    int bufferLength = 255;
    char buffer[bufferLength];
 
    filePointer = fopen("containers.csv", "r");
	
    while(fgets(buffer, bufferLength, filePointer)) {
        char *s = buffer;
        char *pt;
        pt = strtok (s,",");

        int *arrayOfInt = divideByComma(pt);
   
        int newX = arrayOfInt[0];
        int newY = arrayOfInt[1];
        int newZ = arrayOfInt[2];
	
		if(newX>*x)
			*x=newX;
		if(newY>*y)
			*y=newY;
		if(newZ>*z)
			*z=newZ;
		
    }
    
   
     //Close file descriptor
    fclose(filePointer);

}
