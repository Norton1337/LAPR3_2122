#include <stdlib.h>
#include "info.h"

int allocateMemory(Containers*** matrix,int x,int y,int z){
	
	if (matrix == NULL)
    {
        return 0;
    }
    
    for(int i=0; i<x; i++){
		
		matrix[i] = (Containers**) calloc(y, sizeof(Containers*));
		if (matrix[i] == NULL)
		{
			return 0;
		}
		
		for(int k=0; k<y;k++){
		
			matrix[i][k] = (Containers*) calloc(z, sizeof(Containers));
			if (matrix[i][k] == NULL)
			{
				return 0;
			}	
			
		}
		
	}
	
	return 1;
	
}
