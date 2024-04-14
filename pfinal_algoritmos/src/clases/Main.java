package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Patata 27
		int cantVertices = 0, cantAristas = 0, peso = 0, verticeAConectar = 0;
		Scanner scanner = new Scanner(System.in);
		
		// Cuántos vértices?
		System.out.println("¿Con cuántos vértices desea crear el grafo?");
		cantVertices = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.inicializarMatriz(cantVertices);
		
		for (int ind = 0; ind < cantVertices; ind++) {
			
			System.out.println("\n¿Cuántas aristas tendrá el vértice "+ind+"?");
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
		grafo.imprimirMatriz();
		
		// Supongamos que se desea AGREGAR un nuevo vértice
		
		// Ahora, habrán 4 vértices
		grafo.crearVertice(4);
		
		grafo.agregarArista(3, 0, 3);
		grafo.agregarArista(3, 2, 1);
		
		System.out.println("AGREGANDO UN VÉRTICE");
		grafo.imprimirMatriz();
		///
		
		// Supongamos que se desea ELIMINAR un vértice
		
		grafo.eliminarVertice(3);
		
		System.out.println("ELIMINANDO UN VÉRTICE");
		grafo.imprimirMatriz();
		///
		
	}

}
