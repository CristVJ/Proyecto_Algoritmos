package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int cantNodos = 0;
		int opcion1 = 0, opcion2 = 0;
		boolean validado = false, menu = true, menuModificar = true, menuMostrar = true, menuTiempo = true; 
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("�Con cu�ntos nodos desea crear el grafo? ");
		cantNodos = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.crearGrafo(cantNodos);
		
		do {
			
			// Por defecto, siempre se imprimir� una matriz de adyacencia con pesos como distancias
			grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia(true));
			
			System.out.print("\n\t MEN� PRINCIPAL\n\n"
							 + "\t1. Agregar NODO\n"
							 + "\t2. Modificar NODO\n"
						 	 + "\t3. Eliminar NODO\n"
						 	 + "\t4. Mostrar NODO\n"
						 	 + "\t5. Salir\n");
			
			System.out.print("\n\tOpci�n: ");
			opcion1 = scanner.nextInt();
			
			if (opcion1 == 1)
				grafo.agregarNodo();
				
			else if (opcion1 == 2) {
				
				int idNodoAModificar = 0;
				Nodo nodoAModificar;
				
				validado = false;
				do {
					
					System.out.print("\n\tIngrese el nodo que desea modificar: ");
					idNodoAModificar = scanner.nextInt();
					
					nodoAModificar = grafo.buscarNodoById(idNodoAModificar);
					
					if (nodoAModificar != null)
						validado = true;
					else
						System.out.println("\n\tERROR: Por favor ingrese un nodo v�lido\n");
					
				} while (!validado);
				
				do {
					
					System.out.print("\n\tElija la opci�n de modificaci�n que desee:\n"
									 + "\t1. Agregar arista\n"
							 		 + "\t2. Eliminar arista\n"
							 		 + "\t3. Editar distancia\n"
							 		 + "\t4. Editar tiempo\n"
							 		 + "\t5. Salir\n");
					
					System.out.print("\n\tOpci�n: ");
					opcion1 = scanner.nextInt();
					
					if (opcion1 == 1) {
						
						Arista nuevaArista = grafo.crearAristaValidada(nodoAModificar.getId());
						
						if (nuevaArista != null)
							grafo.agregarArista(nuevaArista.getOrigen(), nuevaArista.getDestino(), nuevaArista.getDistancia(), nuevaArista.getTiempo());
						
					}
					else if (opcion1 == 2)
						grafo.elegirAristaAEliminar(nodoAModificar);
					
					else if (opcion1 == 3)
						grafo.modificarArista(nodoAModificar, true);
			
					else if (opcion1 == 4)
						grafo.modificarArista(nodoAModificar, false);
						
					else if (opcion1 == 5)
						menuModificar = false;
					
					else if (opcion1 > 5 || opcion1 <= 0)
						System.out.println("\n\tERROR: Por favor ingrese una opci�n v�lida\n");
					
					opcion1 = 0;
					
				} while (menuModificar);
			}
			else if (opcion1 == 3)
				grafo.elegirNodoAEliminar();
			
			else if (opcion1 == 4) {
				
				do {
					
					System.out.print("\n\tElija lo que desee mostrar\n\n"
									 + "\t1. Matriz Adyacencia Actual por DISTANCIA\n"
							 		 + "\t2. Matriz Adyacencia Actual por TIEMPO\n"
							 		 + "\t3. Ruta m�s corta entre dos NODOS\n"
							 		 + "\t4. Ruta m�s corta de todos los NODOS\n"
							 		 + "\t5. �rbol de Expansi�n M�nima\n"
							 		 + "\t6. Salir\n");
					
					System.out.print("\n\tOpci�n: ");
					opcion1 = scanner.nextInt();
					
					if (opcion1 == 1) {
						System.out.println();
						grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia(true));
						System.out.println();
					}
					else if (opcion1 == 2) {
						System.out.println();
						grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia(false));
						System.out.println();
					}
					else if (opcion1 == 3)
						grafo.mostrarRutaMasCorta();

					else if (opcion1 == 4) {

						do {
							System.out.print("\n\t�En base a qu� desea verlo?\n\t1. Distancia \n\t2. Tiempo\n\n\tOpci�n: ");
							opcion2 = scanner.nextInt();
							if (opcion2 == 1) {
								int[][] distancias = grafo.floydwarshall(grafo.generarMatrizAdyacencia(true)); 
								
								System.out.println("\n\tDistancias m�s cortas entre todos los pares de v�rtices: ");
								grafo.imprimirMatrizAdyacencia(distancias);
							    menuTiempo = false;
							}
							else if(opcion2 == 2) {
								int[][] tiempos = grafo.floydwarshall(grafo.generarMatrizAdyacencia(false)); 
								
								System.out.println("\n\tTiempos m�s cortos entre todos los pares de v�rtices: ");
							    grafo.imprimirMatrizAdyacencia(tiempos);
							    menuTiempo = false;
							}
							else if (opcion2 > 2 || opcion2 <= 0)
								System.out.println("\nERROR: Por favor ingrese una opci�n v�lida\n");
							
						} while(menuTiempo);
						
						menuTiempo = true;
					}
					else if (opcion1 == 5)
						grafo.mostrarArbol();
					
					else if(opcion1 == 6)
						menuMostrar = false;
					
					else if (opcion1 > 6 || opcion1 <= 0)
						System.out.println("\nERROR: Por favor ingrese una opci�n v�lida\n");
					
					opcion1 = 0;
					
				} while(menuMostrar);
			}
			else if (opcion1 == 5)
				menu = false;
			
			else if (opcion1 > 5 || opcion1 <= 0)
				System.out.println("\n\tERROR: Por favor ingrese una opci�n v�lida\n");
			
			menuMostrar = true;
	
		} while (menu);
	}
}