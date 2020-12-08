/*********************************************************************/
/**   ACH2023 - Algoritmos e Estruturas de Dados I                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**   94 - Prof. Luciano Antonio Digiampietri                       **/
/**                                                                 **/
/**   EP3 - Fila de Prioridade (utilizando heap)                    **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro               11917559              **/
/**                                                                 **/
/*********************************************************************/

#include "filaDePrioridade.h"
#include <math.h>

PFILA criarFila(int max) {
  PFILA res = (PFILA) malloc(sizeof(FILADEPRIORIDADE));
  res->maxElementos = max;
  res->arranjo = (PONT*) malloc(sizeof(PONT)*max);
  res->heap = (PONT*) malloc(sizeof(PONT)*max);
  int i;
  for (i=0;i<max;i++) {
    res->arranjo[i] = NULL;
    res->heap[i] = NULL;
  }
  res->elementosNoHeap = 0;
  return res;
}

void exibirLog(PFILA f){
  printf("Log [elementos: %i]\n", f->elementosNoHeap);
  PONT atual;
  int i;
  for (i=0;i<f->elementosNoHeap;i++){
    atual = f->heap[i];
    printf("[%i;%f;%i] ", atual->id, atual->prioridade, atual->posicao);
  }
  printf("\n\n");
}

int tamanho(PFILA f) {
  return 1;
}

bool buscarElemento(PFILA f, int id) {
  return (id < 0 || id > f->maxElementos || f->arranjo[id] != NULL);
}


bool inserirElemento(PFILA f, int id, float prioridade) {
  if (buscarElemento(f, id)) return false;

  PONT newElement = malloc(sizeof(PONT));
  newElement->id = id;
  newElement->prioridade = prioridade;
  newElement->posicao = 0;

  f->arranjo[id] = newElement;

  if (f->elementosNoHeap == 0) {
    f->elementosNoHeap += 1;
    f->heap[0] = newElement;
    return true;
  }

  int index = f->elementosNoHeap;
  int fatherIndex = (index-1) < 0 ? 0 : (int) floor((index-1)/2);
  f->heap[index] = newElement;


  while (f->heap[fatherIndex]->prioridade < newElement->prioridade) {
    PONT atual = f->heap[index];
    PONT pai = f->heap[fatherIndex];

    f->heap[index] = f->heap[fatherIndex];
    f->heap[fatherIndex] = newElement;

    index = fatherIndex;
    fatherIndex = (int) floor((index-1)/2);
  }
  f->elementosNoHeap = f->elementosNoHeap + 1;

  return true;
}

bool aumentarPrioridade(PFILA f, int id, float novaPrioridade){
  bool res = false;
  
  /* COMPLETAR */
  
  return res;
}

bool reduzirPrioridade(PFILA f, int id, float novaPrioridade){
  bool res = false;
  
  /* COMPLETAR */
  
  return res;
}

PONT removerElemento(PFILA f){
  PONT res = NULL;
  
  /* COMPLETAR */
  
  return res;
}

bool consultarPrioridade(PFILA f, int id, float* resposta){
  bool res = false;
  
  /* COMPLETAR */
  
  return res;
}

