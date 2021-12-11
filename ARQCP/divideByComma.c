#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define N_ELEM 5
int * divideByComma(char *pointer){
    int counter = 0;

    int static arr[N_ELEM];
    while (pointer != NULL) {
            int a = atoi(pointer);
            arr[counter] = a;
            //printf("%d\n", counter);
            pointer = strtok (NULL, ",");
            counter++;
    }

    return arr;
}
