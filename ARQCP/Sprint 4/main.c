#include <stdio.h>
#include <stdlib.h>
#include "info.h"
#include "freeMemory.h"
#include "allocateMemory.h"
#include "getSize.h"
#include "printArray.h"
#include "importContainers.h"
#include "printContainerInfo.h"
#include "checkRefrigerated.h"
#include "calculateEnergy.h"
int main(){
	
	int size = getSize();

	Containers *array = (Containers*) calloc(size, sizeof(Containers));

	//Allocate Memory
	/*if (allocateMemory(matrix,x,y,z) == 0){
		fprintf(stderr, "Out of memory");
        exit(0);
	}*/
	
	if(importContainers(array) == 0){
		fprintf(stderr, "Out of memory");
        exit(0);
	}

	printf("\n --- <US409> I wish to fill a dynamically reserved matrix in memory. ---\n\n");
	//Print containers id
	
	printf("Size of struct Containers: %d\n\n",sizeof(Containers));

	printArray(array,size);
    printContainerInfo(array,size,0,0,2);
    
    printf("\n\n --- <US410> Amount of needed energy to keep the container at its required temperature. ---\n\n");
    
    double energyRequired = calculateEnergy(array,size,0,0,2,1.0);
    if(energyRequired==-2)
		printf("Container doesn't exist\n");
    else if(energyRequired!=-1)
		printf("Energy Required for this container: %EJ\n",energyRequired);
	
	else
		printf("Container doesn't need refrigeration\n");
		
		
	printf("\n\n --- <US411>  Receive an alert when provided energy is not enough. ---\n\n");
	
	double providedEnergy = 400000000;
	
	int bool = enoughEnergy(array,size,providedEnergy);
	if(bool==-1)
		printf("Error has occured\n");
	else if(bool==0)
		printf(" !! Warning! Not enough power! !! --\n\n");
	else
		printf("All generators are being powered\n\n");
	
	
    //Free memory
    freeMemory(array,size);
	
	
	return 0;
}
