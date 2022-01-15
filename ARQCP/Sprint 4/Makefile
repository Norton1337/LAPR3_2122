main: main.o freeMemory.o allocateMemory.o printArray.o getSize.o divideByComma.o importContainers.o printContainerInfo.o checkRefrigerated.o calculateEnergy.o enoughEnergy.o
	gcc -Wall -g main.o freeMemory.o allocateMemory.o printArray.o getSize.o divideByComma.o importContainers.o printContainerInfo.o checkRefrigerated.o calculateEnergy.o enoughEnergy.o -o main

main.o: main.c info.h freeMemory.h allocateMemory.h printArray.h getSize.h importContainers.h printContainerInfo.h checkRefrigerated.h calculateEnergy.h enoughEnergy.h
	gcc -Wall -g -c main.c -o main.o

freeMemory.o: freeMemory.c info.h
	gcc -Wall -g -c freeMemory.c -o freeMemory.o

allocateMemory.o: allocateMemory.c info.h
	gcc -Wall -g -c allocateMemory.c -o allocateMemory.o

printArray.o: printArray.c info.h
	gcc -Wall -g -c printArray.c -o printArray.o

getSize.o: getSize.c
	gcc -Wall -g -c getSize.c -o getSize.o

divideByComma.o: divideByComma.c
	gcc -Wall -g -c divideByComma.c -o divideByComma.o
	
importContainers.o : importContainers.c info.h
	gcc -Wall -g -c importContainers.c -o importContainers.o

printContainerInfo.o : printContainerInfo.c info.h
	gcc -Wall -g -c printContainerInfo.c -o printContainerInfo.o

checkRefrigerated.o: checkRefrigerated.s
	gcc -Wall -g -c checkRefrigerated.s -o checkRefrigerated.o
	
calculateEnergy.o: calculateEnergy.c checkRefrigerated.h
	gcc -Wall -g -c calculateEnergy.c -o calculateEnergy.o
	
enoughEnergy.o: enoughEnergy.c calculateEnergy.h
	gcc -Wall -g -c enoughEnergy.c -o enoughEnergy.o
run: main
	./main

clean:
	rm *.o main
