package br.com.pedrohos.ed.listaligada;

public class AppListaLigada {

	public static void main(String[] args) {
		ListaLigada lista = new ListaLigada();
		
		lista.adicionaNoComeco("Pedro");
		System.out.println(lista);
		
		lista.adicionaNoComeco("Taís");
		System.out.println(lista);
		
		lista.adicionaNoComeco("Paulo");
		System.out.println(lista);
		
		lista.adiciona("William");
		System.out.println(lista);
		
		lista.adiciona(2, "Noel");
		System.out.println(lista);
		
		System.out.println("\n");
		System.out.println("Tamanho da Lista: " + lista.tamanho());
		System.out.println("Pegando na posição [2]: " + lista.pega(2));
		
	}

}
