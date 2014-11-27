/* 	BLPAccessControlLists.c
    Name: Jordan Guinn
  	SID: 910293311

	File creates permissions for all users on
	system and all files within directory
*/
    const char *files[5] = {"directory/exploit.c", "directory/findMessage.java", "directory/hideMessage.java", 
    "directory/shellcode.c", "directory/stack.c"};

    /* User and file permission levels */
    const int exploit = 2;
    const int findMessage = 3;
    const int hideMessage = 3;
    const int shellcode = 2;
    const int stack = 1;

    const int root_user = 3;
    const int user_501 = 2;
    const int other_user = 1;

    /* All user and file permisisons together */
    int allfiles[5] = { exploit, findMessage, hideMessage, shellcode, stack };;
    int allusers[3] = { other_user, user_501, root_user };