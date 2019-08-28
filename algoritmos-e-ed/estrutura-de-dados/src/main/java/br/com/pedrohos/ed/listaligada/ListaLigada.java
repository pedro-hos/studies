package br.com.pedrohos.ed.listaligada;

public class ListaLigada {

	private Celula primeira = null;
	private Celula ultima = null;

	private int total = 0;

	public void adicionaNoComeco(Object elemento) {

		if (this.total == 0) {

			Celula nova = new Celula(elemento);
			this.primeira = nova;
			this.ultima = nova;

		} else {

			Celula nova = new Celula(elemento, primeira);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}

		this.total++;
	}

	public void adiciona(Object elemento) {

		if (this.total == 0) {
			adicionaNoComeco(elemento);

		} else {

			Celula nova = new Celula(elemento, null);
			nova.setAnterior(this.ultima);
			this.ultima.setProximo(nova);
			this.ultima = nova;
			this.total++;
		}
	}

	private Celula pegaCelula(final int posicao) {

		if (posicao > total || posicao < 0) {
			throw new IllegalArgumentException("posicao inexistente");
		}

		Celula atual = primeira;

		for (int i = 0; i < posicao; i++) {
			atual = atual.getProximo();
		}

		return atual;
	}

	public void adiciona(int posicao, Object elemento) {

		if (posicao == 0) {
			adicionaNoComeco(elemento);

		} else if (posicao == this.total) {
			this.adiciona(elemento);

		} else {

			Celula anterior = pegaCelula(posicao - 1);
			Celula proxima = anterior.getProximo();

			Celula nova = new Celula(elemento, anterior.getProximo());
			nova.setAnterior(anterior);
			anterior.setProximo(nova);
			proxima.setAnterior(nova);
			this.total++;
		}
	}

	public Object pega(int posicao) {
		return pegaCelula(posicao).getElemento();
	}

	public void removeDoComeco() {

		if (this.total == 0) {
			throw new IllegalArgumentException("lista vazia");
		}

		this.primeira = this.primeira.getProximo();
		this.total--;

		if (this.total == 0) {
			this.ultima = null;
		}
	}

	public void removeDoFim() {

		if (this.total == 1) {
			this.removeDoComeco();

		} else {
			Celula penultima = this.ultima.getAnterior();
			penultima.setProximo(null);
			this.ultima = penultima;
			this.total--;
		}
	}

	public void remove(int posicao) {
		
		if (posicao == 0) {
			this.removeDoComeco();
			
		} else if (posicao == this.total - 1) {
			
			this.removeDoFim();
		} else {
			
			Celula anterior = this.pegaCelula(posicao - 1);
			Celula atual = anterior.getProximo();
			Celula proxima = atual.getProximo();

			anterior.setProximo(proxima);
			proxima.setAnterior(anterior);

			this.total--;
		}
	}

	public int tamanho() {
		return this.total;
	}

	public boolean contem(Object elemento) {
	    Celula atual = this.primeira;

	    while(atual != null) {
	    	
	        if(atual.getElemento().equals(elemento)) {
	            return true;
	        }
	        
	        atual = atual.getProximo();
	    }
	    
	    return false;
	}

	@Override
	public String toString() {

		if (total == 0) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder("[");
		Celula atual = primeira;

		for (int i = 0; i < total; i++) {
			sb.append(atual.getElemento());

			if (!atual.getElemento().equals(this.ultima.getElemento())) {
				sb.append(", ");
			}

			atual = atual.getProximo();
		}

		sb.append("]");

		return sb.toString();
	}

}
