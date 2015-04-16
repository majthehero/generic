#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/types.h>

#define BUFF_SIZE 256

int main(int argc, char* argv[])
{
	char* dir_working = NULL;
	int _status = 0;
	if (!getcwd(dir_working, BUFF_SIZE))
	{
		perror(sys_errlist[errno]);
	}

	printf("CWD: %s\n", dir_working);
	
	if (argc < 2)
	{
		printf("Run %s as %s <path>\nNot enough arguments",
				argv[0], argv[0]);
		dir_working = "/";
	} 
	else
	{
		dir_working = argv[1];
	}
	if (chdir(dir_working))
	{
		perror(sys_errlist[errno]);		
		exit(2);
	}

	printf("CWD: %s\n", dir_working);

	// list dirents
	DIR* dirp_working = NULL;
	if ((dirp_working = opendir(dir_working)) == NULL)
	{
		perror(sys_errlist[errno]);
		exit(3);
	}
	struct dirent * dire_working = malloc(sizeof(struct dirent));
	while ((dire_working = readdir(dirp_working)) != NULL)
	{
		printf(": %s\n", dire_working->d_name);
	}
	/*else
	{
		perror(sys_errlist[errno]);
	}*/

	printf("kthxbye\n");
}

