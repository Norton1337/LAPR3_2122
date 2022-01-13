#include <stdlib.h>
#include "info.h"

int freeMemory(Containers*** matrix, int x,int y,int z){

	for (int i = 0; i < x; i++)
    {
        for (int j = 0; j < y; j++) {
            free(matrix[i][j]);
        }
        free(matrix[i]);
    }
    free(matrix);

	return 1;
}
