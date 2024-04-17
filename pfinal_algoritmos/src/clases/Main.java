package clases;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int cantNodos = 0, cantAristas = 0, distancia = 0, tiempo = 0, nodoAConectar = 0, verticeInicio = 0, verticeDestino = 0;
		Scanner scanner = new Scanner(System.in);
		boolean validado = false;
		
		System.out.print("¿Con cuántos nodos desea crear el grafo? ");
		cantNodos = scanner.nextInt();
		
		Grafo grafo = new Grafo();
		grafo.inicializarGrafo(cantNodos);
		
		for (int ind = 0; ind < cantNodos; ind++) {
			
			do {
				System.out.print("\nIndique la cantidad de aristas que tendrá el nodo "+(ind+1)+": ");
				cantAristas = scanner.nextInt();
				if(cantAristas <= 0) {
					System.out.println();
					System.out.print("\nERROR: La cantidad de aristas debe ser mayor a 0 ");
					System.out.println();
				}
			} while(cantAristas <= 0);
			
			for (int ind1 = 0; ind1 < cantAristas; ind1++) {
				
				do {
					
					System.out.print("\nIndique con cuál nodo se conectará la arista "+(ind+1)+": ");
					nodoAConectar = scanner.nextInt();
					
					if (grafo.buscarNodoById(nodoAConectar) != null)
						validado = true;
					else
						System.out.println("\nERROR: Por favor ingrese un nodo válido\n");
					
				} while (!validado);
				validado = false;
				
				do {
					
					System.out.print("\nIndique la distancia de conexión de la arista "+(ind+1)+": ");
					distancia = scanner.nextInt();
					
					if (distancia > 0) {
						validado = true;
					}
					
					else {
						System.out.println("\nERROR: Por favor ingrese una distancia válida\n");
					}
					
				} while (!validado);
				validado = false;
				
				do {
					
					System.out.print("\nIndique el tiempo de conexión de la arista "+(ind+1)+": ");
					tiempo = scanner.nextInt();
					
					if (tiempo > 0) {
						validado = true;
					}
					
					else {
						System.out.println("\nERROR: Por favor ingrese un tiempo válido\n");
					}
					
				} while (!validado);
				
				grafo.agregarArista(ind, nodoAConectar, distancia, tiempo);
			}
			
		}
		
		int opcion1 = 0, opcion2 = 0;
		boolean menu = true, menuModificar = true, menuMostrar = true, menuTiempo = true;
		
		do {
			
			System.out.println();
			// Por defecto, siempre se imprimirá una matriz de adyacencia con pesos como distancias
			grafo.imprimirMatrizAdyacencia(true);
			System.out.println();
			
			System.out.println("\t1. Agregar NODO\n"
							 + "\t2. Modificar NODO\n"
						 	 + "\t3. Eliminar NODO\n"
						 	 + "\t4. Mostrar NODO\n"
						 	 + "\t5. Salir\n");
			
			System.out.print("\tOpción: ");
			opcion1 = scanner.nextInt();
			
			if (opcion1 == 1) {

				Nodo nuevoNodo = grafo.crearNodo();
				
				System.out.println("\n¿Cuántas aristas tendrá el nuevo nodo?");
				cantAristas = scanner.nextInt();
				
				for (int ind = 0; ind < cantAristas; ind++) {
					
					validado = false;
					do {
						
						System.out.println("\nIndique con cuál nodo se conectará la arista "+(ind+1)+":");
						nodoAConectar = scanner.nextInt();
						
						if (grafo.buscarNodoById(nodoAConectar) != null)
							validado = true;
						else
							System.out.println("\nERROR: Por favor ingrese un nodo válido\n");
						
					} while (!validado);
					validado = false;
					
					do {
						
						System.out.println("\nIndique la distancia de conexión de la arista "+(ind+1)+":");
						distancia = scanner.nextInt();
						
						if (distancia > 0) {
							validado = true;
						}
						
						else {
							System.out.println("\nERROR: Por favor ingrese una distancia válida\n");
						}
						
					} while (!validado);
					validado = false;
					
					do {
						
						System.out.println("\nIndique el tiempo de conexión de la arista "+(ind+1)+":");
						tiempo = scanner.nextInt();
						
						if (tiempo > 0) {
							validado = true;
						}
						
						else {
							System.out.println("\nERROR: Por favor ingrese un tiempo válido\n");
						}
						
					} while (!validado);
					
					grafo.agregarArista(nuevoNodo.getId(), nodoAConectar, distancia, tiempo);
					
				}
				
				System.out.println("\nSE AGREGÓ EL NODO: "+nuevoNodo.getId()+" EXITOSAMENTE\n");
			}
			
			else if (opcion1 == 2) {
				
				int idNodoAModificar = 0;
				Nodo nodoAModificar;
				
				validado = false;
				do {
					
					System.out.println("\n\tIngrese el nodo que desea modificar: ");
					idNodoAModificar = scanner.nextInt();
					
					nodoAModificar = grafo.buscarNodoById(idNodoAModificar);
					
					if (nodoAModificar != null)
						validado = true;
					else
						System.out.println("\nERROR: Por favor ingrese un nodo válido\n");
					
				} while (!validado);
				
				do {
					
					System.out.println();
					System.out.print("\tElija la opción de modificación que desee:\n ");
					System.out.println();
					
					System.out.println("\t1. Agregar arista\n"
							 		 + "\t2. Eliminar arista\n"
							 		 + "\t3. Editar distancia\n"
							 		 + "\t4. Editar tiempo\n"
							 		 + "\t5. Salir\n");
					
					System.out.print("\tOpción: ");
					opcion1 = scanner.nextInt();
					
					if (opcion1 == 1) {
						
						validado = false;
						do {
							
							System.out.print("\nIndique con cuál nodo se conectará la arista: ");
							nodoAConectar = scanner.nextInt();
							
							if (grafo.buscarNodoById(nodoAConectar) != null)
								validado = true;
							else
								System.out.println("\nERROR: Por favor ingrese un nodo válido\n");
							
						} while (!validado);
						validado = false;
						
						do {
							
							System.out.print("\nIndique la distancia de conexión de la arista: ");
							distancia = scanner.nextInt();
							
							if (distancia > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese una distancia válida\n");
							}
							
						} while (!validado);
						validado = false;
						
						do {
							
							System.out.print("\nIndique el tiempo de conexión de la arista: ");
							tiempo = scanner.nextInt();
							
							if (tiempo > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese un tiempo válido\n");
							}
							
						} while (!validado);
						
						grafo.agregarArista(nodoAModificar.getId(), nodoAConectar, distancia, tiempo);
						
					}
					
					else if (opcion1 == 2) {
						
						/* El borrado de arista solo será permitido si ambos nodos, el de origen y el de destino, cuentan
						 * con más de una arista, para evitar que uno de estos se quede flotando. A continuación, se
						 * verifica lo anterior para el nodo de origen */
						if (nodoAModificar.getAristas().size() > 1) {
							int indAristaAEliminar = 0;
							System.out.println();
							
							/* Se despliega una lista organizada de todas las aristas que contiene ese nodo, para facilitarle
							 * al usuario la elección de borrado */
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
									System.out.println("\nERROR: Por favor ingrese una arista válida\n");
								}
								
							} while (!validado);
							
							Arista aristaAEliminar = nodoAModificar.getAristas().get(indAristaAEliminar-1);
							Nodo nodoDestinoArista = grafo.buscarNodoById(aristaAEliminar.getDestino());
							
							/* El borrado de arista solo será permitido si ambos nodos, el de origen y el de destino, cuentan
							 * con más de una arista, para evitar que uno de estos se quede flotando. A continuación, se
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
					
					else if (opcion1 == 3) {
			
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
								System.out.println("\nERROR: Por favor ingrese una arista válida\n");
							}
							
						} while (!validado);
						
						validado = false;
						do {
							
							System.out.print("\nIndique la nueva distancia de conexión de la arista: ");
							distancia = scanner.nextInt();
							
							if (distancia > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese una distancia válida\n");
							}
							
						} while (!validado);
						
						Arista aristaAModificar = nodoAModificar.getAristas().get(indAristaAModificar-1) ;
						aristaAModificar.setDistancia(distancia);
						Arista aristaAdyacente = grafo.encontrarAristaAdyacente(aristaAModificar);
						aristaAdyacente.setDistancia(distancia);
						
					}
					
					else if (opcion1 == 4) {

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
								System.out.println("\nERROR: Por favor ingrese una arista válida\n");
							}
							
						} while (!validado);
						
						validado = false;
						do {
							
							System.out.print("\nIndique el nuevo tiempo de conexión de la arista: ");
							tiempo = scanner.nextInt();
							
							if (tiempo > 0) {
								validado = true;
							}
							
							else {
								System.out.println("\nERROR: Por favor ingrese un tiempo válido\n");
							}
							
						} while (!validado);
						
						Arista aristaAModificar = nodoAModificar.getAristas().get(indAristaAModificar-1) ;
						aristaAModificar.setTiempo(tiempo);
						Arista aristaAdyacente = grafo.encontrarAristaAdyacente(aristaAModificar);
						aristaAdyacente.setTiempo(tiempo);
						
					}
					
					else if (opcion1 == 5) {
						menuModificar = false;
					}
					
					else if (opcion1 > 5 || opcion1 <= 0) {
						System.out.println("\nERROR: Por favor ingrese una opción válida\n");
					}
					
					opcion1 = 0;
					
				} while (menuModificar);
				
			}
			
			else if (opcion1 == 3) {
				
				int nodoAEliminar = 0;
				validado = false;
				
				do {
					
					System.out.print("\nIngrese el nodo que desea borrar: ");
					nodoAEliminar = scanner.nextInt();
					
					if (grafo.buscarNodoById(nodoAEliminar) != null)
						validado = true;
					else
						System.out.println("\nERROR: Por favor ingrese un nodo válido\n");
					
				} while (!validado);
				
				grafo.eliminarNodo(nodoAEliminar);
			}
			
			else if (opcion1 == 4) {
				
				do {
					
					System.out.println();
					System.out.print("\tElija lo que desee mostrar\n ");
					System.out.println();
					
					System.out.println("\t1. Matriz Adyacencia Actual por DISTANCIA\n"
							 + "\t2. Matriz Adyacencia Actual por TIEMPO\n"
							 + "\t3. Ruta más corta entre dos NODOS\n"
						 	 + "\t4. Ruta más corta de todos los NODOS\n"
						 	 + "\t5. Árbol de Expansión Mínima\n"
						 	 + "\t6. Salir\n");
					
					System.out.print("\tOpción: ");
					opcion1 = scanner.nextInt();
					
					if(opcion1 == 1) {
						System.out.println();
						grafo.imprimirMatrizAdyacencia(true);
						System.out.println();
					}
					
					else if(opcion1 == 2) {
						System.out.println();
						grafo.imprimirMatrizAdyacencia(false);
						System.out.println();
					}
					
					else if(opcion1 == 3) {
						System.out.print("\tIngrese el nodo de origen: ");
						verticeInicio = scanner.nextInt();
						System.out.print("\tIngrese el nodo de destino: ");
						verticeDestino = scanner.nextInt();
						do {
							System.out.print("\t¿En base a qué desea verlo?\n\t1. Distancia \n\t2. Tiempo");
							opcion2 = scanner.nextInt();
							if(opcion2 == 1) {
								grafo.dijkstra(grafo.generarMatrizAdyacencia(true), verticeInicio, verticeDestino);
								menuTiempo = false;
							}
							
							else if(opcion2 == 2) {
								grafo.dijkstra(grafo.generarMatrizAdyacencia(false), verticeInicio, verticeDestino);
								menuTiempo = false;
							}
							
							else if (opcion2 > 2 || opcion2 <= 0) {
								System.out.println("\nERROR: Por favor ingrese una opción válida\n");
							}
							
						}while(menuTiempo);
						menuTiempo = true;
					}
					
					else if(opcion1 == 4) {
						
						do {
							System.out.print("¿En base a qué desea verlo?\n\t1. Distancia \n\t2. Tiempo");
							opcion2 = scanner.nextInt();
							if(opcion2 == 1) {
								int[][] distancias = grafo.floydwarshall(grafo.generarMatrizAdyacencia(true)); 
								
								System.out.println("\nDistancias más cortas entre todos los pares de vértices");
							    for (int ind1 = 0; ind1 < distancias.length; ind1++) {  
							        for (int ind2 = 0; ind2 < distancias.length; ind2++) {  
							        	System.out.printf("%d ", distancias[ind1][ind2]);  
							        }
							        
							        System.out.println();  
							    }
							    
							    menuTiempo = false;
							}
							
							else if(opcion2 == 2) {
								int[][] tiem = grafo.floydwarshall(grafo.generarMatrizAdyacencia(false)); 
								System.out.println("\nDistancias más cortas entre todos los pares de vértices");
							    for (int ind1 = 0; ind1 < tiem.length; ind1++) {  
							        for (int ind2 = 0; ind2 < tiem.length; ind2++) {  
							        	System.out.printf("%d ", tiem[ind1][ind2]);  
							        }
							        
							        System.out.println();  
							    }
							    
							    menuTiempo = false;
							}
							
							else if (opcion2 > 2 || opcion2 <= 0) {
								System.out.println("\nERROR: Por favor ingrese una opción válida\n");
							}
							
						}while(menuTiempo);
						
						menuTiempo = true;
					}
					
					else if(opcion1 == 5) {
						do {
							System.out.print("¿En base a qué desea verlo?\n\t1. Distancia \n\t2. Tiempo");
							opcion2 = scanner.nextInt();
							if(opcion2 == 1) {
								grafo.kruskal(grafo.generarMatrizAdyacencia(true));
								//grafo.prim(grafo.generarMatrizAdyacencia(true));
								menuTiempo = false;
							}
							
							else if(opcion2 == 2) {
								grafo.kruskal(grafo.generarMatrizAdyacencia(false));
								//grafo.prim(grafo.generarMatrizAdyacencia(false));
								menuTiempo = false;
							}
							
							else if (opcion2 > 2 || opcion2 <= 0) {
								System.out.println("\nERROR: Por favor ingrese una opción válida\n");
							}
							
						}while(menuTiempo);
						menuTiempo = true;
						
					}
					
					else if(opcion1 == 6) {
						menuMostrar = false;
					}
					
					else if (opcion1 > 6 || opcion1 <= 0) {
						System.out.println("\nERROR: Por favor ingrese una opción válida\n");
					}
					
					opcion1 = 0;
					
				}while(menuMostrar);
			}
			
			else if (opcion1 == 5) {
				menu = false;
			}
			
			else if (opcion1 > 5 || opcion1 <= 0) {
				System.out.println("\nERROR: Por favor ingrese una opción válida\n");
			}
			
			menuMostrar = true;
			
		} while (menu);
		
	}

}
