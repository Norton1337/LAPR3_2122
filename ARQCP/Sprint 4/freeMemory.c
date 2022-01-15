#include <stdlib.h>
#include "info.h"

int freeMemory(Containers* array, int size){

	for (int i = 0; i < size; i++){
		free(array[i].type);
		for(int j = 0; j < array[i].materialAmount; j++){
			free(array[i].materials[j].materialType);
		}
		free(array[i].materials);
    }
    free(array);

	return 1;
}
