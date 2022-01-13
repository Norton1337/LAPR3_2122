#include <stdio.h>
#include "utility.h"
void printArray(int arr[]){
    printf("[");
    for(int i = 0; i < N_ELEM; i++)
      printf("%d ", arr[i]);
    printf("]\n");
}
