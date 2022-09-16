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
        
        for(int i = 0; i < 10; i++) {
            List<Integer> randomNumbers = pratica.generateRandomInt(100000);
            pratica.ehPrimoIterativo(randomNumbers);
            pratica.ehPrimoRecursivo(randomNumbers);
            System.out.println("\n");
        }
        
        pratica.imprimeTempo();
    }
    

    public void ehPrimoRecursivo(List<Integer> values) {

        int maiorPrimo = 0;
        
        Instant start = Instant.now();
        
        for (Integer numero : values) {
            if (verificaPrimoRecursivo(numero, null)) {
                maiorPrimo = numero >= maiorPrimo ? numero : maiorPrimo;
            }
        }
        
        Instant end = Instant.now();
        long interval = Duration.between(start, end).toMillis();
        tempos.get(RECURSIVO).add(interval);
        
        System.out.println("[RECURSIVO] O MAIOR PRIMO: " + maiorPrimo + " TEMPO DE EXECUÇÃO: " + interval + "ms");

    }
    
    public boolean verificaPrimoRecursivo(int numero, Integer divisor) {
        
        divisor = divisor == null ? 2 : divisor;
        
        if(numero < 2) {
            return false;
        }
        
        if(numero % divisor == 0) {
            return false;
        }
        
        /**
         * Em vez de verificar até n, podemos verificar até √n porque um fator 
         * maior de n deve ser um múltiplo do fator menor que já foi verificado.
         */    
        if (divisor == (int) Math.sqrt(numero)) { 
            return true;
        }
        
        return verificaPrimoRecursivo(numero, divisor + 1);
        
    }
    
    public void ehPrimoIterativo(List<Integer> values) {
        
        int maiorPrimo = 0;
        boolean ehPrimo = true;
        Instant start = Instant.now();
        
        for (Integer valor : values) {
            
            if (valor < 2) {
                ehPrimo = false;
                
            } else {

                for (int divisor = 2; divisor < valor; divisor++) {
                    
                    /**
                     * Em vez de verificar até n, podemos verificar até √n porque um fator 
                     * maior de n deve ser um múltiplo do fator menor que já foi verificado.
                     */
                    if (divisor == (int) Math.sqrt(valor)) { 
                        break;
                    }

                    if (valor % divisor == 0) {
                        ehPrimo = false;
                        break;
                    }
                }
            }
            
            if (ehPrimo) {
                maiorPrimo = valor >= maiorPrimo ? valor : maiorPrimo;
            }
            
            ehPrimo = true;
        }
        
        Instant end = Instant.now();
        long interval = Duration.between(start, end).toMillis();
        tempos.get(ITERATIVO).add(interval);
        
        System.out.println("[ITERATIVO] O MAIOR PRIMO: " + maiorPrimo + " TEMPO DE EXECUÇÃO: " + interval + "ms");
    }
    
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
    
    private void imprimeTempo() {
        System.out.println("\n");
        tempos.forEach((k, v) -> {
            System.out.println("Método " + k + " em ms -->");
            v.forEach(System.out::println);
            System.out.println("\n");
        });
    }

}
