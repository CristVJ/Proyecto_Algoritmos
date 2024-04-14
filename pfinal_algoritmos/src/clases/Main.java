package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Patata 27
		int cantVertices = 0, cantAristas = 0, peso = 0, verticeAConectar = 0;
		Scanner scanner = new Scanner(System.in);
		
		// Cu�ntos v�rtices?
		System.out.println("�Con cu�ntos v�rtices desea crear el grafo?");
		cantVertices = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.inicializarMatriz(cantVertices);
		
		for (int ind = 0; ind < cantVertices; ind++) {
			
			System.out.println("\n�Cu�ntas aristas tendr� el v�rtice "+ind+"?");
			cantAristas = scanner.nextInt();
			
			for (int ind1 = 0; ind1 < cantAristas; ind1++) {
				
				System.out.println("\nIndique con cu�l v�rtice se conectar� la arista "+(ind1+1)+":");
				verticeAConectar = scanner.nextInt();
				
				System.out.println("\nIndique el peso de la conexi�n de la arista "+(ind1+1)+":");
				peso = scanner.nextInt();
				
				grafo.agregarArista(ind, verticeAConectar, peso);
				 
			}
			
		}
		
		System.out.println("CREADO POR PRIMERA VEZ");
		grafo.imprimirMatriz();
		
		// Supongamos que se desea AGREGAR un nuevo v�rtice
		
		// Ahora, habr�n 4 v�rtices
		grafo.crearVertice(4);
		
		grafo.agregarArista(3, 0, 3);
		grafo.agregarArista(3, 2, 1);
		
		System.out.println("AGREGANDO UN V�RTICE");
		grafo.imprimirMatriz();
		///
		
		// Supongamos que se desea ELIMINAR un v�rtice
		
		grafo.eliminarVertice(3);
		
		System.out.println("ELIMINANDO UN V�RTICE");
		grafo.imprimirMatriz();
		///
		
	}

}
