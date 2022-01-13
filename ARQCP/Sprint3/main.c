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
/*
 * Edit utility.h
 * if "cargo.txt" -> N_ELEM = 4 AND N_MAX = 5 
 * if "cargo2.txt" -> N_ELEM = 9 AND N_MAX = 10 
 * if changed then "make clean" before "make run"
 */
 
    filePointer = fopen("cargo2.txt", "r");
	
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
    
    printf("\n----------------------------------------------------\n");
    printf("US313: 'Fill matrix with each container's ID in its respective place.'\n\n");
    printMatrix(matrix);
    
    printf("\n----------------------------------------------------\n");
    printf("US314: 'Get amount of containers and free slots.'\n\n");
    long amountOfContainers = countContainers();
    int *amount = (int*) &amountOfContainers;
	printf("There are %d containers.\n",*amount);
	printf("There are %d free slots.\n",*(amount+1));
	
	printf("\n----------------------------------------------------\n");
	printf("US315: 'Verify if container exists, 1 if it does, 0 otherwise.'\n\n");
	int exists = containerExists(3,5,7);
	printf("Exists: %d.\n",exists);
	
	printf("\n----------------------------------------------------\n");
	printf("US316: 'Get amount of occupied positions in array.'\n\n");
	Coordinates coords[SIZE] = {{1,8,0}, {6,7,8}, {2,2,6}, {7,0,2}};
	int amountOccupied = totalOccupied(coords);
    printf("There are %d occupied positions in this array.\n\n",amountOccupied);

    return 0;
}
