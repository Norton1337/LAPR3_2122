#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "putRestWithZero.h"
#include "printMatrix.h"
#include "printArray.h"
#include "divideByComma.h"
#include "US314.h"
#include "US315.h"
#include "US316.h"
#include "utility.h"

int num = N_MAX;
int elemSize = N_ELEM;
int matrix[N_MAX][N_MAX][N_MAX];
int structArraySize = SIZE;

int main(){
    putRestWithZero(matrix);

    FILE* filePointer;
    int bufferLength = 255;
    char buffer[bufferLength];

    filePointer = fopen("cargo.txt", "r");
	
    while(fgets(buffer, bufferLength, filePointer)) {
        char *s = buffer;
        char *pt;
        pt = strtok (s,",");

        int *arrayOfInt = divideByComma(pt);
        int elem = arrayOfInt[0];
        int x = arrayOfInt[1];
        int y = arrayOfInt[2];
        int z = arrayOfInt[3];

        matrix[x][y][z] = elem;

        //printMatrix(matrix);
        //printArray(arrayOfInt);
    }
     //Close file descriptor
    fclose(filePointer);
    
    
    printMatrix(matrix);
    
    printf("\n--------------------------\n");
    printf("US314: 'get amount of containers and free slots.'\n\n");
    long amountOfContainers = countContainers();
    int *amount = (int*) &amountOfContainers;
	printf("There are %d containers\n",*amount);
	printf("There are %d free slots\n",*(amount+1));
	
	printf("\n--------------------------\n");
	printf("US315: 'verify if container exists, 1 if it does, 0 otherwise.'\n\n");
	int exists = containerExists(4,4,4);
	printf("Exists: %d\n",exists);
	
	printf("\n--------------------------\n");
	printf("US316: 'get amount of occupied positions in array.'\n\n");
	Coordinates coords[SIZE] = {{1,1,1}, {3,2,0}, {4,4,4}, {5,8,4}};
	int amountOccupied = totalOccupied(coords);
    printf("There are %d occupied positions in this array\n\n",amountOccupied);

    return 0;
}
