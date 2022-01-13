#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int * divideByComma(char *pointer){
    int counter = 0;

    int static arr[55];
    while (pointer != NULL) {
            int a = atoi(pointer);
            arr[counter] = a;
            //printf("%d\n", counter);
            pointer = strtok (NULL, ",");
            counter++;
    }

    return arr;
}
