#include <stdio.h>
#define N_ELEM 4
void printArray(int arr[]){
    printf("[");
    for(int i = 0; i < N_ELEM; i++)
      printf("%d ", arr[i]);
    printf("]\n");
}
