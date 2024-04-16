package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int cantNodos = 0, cantAristas = 0, distancia = 0, tiempo = 0, nodoAConectar = 0, verticeInicio = 0, verticeDestino = 0;
		Scanner scanner = new Scanner(System.in);
		boolean validado = false;
		
		System.out.print("�Con cu�ntos nodos desea crear el grafo? ");
		cantNodos = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.inicializarGrafo(cantNodos);
		
		for (int ind = 0; ind < cantNodos; ind++) {
			
			do {
				System.out.print("\nIndique la cantidad de aristas que tendr� el nodo: ");
				cantAristas = scanner.nextInt();
				if(cantAristas <= 0) {
					System.out.println();
					System.out.print("\nERROR: La cantidad de aristas debe ser mayor a 0 ");
					System.out.println();
				}
			}while(cantAristas <= 0);
			
			for (int ind1 = 0; ind1 < cantAristas; ind1++) {
				
				do {
					
					System.out.print("\nIndique con cu�l nodo se conectar� la arista "+(ind+1)+": ");
					nodoAConectar = scanner.nextInt();
					
					if (grafo.buscarNodoById(nodoAConectar) != null)
						validado = true;
					else
						System.out.println("\nERROR: Por favor ingrese un nodo v�lido\n");
					
				} while (!validado);
				validado = false;
				
				do {
					
					System.out.print("\nIndique la distancia de conexi�n de la arista "+(ind+1)+": ");
					distancia = scanner.nextInt();
					
					if (distancia > 0) {
						validado = true;
					}
					
					else {
						System.out.println("\nERROR: Por favor ingrese una distancia v�lida\n");
					}
					
				}while (!validado);
				validado = false;
				
				do {
					
					System.out.print("\nIndique el tiempo de conexi�n de la arista "+(ind+1)+": ");
					tiempo = scanner.nextInt();
					
					if (tiempo > 0) {
						validado = true;
					}
					
					else {
						System.out.println("\nERROR: Por favor ingrese un tiempo v�lido\n");
					}
					
				}while (!validado);
				
				grafo.agregarArista(ind, nodoAConectar, distancia, tiempo);
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
		boolean menu = true, menuModificar = true, menuMostrar = true;
		
		do {
			
			System.out.println();
			// Por defecto, siempre se imprimir� una matriz de adyacencia con pesos como distancias
			grafo.imprimirMatrizAdyacencia(true);
			System.out.println();
			
			System.out.println("\t1. Agregar NODO\n"
							 + "\t2. Modificar NODO\n"
						 	 + "\t3. Eliminar NODO\n"
						 	 + "\t4. Mostrar NODOS\n"
						 	 + "\t5. Salir\n");
			
			System.out.print("\tOpci�n: ");
			opcion = scanner.nextInt();
			
			if (opcion == 1) {

				Nodo nuevoNodo = grafo.crearNodo();
				
				System.out.println("\n�Cu�ntas aristas tendr� el nuevo nodo?");
				cantAristas = scanner.nextInt();
				
				for (int ind = 0; ind < cantAristas; ind++) {
					
					do {
						
						System.out.println("\nIndique con cu�l nodo se conectar� la arista "+(ind+1)+":");
						nodoAConectar = scanner.nextInt();
						
						if (grafo.buscarNodoById(nodoAConectar) != null)
							validado = true;
						else
							System.out.println("\nERROR: Por favor ingrese un nodo v�lido\n");
						
					} while (!validado);
					validado = false;
					
					do {
						
						System.out.println("\nIndique la distancia de conexi�n de la arista "+(ind+1)+":");
						distancia = scanner.nextInt();
						
						if (distancia > 0) {
							validado = true;
						}
						
						else {
							System.out.println("\nERROR: Por favor ingrese una distancia v�lida\n");
						}
						
					} while (!validado);
					validado = false;
					
					do {
						
						System.out.println("\nIndique el tiempo de conexi�n de la arista "+(ind+1)+":");
						tiempo = scanner.nextInt();
						
						if (tiempo > 0) {
							validado = true;
						}
						
						else {
							System.out.println("\nERROR: Por favor ingrese un tiempo v�lido\n");
						}
						
					} while (!validado);
					
					grafo.agregarArista(nuevoNodo.getId(), nodoAConectar, distancia, tiempo);
					
				}
				
				System.out.println("\nSE AGREG� EL NODO: "+nuevoNodo.getId()+" EXITOSAMENTE\n");
			}
			
			else if (opcion == 2) {
				
				int idNodoAModificar = 0;
				Nodo nodoAModificar;
				
				validado = false;
				do {
					
					System.out.println("\nIngrese el nodo que desea modificar: ");
					idNodoAModificar = scanner.nextInt();
					
					nodoAModificar = grafo.buscarNodoById(idNodoAModificar);
					
					if (nodoAModificar != null)
						validado = true;
					else
						System.out.println("\nERROR: Por favor ingrese un nodo v�lido\n");
					
				} while (!validado);
				
				do {
					
					System.out.println();
					System.out.print("\tElija la opci�n de modificaci�n que desee:\n ");
					System.out.println();
					
					System.out.println("\t1. Agregar arista\n"
							 		 + "\t2. Eliminar arista\n"
							 		 + "\t3. Editar distancia\n"
							 		 + "\t4. Editar tiempo\n"
							 		 + "\t5. Salir\n");
					
					System.out.print("\tOpci�n: ");
					opcion = scanner.nextInt();
					
					if (opcion == 1) {
						
						validado = false;
						do {
							
							System.out.print("\nIndique con cu�l nodo se conectar� la arista: ");
							nodoAConectar = scanner.nextInt();
							
							if (grafo.buscarNodoById(nodoAConectar) != null)
								validado = true;
							else
								System.out.println("\nERROR: Por favor ingrese un nodo v�lido\n");
							
						} while (!validado);
						validado = false;
						
						do {
							
							System.out.print("\nIndique la distancia de conexi�n de la arista: ");
							distancia = scanner.nextInt();
							
							if (distancia > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese una distancia v�lida\n");
							}
							
						} while (!validado);
						validado = false;
						
						do {
							
							System.out.print("\nIndique el tiempo de conexi�n de la arista: ");
							tiempo = scanner.nextInt();
							
							if (tiempo > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese un tiempo v�lido\n");
							}
							
						} while (!validado);
						
						grafo.agregarArista(nodoAModificar.getId(), nodoAConectar, distancia, tiempo);
						
					}
					
					else if (opcion == 2) {
						
						/* El borrado de arista solo ser� permitido si ambos nodos, el de origen y el de destino, cuentan
						 * con m�s de una arista, para evitar que uno de estos se quede flotando. A continuaci�n, se
						 * verifica lo anterior para el nodo de origen */
						if (nodoAModificar.getAristas().size() > 1) {
							int indAristaAEliminar = 0;
							System.out.println();
							
							/* Se despliega una lista organizada de todas las aristas que contiene ese nodo, para facilitarle
							 * al usuario la elecci�n de borrado */
							grafo.listarAristasNodo(nodoAModificar);
							System.out.println();
							
							validado = false;
							do {
								
								System.out.print("\nIndique la arista que desea eliminar: ");
								indAristaAEliminar = scanner.nextInt();
								
								if (indAristaAEliminar > 0 && indAristaAEliminar <= nodoAModificar.getAristas().size()) {
									validado = true;
								}
								
								else {
									System.out.println("\nERROR: Por favor ingrese una arista v�lida\n");
								}
								
							} while (!validado);
							
							Arista aristaAEliminar = nodoAModificar.getAristas().get(indAristaAEliminar-1);
							Nodo nodoDestinoArista = grafo.buscarNodoById(aristaAEliminar.getDestino());
							
							/* El borrado de arista solo ser� permitido si ambos nodos, el de origen y el de destino, cuentan
							 * con m�s de una arista, para evitar que uno de estos se quede flotando. A continuaci�n, se
							 * verifica lo anterior para el nodo de destino */
							if (nodoDestinoArista.getAristas().size() > 1) {
								
								grafo.eliminarArista(grafo.encontrarAristaAdyacente(aristaAEliminar));
								grafo.eliminarArista(aristaAEliminar);
							}
							else 
								System.out.println("\nEl nodo de destino cuenta con una sola arista. Considere eliminar ese nodo\n");
							
						}
						else 
							System.out.println("\nEste nodo cuenta con una sola arista. Considere eliminar el nodo\n");
		
					}
					
					else if (opcion == 3) {
			
						int indAristaAModificar = 0;
						System.out.println();
						grafo.listarAristasNodo(nodoAModificar);
						System.out.println();
						
						validado = false;
						do {
							
							System.out.print("\nIndique la arista a la que desea modificarle la distancia: ");
							indAristaAModificar = scanner.nextInt();
							
							if (indAristaAModificar > 0 && indAristaAModificar <= nodoAModificar.getAristas().size()) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese una arista v�lida\n");
							}
							
						} while (!validado);
						
						validado = false;
						do {
							
							System.out.print("\nIndique la nueva distancia de conexi�n de la arista: ");
							distancia = scanner.nextInt();
							
							if (distancia > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese una distancia v�lida\n");
							}
							
						} while (!validado);
						
						Arista aristaAModificar = nodoAModificar.getAristas().get(indAristaAModificar-1) ;
						aristaAModificar.setDistancia(distancia);
						Arista aristaAdyacente = grafo.encontrarAristaAdyacente(aristaAModificar);
						aristaAdyacente.setDistancia(distancia);
						
					}
					
					else if (opcion == 4) {

						int indAristaAModificar = 0;
						System.out.println();
						grafo.listarAristasNodo(nodoAModificar);
						System.out.println();
						
						validado = false;
						do {
							
							System.out.print("\nIndique la arista a la que desea modificarle el tiempo: ");
							indAristaAModificar = scanner.nextInt();
							
							if (indAristaAModificar > 0 && indAristaAModificar <= nodoAModificar.getAristas().size()) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese una arista v�lida\n");
							}
							
						} while (!validado);
						
						validado = false;
						do {
							
							System.out.print("\nIndique el nuevo tiempo de conexi�n de la arista: ");
							tiempo = scanner.nextInt();
							
							if (tiempo > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese un tiempo v�lido\n");
							}
							
						} while (!validado);
						
						Arista aristaAModificar = nodoAModificar.getAristas().get(indAristaAModificar-1) ;
						aristaAModificar.setTiempo(tiempo);
						Arista aristaAdyacente = grafo.encontrarAristaAdyacente(aristaAModificar);
						aristaAdyacente.setTiempo(tiempo);
						
					}
					
					else if (opcion == 5) {
						menuModificar = false;
					}
					
					else if (opcion > 5 || opcion <= 0) {
						System.out.println("\nERROR: Por favor ingrese una opci�n v�lida\n");
					}
					
					opcion = 0;
					
				} while (menuModificar);
				
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
						System.out.println("\nERROR: Por favor ingrese un nodo v�lido\n");
					
				} while (!validado);
				
				grafo.eliminarNodo(nodoAEliminar);
			}
			
			else if (opcion == 4) {
				
				do {
					
					System.out.println();
					System.out.print("\t�Qu� deseas mostrar?\n ");
					System.out.println();
					
					System.out.println("\t1. Matriz Adyacencia Actual\n"
							 + "\t2. Ruta m�s corta entre dos NODOS\n"
						 	 + "\t3. Ruta m�s corta de todos los NODOS\n"
						 	 + "\t4. �rbol de Expansi�n M�nima\n"
						 	 + "\t5. Salir\n");
					
					opcion = scanner.nextInt();
					
					if(opcion == 1) {
						System.out.println();
						//grafo.imprimirMatrizAdyacencia();
						System.out.println();
					}
					
					else if(opcion == 2) {
						System.out.print("Ingrese el nodo de origen: ");
						verticeInicio = scanner.nextInt();
						System.out.print("Ingrese el nodo de destino: ");
						verticeDestino = scanner.nextInt();
						//grafo.dijkstra(grafo.generarMatrizAdyacencia(), verticeInicio, verticeDestino);
					}
					
					else if(opcion == 3) {
						System.out.println("Distancias m�s cortas entre todos los pares de v�rtices");
						/*
						int[][] distancias = grafo.floydwarshall(grafo.generarMatrizAdyacencia()); 
						
					    for (int ind1 = 0; ind1 < distancias.length; ind1++) {  
					        for (int ind2 = 0; ind2 < distancias.length; ind2++) {  
					        	System.out.printf("%d ", distancias[ind1][ind2]);  
					        }
					        
					        System.out.println();  
					    }
					    */ 
					}
					
					else if(opcion == 4) {
						//grafo.prim(matrizAdyacencia);
						//grafo.kruskal(grafo.generarMatrizAdyacencia());
					}
					
					else if(opcion == 5) {
						menuMostrar = false;
					}
					
					else if (opcion > 5 || opcion <= 0) {
						System.out.println("\nERROR: Por favor ingrese una opci�n v�lida\n");
					}
					
					opcion = 0;
					
				}while(menuMostrar);
			}
			
			else if (opcion == 5) {
				menu = false;
			}
			
			else if (opcion > 5 || opcion <= 0) {
				System.out.println("\nERROR: Por favor ingrese una opci�n v�lida\n");
			}
			
			menuMostrar = true;
			
		} while (menu);
		
	}

}
