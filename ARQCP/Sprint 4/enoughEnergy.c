#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "info.h"
#include "calculateEnergy.h"

int enoughEnergy(Containers * containerArray, int size, double providedEnergy){

	double requiredEnergy=0;
	double thisEnergy=0;
	for(int i=0;i<size;i++){
		thisEnergy=calculateEnergy(containerArray,size,containerArray[i].x,containerArray[i].y,containerArray[i].z,1.0);
		if(thisEnergy<0)
			return -1;
		requiredEnergy+=thisEnergy;
	}
	
	if(requiredEnergy>providedEnergy){
		return 0;
	}else{
		return 1;
		
	}
}
