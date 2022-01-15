#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "info.h"


int importContainers(Containers* arrayContainers){
	
	Containers temp;
	int k = 0;
	FILE* filePointer;
	filePointer = fopen("containersNew.txt", "r");
	
	if(filePointer == NULL) {
		return 0;
	}
	char type[20];
	char materialsAndThickness[100];
	char *array[20];
	char *materials[10];
	char *thickness[10];
	while(fscanf(filePointer,"%d,%d,%d,%d,%f,%f,%f,%f,%d,%[^,],{%[^}]}",
				&temp.x,
				&temp.y,
				&temp.z,
				&temp.containerNumber,
				&temp.temperaturaInterior,
				&temp.length,
				&temp.width,
				&temp.height,
				&temp.load,
				type,
				materialsAndThickness) != EOF)
	{

		temp.type = (char*) calloc(strlen(type), sizeof(char));
		if(temp.type == NULL){
			return 0;
		}
		strcpy(temp.type, type);
			
		
		char * token = strtok(materialsAndThickness, "|");
		int j=0;
		while( token != NULL ) {
			array[j++]=token;
			token = strtok(NULL, "|");
		} 
		
		
		
		token = strtok(array[0], ",");
		j=0;
		while( token != NULL ) {
			materials[j++]=token;
			token = strtok(NULL, ",");
			
		}
		
		token = strtok(array[1], ",");
		j=0;
		while( token != NULL ) {
			thickness[j++]=token;
			token = strtok(NULL, ",");
		}
		
		
		
		temp.materialAmount=j;
		temp.materials = (Materials*) calloc(temp.materialAmount, sizeof(Materials));
		
		for(int i=0;i<j;i++){

			temp.materials[i].materialType = (char*) calloc(strlen(materials[i]), sizeof(char));
			strcpy(temp.materials[i].materialType, materials[i]);
			temp.materials[i].espessura = atof(thickness[i]);
		}
		
		arrayContainers[k]=temp;
		k++;
	}
				
	fclose(filePointer);		
	
	
	return 1;
}


/*int importContainers(Containers*** matrix){
	int x=0;
	int y=0;
	int z=0;
	
	Containers temp;

	FILE* filePointer;
	filePointer = fopen("containersNew.txt", "r");
	
	if(filePointer == NULL) {
		return 0;
	}
	char materialsAndThickness[100];
	char *array[20];
	char *materials[10];
	char *thickness[10];
	while(fscanf(filePointer,"%d,%d,%d,%d,%f,%f,%f,%f,%[^,],%[^,],{%[^}]}",
				&x,
				&y,
				&z,
				&temp.containerNumber,
				&temp.temperaturaInterior,
				&temp.length,
				&temp.width,
				&temp.height,
				temp.type,
				temp.load,
				materialsAndThickness) != EOF)
	{
		
		
		
		char * token = strtok(materialsAndThickness, "|");
		int j=0;
		while( token != NULL ) {
			array[j++]=token;
			token = strtok(NULL, "|");
		}
		

		token = strtok(array[0], ",");
		j=0;
		while( token != NULL ) {
			materials[j++]=token;
			token = strtok(NULL, ",");
			
		}
		
		token = strtok(array[1], ",");
		j=0;
		while( token != NULL ) {
			thickness[j++]=token;
			token = strtok(NULL, ",");
		}

		temp.materialAmount=j;
		for(int i=0;i<j;i++){
			memcpy(temp.materials[i].materialType,materials[i], 20);
			temp.materials[i].espessura = atof(thickness[i]);
		}
		
		matrix[x][y][z]=temp;
		
	}
				
	fclose(filePointer);		
	
	
	return 1;
}*/
