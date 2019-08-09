package br.com.pedrohos.ed.listaligada;

public class ListaLigada {
	
	private Celula primeira = null;
	private Celula ultima = null;
	
	private int total = 0;
	
	public void adicionaNoComeco(Object elemento) {
		Celula atual = new Celula(elemento, primeira);
		primeira = atual;
		
		if(this.total == 0) {
            this.ultima = this.primeira;
        }
		
		this.total++;
	}

    public void adiciona(Object elemento) {
    	
    	if(this.total == 0) {
    		adicionaNoComeco(elemento);
    	
    	} else {
    		
    		Celula nova = new Celula(elemento, null);
    		this.ultima.setProximo(nova);
    		this.ultima = nova;
    		this.total++;
    	}
    }
    
    private Celula pegaCelula(final int posicao) {
    	
    	if(posicao > total || posicao < 0) {
            throw new IllegalArgumentException("posicao inexistente");
    	}
    	
    	Celula atual = primeira;
    	
    	for(int i = 0; i < posicao; i++) {
    		atual = atual.getProximo();
    	}
    	
    	return atual;
    }

    public void adiciona(int posicao, Object elemento) {
    	
		if (posicao == 0) {
			adicionaNoComeco(elemento);
			
		} else if (posicao == this.total) {
			adiciona(elemento);
			
		} else {
			Celula anterior = pegaCelula(posicao - 1);
			Celula nova = new Celula(elemento, anterior.getProximo());
			anterior.setProximo(nova);
			total++;
			
		}
    }

    public Object pega(int posicao) { 
    	return pegaCelula(posicao).getElemento(); 	
    }

    public void remove(int posicao) {}

    public int tamanho() { 
    	return this.total; 
    }

    public boolean contem(Object o) { return false;}
    
    @Override
    public String toString() {
    	
    	if(total == 0) {
    		return "[]";
    	}
    	
    	StringBuilder sb = new StringBuilder("[");
    	Celula atual = primeira;
    	
    	for(int i =  0; i < total; i++) {
    		sb.append(atual.getElemento());
    		
    		if(!atual.getElemento().equals(this.ultima.getElemento())) {
    			sb.append(", ");
    		}
    		
    		atual = atual.getProximo();
    	}
    	
    	sb.append("]");
    	
    	return sb.toString();
    }
    
}
