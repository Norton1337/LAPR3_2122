#include <stdio.h>
#include "info.h"

int importContainers(Containers*** matrix,int x, int y, int z){
	int i = 0, j = 0, k = 0;
	int validScan = 0;
	FILE* filePointer;
	filePointer = fopen("containers.csv", "r");
	
	if(filePointer == NULL) {
		return 0;
	}
	
	for(i = 0; i < x; i++){
		for(j = 0; j < y; j++){
			for(k = 0; k < z; k++){
				
				validScan = fscanf(filePointer,"%d,%d,%d,%d,%d,%d,%d,%d,%d,%c,%s,%s,%s,%s",
						matrix[i][j][k].x,matrix[i][j][k].y,matrix[i][j][k].z,
						matrix[i][j][k].container_number,matrix[i][j][k].check_digit,
						matrix[i][j][k].container_payload,matrix[i][j][k].container_tare,
						matrix[i][j][k].container_gross,matrix[i][j][k].container_volume,
						matrix[i][j][k].iso_code,matrix[i][j][k].certificates,
						matrix[i][j][k].repairInfo,matrix[i][j][k].type,
						matrix[i][j][k].load);
						printf("Cheguei\n");
			}
		}
	}
	
	if(validScan == 0){
		return 0;
	}
	//ToDo, verificar se validScan corresponde ao numero de input desejado(14).
	return 1;
}
