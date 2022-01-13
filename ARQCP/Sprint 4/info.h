#ifndef INFO_H
#define INFO_H


typedef struct {
	int x,y,z;
	int *container_number;
	int *check_digit;
	int *container_payload;
	int *container_tare;
	int *container_gross;
	int *container_volume;
	char iso_code[4];
	char *certificates;
	char *repairInfo;
	char *type;
	char *load;
} Containers;

#endif
