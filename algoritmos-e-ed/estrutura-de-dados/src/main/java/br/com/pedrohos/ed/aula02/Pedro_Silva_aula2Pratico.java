package br.com.pedrohos.ed.aula02;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Pedro Silva <pedro-hos@outlook.com>
 *
 */
public class Pedro_Silva_aula2Pratico {

    /**
     * 
     */
    private static final int _100000 = 100000;

    public static void main(String[] args) {
        
        Pedro_Silva_aula2Pratico pratica = new Pedro_Silva_aula2Pratico();
        
        for(int i = 0; i < 20; i++) {
            List<Integer> randomNumbers = pratica.generateRandomInt(_100000);
            
            System.out.println("##### Busca Sequencial #####\n");
            pratica.buscaSequencial(randomNumbers, 9);
            pratica.buscaSequencial(randomNumbers, 99999);
            
            System.out.println("\n##### Busca Binaria #####\n");
            pratica.buscaBinaria(randomNumbers, 0, randomNumbers.size(), 9, Instant.now());
            pratica.buscaBinaria(randomNumbers, 0, randomNumbers.size(), 99999, Instant.now());
        }
    }
    
    public int buscaBinaria(List<Integer> listaNumeros, int min, int max, int valorProcurado, Instant start) {
        
        if (min == max - 1) {
            long interval = Duration.between(start, Instant.now()).toNanos();
            System.out.println("valor " + valorProcurado + " não encontrado no vetor! E a busca levou " + interval + " nanosegundos");
            return -1;
        }
        
        int media = (max + min) / 2;
        Integer numeroLista = listaNumeros.get(media);
        
        if(numeroLista == valorProcurado) {
            long interval = Duration.between(start, Instant.now()).toNanos();
            System.out.println("valor " + numeroLista + " encontrado no vetor na posição " + media + " em " + interval + " nanos");
            return media;
        }

        if (numeroLista < valorProcurado)
            return buscaBinaria(listaNumeros, media, max, valorProcurado, start);
        else
            return buscaBinaria(listaNumeros, min, media, valorProcurado, start);
    }
    
    public int buscaSequencial(List<Integer> listaNumeros, int valor) {
        
        Instant start = Instant.now();
        
        for (int index = 0; index < listaNumeros.size(); index++) {
            if(listaNumeros.get(index) == valor) {
                Instant end = Instant.now();
                long interval = Duration.between(start, end).toNanos();
                System.out.println("valor " + valor + " encontrado no vetor na posição " + index + " em " + interval + " nanos");
                return index;
            }
        }
        
        Instant end = Instant.now(); //1 
        long interval = Duration.between(start, end).toNanos();
        
        System.out.println("valor " + valor + " não encontrado no vetor! E a busca levou " + interval + " nanosegundos");
        return -1;
        
    }
    
    /**
     * Gera uma lista de inteiros num range entre 1 e o tamanho da lista * um número aleatório até 100
     * @param size
     * @return
     */
    public List<Integer> generateRandomInt(int size) {
        
        List<Integer> values = new ArrayList<>();
        int max = size;
        
        while (size != 0) {
            int valor = ThreadLocalRandom.current().nextInt(max);
            values.add(valor);
            size--;
        }

        Collections.sort(values);
        
        return values;
    }

}
