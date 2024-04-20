package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		//Generamos el primer grafo
		Grafo grafo = new Grafo();
		grafo = grafo.generarPrimerGrafo();
		
		grafo.menuPrincipal(grafo);
		
	}

}
