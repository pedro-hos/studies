#include <stdio.h>
#include <stdlib.h>

#define INICIO 1
#define MAXTAM 1000

typedef int TChave;
typedef int TApontador;

typedef struct {
	TChave Chave;
} TItem;

typedef struct {
	TItem Item[MAXTAM];
	TApontador Primeiro, Ultimo;
} TLista;

void TLista_Inicia(TLista *pLista){
	pLista->Primeiro = INICIO;
	pLista->Ultimo = pLista->Primeiro;
}

int TLista_EhVazia(TLista *pLista) {
	return (pLista->Primeiro == pLista->Ultimo);
}

int TLista_Insere(TLista *pLista, TItem x) {
	
	if(pLista->Ultimo > MAXTAM) {
		return 0;
	}
	
	pLista->Item[pLista->Ultimo-1] = x;
	pLista->Ultimo++;
	return 1;
}

void main() {
	printf("###### LISTA LINEAR POR ARRANJO ######\n");
	TLista *pLista = (TLista *) malloc(sizeof(TLista));
	TLista_Inicia(pLista);
	printf("%d\n A Lista é vazia? ",TLista_EhVazia(pLista));
	
	TItem itemA;
	itemA.Chave = 10;
	TLista_Insere(pLista, itemA);

	printf("%d\n A Lista é vazia? ", TLista_EhVazia(pLista));
	printf("\n");

	printf("%d", itemA.Chave);
}

