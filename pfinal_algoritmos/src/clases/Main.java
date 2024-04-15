package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int cantNodos = 0, cantAristas = 0, peso = 0, verticeAConectar = 0, verticeInicio = 0, verticeDestino = 0;
		Scanner scanner = new Scanner(System.in);
		boolean validado = false;
		
		// Cuántos vértices?
		System.out.print("¿Con cuántos vértices desea crear el grafo? ");
		cantNodos = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.inicializarGrafo(cantNodos);
		
		for (int ind = 0; ind < cantNodos; ind++) {
			
			do {
				System.out.print("\n¿Cuántas aristas tendrá el nodo "+ind+"? ");
				cantAristas = scanner.nextInt();
				if(cantAristas <= 0) {
					System.out.println();
					System.out.print("\nERROR, La cantidad de aristas debe ser mayor a 0 ");
					System.out.println();
				}
			}while(cantAristas <= 0);
			
			for (int ind1 = 0; ind1 < cantAristas; ind1++) {
				
				do {
					
					System.out.println("\nIndique con cuál nodo se conectará la arista "+(ind+1)+":");
					verticeAConectar = scanner.nextInt();
					
					if (grafo.buscarNodoById(verticeAConectar) != null)
						validado = true;
					else
						System.out.println("\nERROR, Por favor ingrese un nodo válido\n");
					
				} while (!validado);
				validado = false;
				
				do {
					
					System.out.println("\nIndique el peso de la conexión de la arista "+(ind+1)+":");
					peso = scanner.nextInt();
					
					if (peso > 0) {
						validado = true;
					}
					
					else {
						System.out.println("\nERROR, Por favor ingrese un peso válido\n");
					}
					
				}while (!validado);
				
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
		boolean menu = true, menu2 = true;
		
		do {
			
			System.out.println();
			grafo.imprimirMatrizAdyacencia2();
			System.out.println();
			
			System.out.println("\t1. Agregar NODO\n"
							 + "\t2. Editar NODO\n"
						 	 + "\t3. Eliminar NODO\n"
						 	 + "\t4. Mostrar NODOS\n"
						 	 + "\t5. Salir\n");
			
			System.out.print("\tOpción: ");
			opcion = scanner.nextInt();
			
			if (opcion == 1) {

				Nodo nuevoNodo = grafo.crearNodo();
				
				System.out.println("\n¿Cuántas aristas tendrá el nuevo nodo?");
				cantAristas = scanner.nextInt();
				
				for (int ind = 0; ind < cantAristas; ind++) {
					
					do {
						
						System.out.println("\nIndique con cuál nodo se conectará la arista "+(ind+1)+":");
						verticeAConectar = scanner.nextInt();
						
						if (grafo.buscarNodoById(verticeAConectar) != null)
							validado = true;
						else
							System.out.println("\nERROR, Por favor ingrese un nodo válido\n");
						
					} while (!validado);
					validado = false;
					
					do {
						
						System.out.println("\nIndique el peso de la conexión de la arista "+(ind+1)+":");
						peso = scanner.nextInt();
						
						if (peso > 0) {
							validado = true;
						}
						
						else {
							System.out.println("\nERROR, Por favor ingrese un peso válido\n");
						}
						
					} while (!validado);
					
					grafo.agregarArista(nuevoNodo.getId(), verticeAConectar, peso);
					
				}
				
				System.out.println("\nSE AGREGÓ EL NODO: "+nuevoNodo.getId()+" EXITOSAMENTE\n");
				grafo.imprimirMatrizAdyacencia();
				System.out.println();
			}
			
			else if (opcion == 2) {
				
			}
			
			else if (opcion == 3) {
				
				int nodoAEliminar = 0;
				validado = false;
				
				do {
					
					System.out.print("\nIngrese el nodo que desea borrar: ");
					nodoAEliminar = scanner.nextInt();
					
					if (grafo.buscarNodoById(nodoAEliminar) != null)
						validado = true;
					else
						System.out.println("\nERROR, Por favor ingrese un nodo válido\n");
					
				} while (!validado);
				
				grafo.eliminarNodo(nodoAEliminar);
				grafo.imprimirMatrizAdyacencia();
				System.out.println();
			}
			
			else if (opcion == 4) {
				do {
					
					System.out.println();
					System.out.print("\t¿Qué deseas mostrar?\n ");
					System.out.println();
					
					System.out.println("\t1. Matriz Adyacencia Actual\n"
							 + "\t2. Ruta más corta entre dos NODOS\n"
						 	 + "\t3. Ruta más corta de todos los NODOS\n"
						 	 + "\t4. Árbol de Expansión Mínima\n"
						 	 + "\t5. Salir\n");
					
					opcion = scanner.nextInt();
					
					if(opcion == 1) {
						System.out.println();
						grafo.imprimirMatrizAdyacencia2();
						System.out.println();
					}
					
					else if(opcion == 2) {
						System.out.println("COLOCA EL NODO DE INICIO: ");
						verticeInicio = scanner.nextInt();
						System.out.println("COLOCA EL NODO DE DESTINO: ");
						verticeDestino = scanner.nextInt();
						grafo.dijkstra(grafo.generarMatrizAdyacencia(), verticeInicio, verticeDestino);
					}
					
					else if(opcion == 3) {
						System.out.println("Distancias más cortas entre todos los pares de vértices");
						int[][] distancias = grafo.floydwarshall(grafo.generarMatrizAdyacencia()); 
						
					    for (int ind1 = 0; ind1 < distancias.length; ind1++) {  
					        for (int ind2 = 0; ind2 < distancias.length; ind2++) {  
					        	System.out.printf("%d ", distancias[ind1][ind2]);  
					        }
					        
					        System.out.println();  
					    } 
					}
					
					else if(opcion == 4) {
						//grafo.prim(matrizAdyacencia);
						grafo.kruskal(grafo.generarMatrizAdyacencia());
					}
					
					else if(opcion == 5) {
						menu2 = false;
					}
					
					else if (opcion > 5 || opcion <= 0) {
						System.out.println("\nERROR, Por favor ingrese una opción válida\n");
					}
					
					opcion = 0;
					
				}while(menu2);
			}
			
			else if (opcion == 5) {
				menu = false;
			}
			
			else if (opcion > 5 || opcion <= 0) {
				System.out.println("\nERROR, Por favor ingrese una opción válida\n");
			}
			
			menu2 = true;
			
		} while (menu);
		
	}

}
