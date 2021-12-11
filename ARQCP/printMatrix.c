#include <stdio.h>
#include "utility.h"
void printMatrix(int matrix[N_MAX][N_MAX][N_MAX]){
  printf("\nMatrix is :\n");
    for(int x=0;x<N_MAX;x++)
    {
        for(int y=0;y<N_MAX;y++)
        {
            printf("|");
            for(int z=0;z<N_MAX;z++){
                printf("%d\t",matrix[x][y][z]);
            }
            
        }
        printf("\n");
    }
}
