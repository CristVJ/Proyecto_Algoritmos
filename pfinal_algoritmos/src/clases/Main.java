package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Patata 27
		int cantNodos = 0, cantAristas = 0, peso = 0, verticeAConectar = 0;
		Scanner scanner = new Scanner(System.in);
		
		// Cuántos vértices?
		System.out.println("¿Con cuántos vértices desea crear el grafo?");
		cantNodos = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.inicializarGrafo(cantNodos);
		
		for (int ind = 0; ind < cantNodos; ind++) {
			
			System.out.println("\n¿Cuántas aristas tendrá el nodo "+ind+"?");
			cantAristas = scanner.nextInt();
			
			for (int ind1 = 0; ind1 < cantAristas; ind1++) {
				
				System.out.println("\nIndique con cuál vértice se conectará la arista "+(ind1+1)+":");
				verticeAConectar = scanner.nextInt();
				
				System.out.println("\nIndique el peso de la conexión de la arista "+(ind1+1)+":");
				peso = scanner.nextInt();
				
				grafo.agregarArista(ind, verticeAConectar, peso);
				 
			}
			
		}
		
		System.out.println("CREADO POR PRIMERA VEZ");
		grafo.imprimirMatrizAdyacencia();
		System.out.println();
		
		// Eliminando nodo
		grafo.eliminarNodo(2);
		System.out.println("LUEGO DE BORRAR");
		grafo.imprimirMatrizAdyacencia();
		System.out.println();
		
	}

}
