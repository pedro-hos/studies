# Análise de Algoritmos e Estrutura de Dados - Aula 01

### Qual a diferença entre um altoritmo e um programa?

* Algoritmo:

  Sequência de ações executáveis para solucionar um determinado problema.

* Estrutura de Dados

  Conjunto de dados que representa uma situação real. Abstração da realidade, algoritmos e estrutura de dados estão intimamente ligados;

### Representação de dados

Podem ser representados (estruturados) de diferentes maneiras, a escolha da representação é determinada pelas operações utilizadas sobre eles. Podemos ter uma representação numérica de palitinhos, ela não é boa para grandes números, por exemplo, para isso podemos usar a represetação por números decimais.

### Programas

É a formulação concreta de uma algoritmo abstrato. São feitos em alguma linguagem entendida pelo computador.

### Tipos abstratos de dados (TADs)

Uma lib que expõe algumas operações ao usuário da TAD. Qualquer modificação fica exclusivamente para o "dono" da TAD.

Exemplo:

* Implementação da TAD

```
void Insere(int x, Lista L) {
  for(...) {
    ...
  }
}
```

* Usuário da TAD

```
void main() {
  Lista L;
  Insere(1, L);
}
```

## Análise de Algoritmo

Consiste em verificar o __tempo__ de execução e o __espaço__ ocupado de um determinado algoritmo. Podemos utilizar esta análise para determinar o algoritmo mais adequado para resolver um problema.

**O tempo de um algoritmo depende geralmente do tamanho de sua entrada**

Fazer esta análise, pode ser complicada já que o resultado pode depender do sistema operacional utilizado, ou então do compilador que esta executando o algoritmo. Apesar dessas variáveis, ainda é possivel determinar uma ordem de grandesa e estipular o tempo de execução!

Para análise, é possivel analisar:

* Um **algoritmo** particular;

  Se verifica qual o custo de um dado algoritmo para resolver um determinado problema. Nesta análise, é levado em consideração o número de vezes que cada parte de um algoritmo leva para ser executada (**tempo**) e também a quantidade de memória necessária para esta execução (**espaço**).

  Vale resaltar que o estudo do **espaço** não é muito discutida nos tempos atuais, já que temos uma quantide maior de espaço e é possível "comprar" mais espaço com mais facilidade.

* Uma **classe** de algoritmos;

Estuda-se toda uma família de algoritmo para determinar qual melhor se adequa para realizar uma determinda tarefa.

Ainda sobre a análise de algoritmos é comum ignorar custo de algumas operações e focar apenas nas mais significativas. Como por exemplo num algoritmo de ordenação, focamos no número de comparações realizados e não nas outras partes do algoritmo.

Para medir o custo de execução de um algoritmo é comum definir uma função de custo ou função de complexidade, `f;` `f(n)` é a medida do tempo necessário para executar um algoritmo para um problema de tamanho `n`;

Vale lembrar que a complexidade de tempo não diz a respeito do tempo, mas sim representa o número de vezes que uma determinada operação é executada.

Por exemplo, considera o seguinte código de soma de inteiros de um vetor até um determinado `n`:

```
int soma(int *vetor, int n) {
  int soma = 0;
  for(i = 0, i < n, i++) {
    soma = soma + vetor[i];
  }
}
```

Logo o custo de execução desse algoritmo depende da entrada de `n`, dizemos então que o custo é `f(n) = n`.

Mas, podemos melhorar esse altoritmo, da seguinte forma:


```
int soma(int *vetor, int n) {
  int soma = vetor[0];
  for(i = 1, i < n, i++) {
    soma = soma + vetor[i];
  }
}
```
Dessa maneira, o resultado da custo de execução será `f(n) = n - 1`. Já que estamos deternando que o valor da soma inicia com o primeiro elemento do vetor, e o mesmo não entrará no laço `for()`;

São 3 os casos a serem analisados:

* Melhor Caso: Representa o menor tempo de execução sobre todas as entradas de tamanho `n`;
* Pior Caso:Representa o maior tempo de execução sobre todas as entradas de tamanho `n`;
* Caso médio: Representa o tempo médio de execução sobre todas as entradas de tamanho `n`;

`Melhor Caso ≤ Caso Médio ≤ Pior Caso`

Considere o problema de achar um arquivo dentro de uma lista de arquivos passando uma chave como parâmetro. Em uma **busca sequencial** que é a mais simples teremos:

* Melhor caso: `f(n) = 1`
* Pior caso: `f(n) = n`
* Caso Médio: Média aritmética do pior caso e do melhor caso, logo temos que `f(n) = (n+1)/2`

Vamos analisar o seguinte caso:

```
void MaxMin1(int* A, int n, int* pMax, int* pMin) {
  int i;
  *pMax = A[0];
  *pMin = A[0];
  for (i = 1; i < n; i++) {
      if (A[i] > *pMax) *pMax = A[i];
      if (A[i] < *pMin) *pMin = A[i];
    }
}
```

Aqui temos que o custo da execução será `(n-1)` para o primeiro `if` e `(n-1)` para o segundo `if` logo `f(n) = 2(n-1)`. Como para todo `n` da entrada os 2 `ifs` serão executados, temos então que:

|Algorítmo|Melhor Caso|Pior Caso|Caso Médio|
|---------|-----------|---------|----------|
|MaxMin1|`f(n)=2(n-1)`|`f(n)=2(n-1)`|`f(n)=2(n-1)`|

Existe uma maneira de melhorar o algoritmo, que é impedindo que que a segunda comparação: `if (A[i] < *pMin) *pMin = A[i];` seja feita, caso a primeira: `if (A[i] > *pMax) *pMax = A[i];` satisfaça. Para isso, colocamos um `else`:

```
void MaxMin1(int* A, int n, int* pMax, int* pMin) {
  int i;
  *pMax = A[0];
  *pMin = A[0];
  for (i = 1; i < n; i++) {
      if (A[i] > *pMax) {
        *pMax = A[i];
      } else if (A[i] < *pMin) {
        *pMin = A[i];
      }
    }
}
```

Neste caso vamos pensar no **Melhor Caso**, uma lista ordenada em ordem **crescente**: `int * A = [1,2,3,4,5,6 ...]` Neste caso, o próximo elemento da lista, sempre será maior que o anterior, sendo assim a segunda condição nunca é chamada, já que o `*pMin` já inicia com o primeiro elemento da lista, e a condição `else` impede que o segundo `if` seja chamado sem necessidade. Temos então que: `f(n) = (n-1)`

Pensando agora no **Pior Caso**, numa lista ordenada de forma **decrescente** `int * A = [10,9,8,7 ...]`, neste caso específico, a primeira e a segunda condições serão chamadas, logo `f(n) = 2(n-1)`

Já no **Caso Médio**, vamos pensar que ele vai passar metade das vezes pelo Melhor Caso e metade das vezes pelo Pior Caso, temos então:

```
1/2(n-1) + 1/2(n-1)2 = (2n-2 + n -2)/2 = (3n - 3)/2 = 3(n-1)/2
```

Por fim, teremos:

|Algorítmo|Melhor Caso|Pior Caso|Caso Médio|
|---------|-----------|---------|----------|
|MaxMin1|`f(n)=2(n-1)`|`f(n)=2(n-1)`|`f(n)=2(n-1)`|
|MaxMin2|`f(n)=n-1`|`f(n)=2(n-1)`|`f(n)=3(n-1)/2`|
