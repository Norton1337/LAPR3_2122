#include "utility.h"
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
