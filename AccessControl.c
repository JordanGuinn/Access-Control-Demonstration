/*  AccessControl.c
    Name: Jordan Guinn
    SID: 910293311

    File allows user to read to or write from
    various files within a directory, if they
    have permission to use such functionality

    COMPILE AND RUN THIS FILE
*/

#include <stdio.h>
#include <stdbool.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include "AccessControlLists.c"

    /* Read from file if permitted */
    void myRead(char buffer[], int bufSize, int file, int access)
    {
    	if(allfiles[file][access] == 'r' || allfiles[file][access] == 'x')
    	{
    		int fd = open(files[file], O_RDONLY);
    		int beenRead = read(fd, buffer, bufSize);
    		printf("%i bytes read.\nHere's your file:\n\n", beenRead);
    		for(int i = 0; buffer[i] != '\0'; i++)
    			printf("%c", buffer[i]);
    	}
    	else 
    		printf("You don't have permission to read this file.\n");
    }


  /* Write to file if permitted */
    void myWrite(int file, int access)
    {
    	if(allfiles[file][access] == 'w' || allfiles[file][access] == 'x')
    	{
    		int fd = open(files[file], O_WRONLY | O_RDONLY | O_APPEND);
    		char buffer[] = {"\nYou just wrote to a file.  CONGRATULATIONS."};
    		int written = write(fd, buffer, strlen(buffer));
    		printf("%i bytes written.\n", written);
    	}
    	else 
    		printf("You don't have permission to write to this file.\n");
    }

  /* Get user ID */
    int userAccess() 
    {
    	return getuid();
    }


/* Test myRead and myWrite functions */
    int main()
    {
    	int access, method, fd, file = 0;
    	initFilePermissions();

    	while(file != -1)
    	{
    		char buffer[10000];
    		printf("Please choose a file within the directory to edit.\n");
    		printf("For exploit.c, enter 1.\n");
    		printf("For findMessage.java, enter 2.\n");
    		printf("For hideMessage.java, enter 3.\n");
    		printf("For shellcode.c, enter 4.\n");
    		printf("For stack.c, enter 5.\n");
    		printf("To exit, enter 0.\n");

	/* Exit if requested */
    		scanf("%d", &file);
    		if(file == 0)
    			return 0;

    		printf("Would you like to read(1) or write(2)?\n");
    		scanf("%d", &method);

  /* Determine if user actually has permission to manipulate file how they'd like */
    		if(userAccess() == 0) 
    			access = 1;
    		else
    			access = 0;

	/* Call read or write function */
    		file = file - 1;
    		if(method == 1)
    			myRead(buffer, sizeof(buffer), file, access);
    		else if(method == 2)
    			myWrite(file, access);

    		printf("\n");
    	}
    	return 0;
    }