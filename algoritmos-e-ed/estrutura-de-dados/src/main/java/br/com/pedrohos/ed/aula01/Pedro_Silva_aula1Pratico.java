package br.com.pedrohos.ed.aula01;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 */
public class Pedro_Silva_aula1Pratico {
    
    private static final String ITERATIVO = "iterativo";
    private static final String RECURSIVO = "recursivo";
    
    public Map<String, List<Long>> tempos;
    
    public Pedro_Silva_aula1Pratico() {
        tempos = new HashMap<>();
        tempos.put(RECURSIVO, new ArrayList<>());
        tempos.put(ITERATIVO, new ArrayList<>());
    }
    
    public static void main(String[] args) {
        
        Pedro_Silva_aula1Pratico pratica = new Pedro_Silva_aula1Pratico();
        
        //Fiz esse laço apenas para coletar o tempo
        for(int i = 0; i < 10; i++) {
            List<Integer> randomNumbers = pratica.generateRandomInt(100000);
            pratica.ehPrimoIterativo(randomNumbers);
            pratica.ehPrimoRecursivo(randomNumbers);
            System.out.println("\n");
        }
        
        pratica.imprimeTempo();
    }
    
    /**
     * Percorre uma lista de inteiros, para saber se o valor é primo ou não, e também mostra o maior primo da listagem
     * @param values
     */
    public void ehPrimoRecursivo(List<Integer> values) {
        
        // 1 + 1 + 2 + 2n + 6n + 1 + 1 + 1 + 1 => 8 + 8n

        int maiorPrimo = 0; //1 
        
        Instant start = Instant.now(); //1
        
        for (Integer numero : values) { //2 + 2n
            if (verificaPrimoRecursivo(numero, null)) { // 6n => n + 5n de dentro do recursivo
                maiorPrimo = numero >= maiorPrimo ? numero : maiorPrimo; // n
            }
        }
        
        Instant end = Instant.now(); //1 
        long interval = Duration.between(start, end).toMillis(); //1
        tempos.get(RECURSIVO).add(interval); //1
        
        System.out.println("[RECURSIVO] O MAIOR PRIMO: " + maiorPrimo + " TEMPO DE EXECUÇÃO: " + interval + "ms"); //1

    }
    
    /**
     * Verifica se um número é primo ou não de forma recursiva;
     * @param numero
     * @param divisor
     * @return
     */
    public boolean verificaPrimoRecursivo(int numero, Integer divisor) {
        
        divisor = divisor == null ? 2 : divisor; //n
        
        if(numero < 2) { //n
            return false;
        }
        
        if(numero % divisor == 0) { //n
            return false;
        }
        
        /**
         * Em vez de verificar até n, podemos verificar até √n porque um fator 
         * maior de n deve ser um múltiplo do fator menor que já foi verificado.
         */    
        if (divisor == (int) Math.sqrt(numero)) {//n
            return true;
        }
        
        return verificaPrimoRecursivo(numero, divisor + 1); //n
        
    }
    
    /**
     * Método iterativo para verificar se é primo, e também mostra o maior primo na lista.
     * @param values
     */
    public void ehPrimoIterativo(List<Integer> values) {
        
        // 1 + 1 + 1 + 2 + 2n + 1 + 2 + 2n² + n² + n² + n + n + n + 1 + 1 + 1 + 1 = 11 + 5n + 4n²
        
        int maiorPrimo = 0; // 1
        boolean ehPrimo = true; // 1
        Instant start = Instant.now(); // 1
        
        for (int i=0; i < values.size(); i++) { // 2 + 2n
            
            int numero = values.get(i); //1
            
            if (numero < 2) {
                ehPrimo = false;
                
            } else {

                for (int divisor = 2; divisor < numero; divisor++) { //2 + 2n²
                    
                    /**
                     * Em vez de verificar até n, podemos verificar até √n porque um fator 
                     * maior de n deve ser um múltiplo do fator menor que já foi verificado.
                     */
                    if (divisor == (int) Math.sqrt(numero)) {
                        break;
                    }

                    if (numero % divisor == 0) { //n²
                        ehPrimo = false; //n²
                        break;
                    }
                }
            }
            
            if (ehPrimo) { //n
                maiorPrimo = numero >= maiorPrimo ? numero : maiorPrimo; //1n
            }
            
            ehPrimo = true; //n
        }
        
        Instant end = Instant.now(); //1
        long interval = Duration.between(start, end).toMillis(); //1
        tempos.get(ITERATIVO).add(interval); //1
        
        System.out.println("[ITERATIVO] O MAIOR PRIMO: " + maiorPrimo + " TEMPO DE EXECUÇÃO: " + interval + "ms"); //1
    }
    
    /**
     * Gera uma lista de inteiros num range entre 1 e o tamanho da lista * um número aleatório até 100
     * @param size
     * @return
     */
    public List<Integer> generateRandomInt(int size) {
        
        List<Integer> values = new ArrayList<>();
        int max = size * ThreadLocalRandom.current().nextInt(100);
        
        while(size != 0) {
            int valor = ThreadLocalRandom.current().nextInt(max);
            
            if(valor != 0) {
                values.add(valor);
            }
            
            size--;
        }
        
        return values;
    }
    
    /**
     * Imprime o tempo coletado durante n repetições
     */
    private void imprimeTempo() {
        System.out.println("\n");
        tempos.forEach((k, v) -> {
            System.out.println("Método " + k + " em ms -->");
            v.forEach(System.out::println);
            System.out.println("\n");
        });
    }

}
