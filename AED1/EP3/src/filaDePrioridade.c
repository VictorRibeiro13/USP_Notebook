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

void exibirLog(PFILA f) {
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
  int size = 0;
  for (int i=0; i < f->maxElementos; i++) {
    if (f->heap[i] != NULL) size++;
  }

  return size;
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
    f->heap[index] = f->heap[fatherIndex];
    f->heap[fatherIndex] = newElement;

    f->heap[index]->posicao = index;
    f->heap[fatherIndex]->posicao = fatherIndex; 

    index = fatherIndex;
    fatherIndex = (int) floor((index-1)/2);
  }

  f->heap[index]->posicao = index;
  f->heap[fatherIndex]->posicao = fatherIndex;
  f->elementosNoHeap = f->elementosNoHeap + 1;

  return true;
}
  
bool aumentarPrioridade(PFILA f, int id, float novaPrioridade) {
  if (id < 0 || id > f->maxElementos || f->arranjo[id] == NULL) return false;
  PONT element = f->arranjo[id];
  if (element->prioridade >= novaPrioridade) return false;

  element->prioridade = novaPrioridade;

  int index = element->posicao;
  int fatherIndex = (index-1) < 0 ? 0 : (int) floor((index-1)/2);

  while (f->heap[fatherIndex]->prioridade < element->prioridade) {
    f->heap[index] = f->heap[fatherIndex];
    f->heap[fatherIndex] = element;

    f->heap[index]->posicao = index;
    f->heap[fatherIndex]->posicao = fatherIndex; 

    index = fatherIndex;
    fatherIndex = (int) floor((index-1)/2);
  }

  return true;
}


void changeChildrenElementPosition(PFILA f, int index) {
  int rightChildren = (int) floor((2 * index) + 1);
  int leftChildren = (int) floor((2 * index) + 2);
  PONT actualElement = f->heap[index];

  if (f->heap[rightChildren] != NULL && f->heap[rightChildren]->prioridade > actualElement->prioridade) { // pode dar errado, nesse caso colocar 2 ifs
    f->heap[index] = f->heap[rightChildren];
    f->heap[rightChildren] = actualElement;

    f->heap[index]->posicao = index;
    f->heap[rightChildren]->posicao = rightChildren;

    changeChildrenElementPosition(f, rightChildren);
  }

  if (f->heap[leftChildren] != NULL && f->heap[leftChildren]->prioridade > actualElement->prioridade && index != 0) {
    f->heap[index] = f->heap[leftChildren];
    f->heap[leftChildren] = actualElement;

    f->heap[index]->posicao = index;
    f->heap[leftChildren]->posicao = leftChildren;

    changeChildrenElementPosition(f, leftChildren);
  }
} 

bool reduzirPrioridade(PFILA f, int id, float novaPrioridade) {
  if (id < 0 || id > f->maxElementos || f->arranjo[id] == NULL) return false;
  if (f->arranjo[id]->prioridade < novaPrioridade) return false;

  f->arranjo[id]->prioridade = novaPrioridade;

  changeChildrenElementPosition(f, f->arranjo[id]->posicao);

  return true;
}

PONT removerElemento(PFILA f) {
  int heapLength = tamanho(f);
  if (heapLength == 0) return NULL;
  
  PONT elementRemoved = f->heap[0];
  f->heap[0] = f->heap[f->elementosNoHeap-1];
  f->heap[f->elementosNoHeap-1] = NULL;

  changeChildrenElementPosition(f, 0);

  f->elementosNoHeap--;
  f->arranjo[elementRemoved->id] = NULL;
  return elementRemoved;
}

bool consultarPrioridade(PFILA f, int id, float* resposta) {
  if (id < 0 || id > f->maxElementos || f->arranjo[id] == NULL) return false;
  *resposta = f->arranjo[id]->prioridade;
  return true;
}
