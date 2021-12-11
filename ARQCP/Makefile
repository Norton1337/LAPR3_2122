main: main.o putRestWithZero.o printMatrix.o printArray.o divideByComma.o US314.o US315.o US316.o
	gcc -Wall -g main.o putRestWithZero.o printMatrix.o printArray.o divideByComma.o US314.o US315.o US316.o -o main

main.o: main.c putRestWithZero.h printMatrix.h printArray.h divideByComma.h US314.h US315.h US316.h
	gcc -Wall -g -c main.c -o main.o

putRestWithZero.o: putRestWithZero.c
	gcc -Wall -g -c putRestWithZero.c -o putRestWithZero.o
	
printMatrix.o: printMatrix.c
	gcc -Wall -g -c printMatrix.c -o printMatrix.o
	
printArray.o: printArray.c
	gcc -Wall -g -c printArray.c -o printArray.o
	
divideByComma.o: divideByComma.c
	gcc -Wall -g -c divideByComma.c -o divideByComma.o

US314.o: US314.s
	gcc -Wall -g -c US314.s -o US314.o
	
US315.o: US315.s
	gcc -Wall -g -c US315.s -o US315.o
	
US316.o: US316.s
	gcc -Wall -g -c US316.s -o US316.o
	
run: main
	./main

clean:
	rm *.o main
