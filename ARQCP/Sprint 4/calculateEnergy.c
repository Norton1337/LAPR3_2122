#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "info.h"
#include "checkRefrigerated.h"

double calculateEnergy(Containers* containerArray, int size, int x, int y, int z, float hours){


	if(checkRefrigerated(containerArray,size, 0,0,2)!=1)
		return -1;


	int k=0;
	for(k=0;k< size;k++){
		if(x==containerArray[k].x && y==containerArray[k].y && z==containerArray[k].z){
			break;
		}
	}
	
	if(k==size){
		return -2;
	}

	int listSize = 14;
	
	char *materialList[14]= {"Air","Pine","Oak","Ice","Water","Glass",
							 "Concrete","Lead","Steel","Iron","Aluminum",
							 "Gold","Copper","Silver"};
	double conductivityList[14] = {0.026,0.11,0.15,0.592,0.609,0.8,0.55,35.3,
								  46,80.4,237,318,401,429};
								  
	Containers thisContainer = containerArray[k];

	if(thisContainer.containerNumber==0)
		return -2;
			
	double resistencia=0;
	double area= (thisContainer.width*thisContainer.length)*2 + 
				(thisContainer.width*thisContainer.height)*2 +
				(thisContainer.height*thisContainer.length)*2;
	
	for(int i=0;i<thisContainer.materialAmount;i++){
		
		float conductivity=0;
		
		for(int k=0;k<listSize;k++){
			if(strcmp(thisContainer.materials[i].materialType,materialList[k])==0){
				conductivity = conductivityList[k];				
				resistencia+=thisContainer.materials[i].espessura/(conductivity*area);
				break;
			}
		}
	}
	
	double temperaturaExterior = 20.0;
	
	double fluxoCalor = (temperaturaExterior - thisContainer.temperaturaInterior) /
						resistencia;
	
	
	double energyRequired = (hours*3600) * fluxoCalor;
	
	return energyRequired;
}
