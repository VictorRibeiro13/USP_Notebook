/*********************************************************************/
/**   ACH2023 - Algoritmos e Estruturas de Dados I                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**   94 - Prof. Luciano Antonio Digiampietri                       **/
/**                                                                 **/
/**   EP2 - Fila Preferencial                                       **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**                                                                 **/
/*********************************************************************/

#include "filapreferencial.h"

PFILA criarFila(){
  PFILA res = (PFILA) malloc(sizeof(FILAPREFERENCIAL));
  res->inicioPref = NULL;
  res->fimPref = NULL;
  res->inicioGeral = NULL;
  res->fimGeral = NULL;
  return res;
}

int tamanho(PFILA f){
  PONT atual = f->inicioGeral;
  int tam = 0;
  while (atual) {
    atual = atual->prox;
    tam++;
  }
  return tam;
}

int tamanhoFilaPreferencial(PFILA f){
  PONT atual = f->inicioPref;
  int tam = 0;
  while (atual) {
    atual = atual->prox;
    tam++;
  }
  return tam;
}

PONT buscarID(PFILA f, int id){
  PONT atual = f->inicioGeral;
   while (atual) {
    if (atual->id == id) return atual;
    atual = atual->prox;
  }
  return NULL;
}

void exibirLog(PFILA f){
  int numElementos = tamanho(f);
  printf("\nLog fila geral [elementos: %i] - Inicio:", numElementos);
  PONT atual = f->inicioGeral;
  while (atual){
    printf(" [%i;%i]", atual->id, atual->ehPreferencial);
    atual = atual->prox;
  }
  printf("\n");
  numElementos = tamanhoFilaPreferencial(f);
  printf("\nLog fila preferencial [elementos: %i] - Inicio:", numElementos);
  atual = f->inicioPref;
  while (atual){
    printf(" [%i;%i]", atual->id, atual->ehPreferencial);
    atual = atual->prox;
  }
  printf("\n\n");
}


bool inserirPessoaNaFila(PFILA f, int id, bool ehPreferencial) {
    if (id < 0 || buscarID(f, id) != NULL) return false;

    PONT person = (PONT) malloc(sizeof(ELEMENTO));
    person->id = id;
    person->ehPreferencial = ehPreferencial;
    person->prox = NULL;

    if (f->inicioGeral == NULL) {
        f->inicioGeral = person;
        f->fimGeral = person;
    } else {
        f->fimGeral->prox = person;
        f->fimGeral = person;
    }

    if (ehPreferencial) {
      PONT prefPerson = (PONT) malloc(sizeof(ELEMENTO));
      prefPerson = person;

      if (f->inicioPref == NULL) {
          f->inicioPref = prefPerson;
          f->fimPref = prefPerson;
      } else {
        f->fimPref->prox = prefPerson;
        f->fimPref = prefPerson;
      }
    }
    return true;
}

bool atenderPrimeiraDaFilaPreferencial(PFILA f, int* id) {
    if (tamanho(f) == 0) return false;

    if (tamanhoFilaPreferencial(f) != 0) {
        *id = f->inicioPref->id;

        PONT prefPersonToRemove = f->inicioPref;
        f->inicioPref = f->inicioPref->prox;
        free(prefPersonToRemove);

        PONT ant = f->inicioGeral;
        PONT atual = f->inicioGeral;

        while (atual) {
          if (atual->id == *id) {
            if (atual != f->inicioGeral) {
              ant->prox = atual->prox;
              free(atual);
              break;
            }
            f->inicioGeral = f->inicioGeral->prox;
            free(atual);
            break;
          }
          ant = atual;
          atual = atual->prox;
        }
        return true;
    }
    *id = f->inicioGeral->id;
    PONT temp = f->inicioGeral;
    f->inicioGeral = f->inicioGeral->prox;
    free(temp);
    
    return true;
}

bool atenderPrimeiraDaFilaGeral(PFILA f, int* id) {
  if (tamanho(f) == 0) return false;
  
  if (f->inicioGeral->ehPreferencial == false) {
    *id = f->inicioGeral->id;
    PONT temp = f->inicioGeral;
    f->inicioGeral = f->inicioGeral->prox;
    free(temp);
    return true;
  }
  *id = f->inicioGeral->id;
  PONT temp = f->inicioGeral;
  f->inicioGeral = f->inicioGeral->prox;
  free(temp);
  PONT ant = f->inicioGeral;
  PONT atual = f->inicioGeral;
  while (atual) {
    if (atual->id == *id) {
      if (atual != f->inicioPref) {
        ant->prox = atual->prox;
        free(atual);
        break;
      }
      f->inicioPref = f->inicioPref->prox;
      free(atual);
      break;
    }
    ant = atual;
    atual = atual->prox;
  }
  return true;
}

bool desistirDaFila(PFILA f, int id){
  bool resposta = false;

  /* COMPLETAR */

  return resposta;
}

