#include <stdio.h>
#define N_MAX 5
void printMatrix(int matrix[N_MAX][N_MAX][N_MAX]){
  printf("\nMatrix is :\n");
    for(int x=0;x<N_MAX;x++)
    {
        for(int y=0;y<N_MAX;y++)
        {
            for(int z=0;z<N_MAX;z++){
                printf("%d\t",matrix[x][y][z]);
            }
        }
        printf("\n");
    }
}
