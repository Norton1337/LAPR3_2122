#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define N_ELEM 4
#define N_MAX 5

void putRestWithZero(int matrixCopy[N_MAX][N_MAX][N_MAX]){
    for(int x=0;x<N_MAX;x++)
    {
        for(int y=0;y<N_MAX;y++)
        {
            for(int z=0;z<N_MAX;z++){
                matrixCopy[x][y][z] = 0;
            }
        }
    }
}


void printMatrix(int matrix[N_MAX][N_MAX][N_MAX]){
  printf("\nMatrix is :\n");
    for(int x=0;x<N_MAX;x++)
    {
        for(int y=0;y<N_MAX-1;y++)
        {
            for(int z=0;z<N_MAX-1;z++){
                printf("%d\t",matrix[x][y][z]);
            }
        }
        printf("\n");
    }
}

void printArray(int arr[]){
    printf("[");
    for(int i = 0; i < N_ELEM; i++)
      printf("%d ", arr[i]);
    printf("]\n");
}

int * divideByComma(char *pointer){
    int counter = 0;

    static int arr[N_ELEM];
    while (pointer != NULL) {
            int a = atoi(pointer);
            arr[counter] = a;
            //printf("%d\n", counter);
            pointer = strtok (NULL, ",");
            counter++;
    }

    return arr;
}



int main()
{
    int matrix[N_MAX][N_MAX][N_MAX];
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


        printMatrix(matrix);
        printArray(arrayOfInt);
    }

    //Close file descriptor
    fclose(filePointer);
}


