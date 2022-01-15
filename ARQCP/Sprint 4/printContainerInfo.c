#include <stdio.h>
#include <stdlib.h>
#include "printContainerInfo.h"
#include "info.h"

void printContainerInfo(Containers* containerArray, int size, int x, int y, int z){
	/*if(matrix[x][y][z].container_number != 0){
		exit(0);
	}*/
	int k=0;
	for(k=0;k< size;k++){
		if(x==containerArray[k].x && y==containerArray[k].y && z==containerArray[k].z){
			break;
		}
		
	}
	
	printf("Container Number: %d\n", containerArray[k].containerNumber);
	printf("Container Temperature: %f\n", containerArray[k].temperaturaInterior);
	printf("Container Length: %f\n", containerArray[k].length);
	printf("Container Width: %f\n", containerArray[k].width);
	printf("Container Height: %f\n", containerArray[k].height);
	printf("Type: %s\n", containerArray[k].type);
	printf("Load: %d\n", containerArray[k].load);
	printf("Materials [ %d ]: \n",containerArray[k].materialAmount);
	for(int i=0;i<containerArray[k].materialAmount;i++){
		printf("	Material: %s\n", containerArray[k].materials[i].materialType);
		printf("	Espessura: %f\n", containerArray[k].materials[i].espessura);
	}
	
}
