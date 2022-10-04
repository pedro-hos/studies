package br.com.pedrohos.ed.aula03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Pedro Hos <pedro-hos@outlook.com>
 *  
 */
public class Pedro_Silva_aula3Pratico {

    /**
     * Gerar permutações sempre foi um problema importante na ciência da computação.
     * Neste problema, você terá de gerar todas as permutações de uma dada string,
     * em ordem lexicográfica crescente. Lembre-se que seu algoritmo deve ser
     * eficiente.
     * 
     * Entrada: A primeira linha da entrada contém um inteiro n, indicando o número
     * de strings que seguem. As próximas n linhas contém uma string cada. Cada
     * string conterá apenas caracteres alfanuméricos, e nunca conterá espaços. O
     * tamanho máximo de uma string é 10.
     * 
     * Saída: Para cada string da entrada, imprima todas as permutações possíveis da
     * string, em ordem lexicográfica crescente. Note que as strings devem ser
     * tratas como Case Sensitive (isto é, letras maiúsculas são diferentes das
     * minúsculas). Além disso, nenhuma permutação deve ser impressa mais de uma
     * vez. Uma linha em branco deve ser impressa após cada lista de permutações.
     * 
     * @param args
     */

    public static void main(String[] args) {
        permutacao(3, "ab", "abc", "bca");
        
        values.forEach((k,v) -> {
            for(int i = 0; i < v.length; i++) {
                System.out.print(v[i] + "\n");
            }
            
            System.out.print("\n");
            
        });
        
    }
    
    public static Map<Integer, String[]> values = new HashMap<>();
    
    /**
     * @param tamanho
     * @param strings
     */
    public static void permutacao(int tamanho, String...strings ) {
        if(tamanho <= 0) return;
        
        for(int i = 0; i < tamanho; i++) {
            
            String current = strings[i];
            
            if(current.length() > 10) {
                System.out.println("não pode maior que 10");
            } else {
                Set<String> permutacoes = new HashSet<>();
                permuta(current, "", permutacoes);
                values.put(i, order(permutacoes));
            }
            
        }
    }

    /**
     * @param permutacoes
     * @return
     */
    private static String[] order(Set<String> permutacoes) {
        
        int n = permutacoes.size();
        String lista[] = new String[n];
        System.arraycopy(permutacoes.toArray(), 0, lista, 0, n);
        
        for(int i = 0; i < lista.length-1; i++) {
            
            int index = i;
            int char1 = Character.getNumericValue(lista[i].charAt(0));
            
            for(int j = i+1; j < lista.length; j++) {
                
                int char2 = Character.getNumericValue(lista[j].charAt(0));
                index = char2 < char1 ? j : index;
            }
            
            String menor = lista[index];
            lista[index] = lista[i];
            lista[i] = menor;
        }
        
        return lista;
    }

    /**
     * @param current
     * @param permutacoes 
     */
    private static void permuta(String current, String append, Set<String> permutacoes) {
        
        if(current.length() == 0) {
            permutacoes.add(append);
            return;
        }
        
        Stream<Character> characterStream = current.chars().mapToObj(c -> (char) c);
        
        characterStream.forEach(character -> {
            permuta(current.replaceFirst(character.toString(), ""), append + character, permutacoes);
        });

    }

}
