package br.com.pedrohos.ed.vetor;

import java.util.Arrays;

public class Vetor {

	private Aluno[] alunos = new Aluno[100];
	private int totalAlunos = 0;

	
	private void garanteEspaco() {
	    if(totalAlunos == alunos.length) {
	        Aluno[] novoArray = new Aluno[alunos.length*2];
	        for(int i = 0; i < alunos.length; i++) {
	            novoArray[i] = alunos[i];
	        }
	        this.alunos= novoArray;
	    }

	}
	
	public void adiciona(Aluno aluno) {
		this.garanteEspaco();
		alunos[totalAlunos] = aluno;
		totalAlunos++;
	}
	
	private boolean posicaoValida(int posicao) {
	    return posicao >= 0 && posicao <= totalAlunos;
	}
	
	public void adiciona(int posicao, Aluno aluno) {
		
		this.garanteEspaco();
		
		if(!posicaoValida(posicao)) {
	        throw new IllegalArgumentException("posicao invalida");
	    }
		
	    for(int i = totalAlunos - 1; i >= posicao; i-=1) {
	        alunos[i+1] = alunos[i];
	    }
	    
	    alunos[posicao] = aluno;
	    totalAlunos++;
	}

	public Aluno pega(int posicao) {
		
		if(posicao > totalAlunos || posicao < 0) {
			throw new IllegalArgumentException("Valor invalido, maior que a quantidade de alunos");
		}
		
		return alunos[posicao];
	}

	public void remove(int posicao) {
		
		for(int i = posicao; i < totalAlunos; i++) {
			alunos[i] = alunos[i + 1];
		}
		
		totalAlunos--;
	}

	public boolean contem(Aluno aluno) {
		for (int i = 0; i < totalAlunos; i++) {
			if(aluno.equals(alunos[i])) {
				return true;
			}
		}
		
		return false;
	}

	public int tamanho() {
		return this.totalAlunos;
	}

	public String toString() {
		return Arrays.toString(alunos);
	}

}
