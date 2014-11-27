/* 	AccessControlLists.c
	Name: Jordan Guinn
  	SID: 910293311

	Files creates read & write permissions for 
	root & non-root users, specifying certain 
	accessability for all files within directory

	Files organized alphabetically within directory
*/

	const char *files[5] = {"directory/exploit.c", "directory/findMessage.java", "directory/hideMessage.java", 
	"directory/shellcode.c", "directory/stack.c"};

	/* Read and write permissions for all files
	First value -> non-root; Second value -> root 
	r -> read; w -> write; x -> read and write */
	const char exploit[2] = {'r', 'x'};
	const char findMessage[2] = {'w', 'x'};
	const char hideMessage[2] = {'w', 'x'};
	const char shellcode[2] = {'r', 'x'};
	const char stack[2] = {'x', 'x'};
	char allfiles[5][2];

	/* All file permissions together */
	void initFilePermissions()
	{
		memcpy(allfiles[0], exploit, sizeof(exploit));
		memcpy(allfiles[1], findMessage, sizeof(findMessage));
		memcpy(allfiles[2], hideMessage, sizeof(hideMessage));
		memcpy(allfiles[3], shellcode, sizeof(shellcode));
		memcpy(allfiles[4], stack, sizeof(stack));
	}