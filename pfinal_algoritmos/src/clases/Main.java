package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
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
		
		/*
		// Agregando nodo (PROCESO DE PRUEBA)
		grafo.crearNodo();
		grafo.agregarArista(3, 0, 3);
		grafo.agregarArista(3, 2, 5);
		System.out.println("LUEGO DE AGREGAR");
		grafo.imprimirMatrizAdyacencia();
		System.out.println();
		
		// Eliminando nodo (PROCESO DE PRUEBA)
		grafo.eliminarNodo(2);
		System.out.println("LUEGO DE BORRAR");
		grafo.imprimirMatrizAdyacencia();
		System.out.println();
		*/
		int opcion = 0;
		boolean menu = true;
		
		do {
			
			System.out.println();
			grafo.imprimirMatrizAdyacencia();
			System.out.println();
			
			System.out.println("\t1. Agregar NODO\n"
							 + "\t2. Editar NODO\n"
						 	 + "\t3. Eliminar NODO\n"
						 	 + "\t4. Salir\n");
			
			opcion = scanner.nextInt();
			
			if (opcion == 1) {
				
				Nodo nuevoNodo = grafo.crearNodo();
				
				System.out.println("\n¿Cuántas aristas tendrá el nuevo nodo?");
				cantAristas = scanner.nextInt();
				
				for (int ind = 0; ind < cantAristas; ind++) {
					
					System.out.println("\nIndique con cuál vértice se conectará la arista "+(ind+1)+":");
					
					boolean validado = false;
					
					do {
						
						verticeAConectar = scanner.nextInt();
						
						if (grafo.buscarNodoById(verticeAConectar) != null)
							validado = true;
						
					} while (!validado);
					
					
					System.out.println("\nIndique el peso de la conexión de la arista "+(ind+1)+":");
					
					do {
						
						peso = scanner.nextInt();
						
						if (peso > 0)
							validado = true;
						
					} while (!validado);
					
					grafo.agregarArista(nuevoNodo.getId(), verticeAConectar, peso);
					
				}
				
			}
			else if (opcion == 2) {
				
			}
			else if (opcion == 3) {
				
			}
			else if (opcion == 4) {
				menu = false;
			}
			
		} while (menu);
		
	}

}
