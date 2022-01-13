#include <stdio.h>
#include <stdlib.h>
#include "info.h"
#include "freeMemory.h"
#include "allocateMemory.h"
#include "getMax.h"
#include "printMatrix.h"
#include "importContainers.h"
int main(){
	
	int x=0;
	int y=0;
	int z=0;
	
	getMax(&x,&y,&z);
	
	printf("max X: %d\n",x);
    printf("max Y: %d\n",y);
    printf("max Z: %d\n",z);
    
	Containers *** matrix = (Containers***) calloc(x, sizeof(Containers**));
	//Allocate Memory
	if (allocateMemory(matrix,x,y,z) == 0){
		fprintf(stderr, "Out of memory");
        exit(0);
	}
	
	if(importContainers(matrix,x,y,z) == 0){
		fprintf(stderr, "Out of memory");
        exit(0);
	}
	
	//Print containers id
	printMatrix(matrix,x,y,z);
    
    
    //Free memory
    freeMemory(matrix,x,y,z);
	
	
	return 0;
}
