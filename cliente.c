#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

/*Headers para o socket*/

//#include <sys/socket.h>
/*
#include <sys/types.h>

#ifdef __WIN32__
# include <winsock2.h>
#else
# include <sys/socket.h>
#include <netinet/in.h>
#endif
*/

#ifndef _WIN32
#include <netinet/in.h>
#include <unistd.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/time.h>
#include <sys/wait.h>
#else
#include <winsock.h>
#include <time.h>
#endif

#define PORTA 5555
#define LEN 4096

struct sockaddr_in local;
struct sockaddr_in remoto;

int main(){
	
	
	int sockfd; /*descritor de dados - ler e enviar*/
	int client;
	int len = sizeof(remoto);
	char buffer[4096];
	int slen;
	
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	
	/*Teste para ver se o socket funciona*/
	testeDeSocket(sockfd);
	
	local.sin_family 	= AF_NET;
	local.sin_port		= htons(PORTA);
	
	//DEFINIR O IP
	//local.sin_addr.s_addr		= inet_addr(IP);
	
	memset(local.sin_zero, 0x0, 8);
	
	testeBind( bind(sockfd, (struct sockaddr*)&local, sizeof(local) ) );
	
	//ouvindo o socket
	listen(sockfd, 1);
	
	if(client = acept(sockfd, (struct sockaddr*)&remoto, &len) == -1){
		perror("acept");
		exit(1);
	}
	
	
	
	
	
	strcpy(buffer, "Welcome\n\0");
	
	if(send(client, buffer, strlen(buffer), 0)){
		print("Aguardando a resposta do cliente");
		while(1){
			
			memset(buffer, 0x0, LEN);
			if(slen = recv(client, buffer, LEN, 0) > 0 ){	
				buffer[slen] = '\0';		
				printf("Mensagem Recebida: %s\n",buffer ); 
				close(client);
				break;
			} 
			
		}
	}
	
	close(sockfd);
	printf("Servidor Encerrado");
	
	return 0;
}

void testeDeSocket(int sockfd){
	if(sockfd == -1){
		perror("Erro na criação do Socket");
		exit(1);
	}else
		printf("Socket criado com Sucesso!\n");
}

void testeBind(Bind b){
	if(b == -1){
		perror("Bind");
		exit(1);
	}
}



	/*MACRO CONSTANTES
	DOMAIN ----------
	AF_INET - ipv4
	
	TYPE ------------
	SOCK_STREAM - TCP
	SOCK_DGRAM - UDP
	
	PROTOCOL--------
	0 -ip
	*/
