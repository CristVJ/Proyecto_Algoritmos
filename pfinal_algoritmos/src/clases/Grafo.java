package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Scanner;

public class Grafo {
	
	/* El atributo id sirve para controlar el número identificador que se le colocará a un nodo
	 * cada vez que se cree. Es único e irremplazable */
	private int id;
	private int size;
	private ArrayList<Nodo> grafo;

	public Grafo() {
		super();
		this.grafo = new ArrayList<Nodo>();
	}

	public int getSize() {
		return size;
	}
	
	public ArrayList<Nodo> getGrafo() {
		return grafo;
	}

	public void setGrafo(ArrayList<Nodo> grafo) {
		this.grafo = grafo;
	}
	
	// Su objetivo es encontrar un nodo a partir de su id único, y retornarlo en caso de que exista
	public Nodo buscarNodoById(int idNodo) {
		
		Nodo nodoABuscar = null;
		boolean encontrado = false;
		int ind = 0;
		
		while (!encontrado && ind < size) {
			
			if (grafo.get(ind).getId() == idNodo) {
				
				nodoABuscar = grafo.get(ind);
				encontrado = true;
				
			}
			
			ind++;
		}
		
		return nodoABuscar;
	}
	
	// Creación inicial de la estructura grafo, lista para añadir aristas en cualquier nodo inicial
	public void inicializarGrafo(int cantNodos) {
		
		size = cantNodos;
		
		for (int ind = 0; ind < cantNodos; ind++) {
		
			Nodo nuevoNodo = new Nodo();
			nuevoNodo.setId(ind);
			grafo.add(nuevoNodo);
			id++;
		}
	}
	
	public Nodo crearNodo() {
		
		/* Se añade el nuevo nodo luego de ser identificado, para poder agregarle aristas, además,
		 * se retorna para tenerlo a mano y agregarle aristas directamente */
		Nodo nuevoNodo = new Nodo();
		nuevoNodo.setId(id);
		grafo.add(nuevoNodo);
		id++;
		size++;
		
		return nuevoNodo;
	}
	
	/* Se agrega una arista con las características que se le pasen por parámetro en cada nodo (origen y destino),
	 * pues, en este proyecto, se está trabajando con grafos no dirigidos, ya que los algoritmos de Prim y Kruskal
	 * están diseñados para trabajar directamente con esta variante de la estructura de datos abstracta */
	public void agregarArista(int origen, int destino, int distancia, int tiempo) {
		
		Nodo nodoOrigen, nodoDestino;
		
		Arista nuevaArista1 = new Arista();
		nuevaArista1.setOrigen(origen);
		nuevaArista1.setDestino(destino);
		nuevaArista1.setDistancia(distancia);
		nuevaArista1.setTiempo(tiempo);
		
		nodoOrigen = buscarNodoById(origen);
		nodoOrigen.getAristas().add(nuevaArista1);
		
		Arista nuevaArista2 = new Arista();
		nuevaArista2.setOrigen(destino);
		nuevaArista2.setDestino(origen);
		nuevaArista2.setDistancia(distancia);
		nuevaArista2.setTiempo(tiempo);
		
		nodoDestino = buscarNodoById(destino);
		nodoDestino.getAristas().add(nuevaArista2);
	}
	
	public void eliminarNodo(int nodoAEliminar) {
		
		Nodo nodoAVerificar = buscarNodoById(nodoAEliminar);
		
		if (nodoAVerificar != null) {
			
			for (Nodo nodo : grafo) {
				
				/* Recorrido de todas las aristas de todos los nodos, para verificar si el nodo que
				 * se desea eliminar, está presente en alguna de las aristas o conexiones de otro nodo.
				 * Se realiza en reversa para evitar problemas de manejo de índices al
				 * momento de borrar e iterar al mismo tiempo */
				int cantAristas = nodo.getAristas().size();
				for (int ind = cantAristas-1; ind >= 0; ind--) {
					
					if (nodo.getAristas().get(ind).getOrigen() == nodoAEliminar ||
						nodo.getAristas().get(ind).getDestino() == nodoAEliminar)
						nodo.getAristas().remove(ind);
					
				}
				
			}
			
			// Eliminación del nodo que, finalmente, queda sin aristas
			int ind = 0;
			boolean eliminado = false;
			
			while (ind < size && !eliminado) {
				
				if (grafo.get(ind).getId() == nodoAEliminar) {
					grafo.remove(ind);
					size--;
					eliminado = true;
				}
				
				ind++;
			}
			
			eliminado = true;
		}
	}
	
	/* Se decidió que, al eliminar una arista, se borren las conexiones de ambos lados, para evitar incongruencias
	 * en el manejo de los nodos del grafo no dirigido */
	public void eliminarArista(Arista aristaAEliminar) {
		
		if (aristaAEliminar != null) {
			
			Nodo nodoOrigen = buscarNodoById(aristaAEliminar.getOrigen());
			Nodo nodoDestino = buscarNodoById(aristaAEliminar.getDestino());
			
			int ind = 0, cantAristas = nodoOrigen.getAristas().size();
			boolean eliminado = false;
			
			// Primero, se busca la arista en el ArrayList de aristas del nodo de origen y se elimina
			while (ind < cantAristas && !eliminado) {
				
				if (nodoOrigen.getAristas().get(ind).getOrigen() == aristaAEliminar.getOrigen() &&
					nodoOrigen.getAristas().get(ind).getDestino() == aristaAEliminar.getDestino()) {
					nodoOrigen.getAristas().remove(ind);
					eliminado = true;
				}
					
				ind++;
			}
			
			ind = 0; 
			cantAristas = nodoDestino.getAristas().size();
			
			// Luego, se busca la arista en el ArrayList de aristas del nodo de destino y se elimina
			while (ind < cantAristas && !eliminado) {
				
				if (nodoDestino.getAristas().get(ind).getOrigen() == aristaAEliminar.getDestino() &&
					nodoDestino.getAristas().get(ind).getDestino() == aristaAEliminar.getOrigen()) {
					nodoDestino.getAristas().remove(ind);
					eliminado = true;
				}
					
				
				ind++;
			}
		}
	}
	
	/* Se le denominó "Arista adyacente" a la arista que conecta los mismos nodos que otra, pero en sentido
	 * contrario. Esta función resulta útil para editar los pesos (distancia o tiempo) luego de haber hallado
	 * el par de conexiones */
	public Arista encontrarAristaAdyacente(Arista arista) {
		
		Arista aristaAdyacente = null;
		Nodo nodoDestino = buscarNodoById(arista.getDestino());
		int ind = 0, cantAristas = nodoDestino.getAristas().size();
		boolean encontrado = false;
		
		while (ind < cantAristas && !encontrado) {
			
			aristaAdyacente = nodoDestino.getAristas().get(ind);
			
			if (verificarAristasAdyacentes(arista, aristaAdyacente))
				encontrado = true;
			
			
			ind++;			
		}
		
		return aristaAdyacente;
	}
	
	// Retorna verdadero si ambas aristas conectan a los mismos nodos; falso en caso contrario
	public boolean verificarAristasAdyacentes(Arista arista1, Arista arista2) {
		
		if (arista1.getOrigen() == arista2.getDestino() && arista1.getDestino() == arista2.getOrigen())
			return true;
		else 
			return false;
		
	}
	
	// Listado organizado de todas las aristas que posee un nodo, junto con las propiedades de la misma
	public void listarAristasNodo(Nodo nodo) {
		
		int cantAristas = nodo.getAristas().size();
		
		for (int ind = 0; ind < cantAristas; ind++)
			System.out.printf("%-2d. ( %-2d ) <---> ( %-2d ) | Distancia: %d | Tiempo: %d|\n", ind+1, nodo.getAristas().get(ind).getOrigen(),
							  nodo.getAristas().get(ind).getDestino(), nodo.getAristas().get(ind).getDistancia(), nodo.getAristas().get(ind).getTiempo());
	
	}
	
	/* Generación de matriz adyacencia para ser utilizada en la ejecución de los algoritmos. Si "usarDistancia"
	 * es verdadero, se utilizará el atributo distancia como peso de la arista; en caso contrario, se usará el tiempo */
	public int[][] generarMatrizAdyacencia(boolean usarDistancia) {
		
	    // Inicializar la matriz de adyacencia con ceros
	    int matrizAdyacencia[][] = new int[size][size];

	    // Asociamiento de IDs de los nodos con sus índices en la matriz de adyacencia
	    Map<Integer, Integer> idToIndMap = new HashMap<>();
	    for (int ind = 0; ind < grafo.size(); ind++)
	        idToIndMap.put(grafo.get(ind).getId(), ind);

	    // Guardado de los pesos (distancia o tiempo) de las aristas en la matriz de adyacencia
	    for (Nodo nodo : grafo) {
	    	
	        int indOrigen = idToIndMap.get(nodo.getId());
	        for (Arista arista : nodo.getAristas()) {
	        	
	            int indDestino = idToIndMap.get(arista.getDestino());
	            
	            if (usarDistancia) {
	            	
		            matrizAdyacencia[indOrigen][indDestino] = arista.getDistancia();
		            matrizAdyacencia[indDestino][indOrigen] = arista.getDistancia();
	            }
	            else {
					
		            matrizAdyacencia[indOrigen][indDestino] = arista.getTiempo();
		            matrizAdyacencia[indDestino][indOrigen] = arista.getTiempo();
				}

	        }
	    }

	    return matrizAdyacencia;
	}
	
	// Despliegue organizado e identificado en consola de la matriz de adyacencia descriptiva del grafo no dirigido
	public void imprimirMatrizAdyacencia(boolean usarDistancia) {
		
		int matriz[][] = generarMatrizAdyacencia(usarDistancia);
		
		System.out.print("\t   ");
		for (int ind = 0; ind < size; ind++) {
			System.out.printf("- %-3d", grafo.get(ind).getId());
		}
		System.out.print("\n\t");
		for (int ind1 = 0; ind1 < size; ind1++) {
			System.out.printf("%-2d ", grafo.get(ind1).getId());
			for (int ind2 = 0; ind2 < size; ind2++) {
				System.out.printf("| %-3d", matriz[ind1][ind2]);
			}
			System.out.print("\n\t");
		}
		
		System.out.println();
	}
	
	public void prim (int matriz[][]) {
		
	    // Se inicializa el valor de infinito como el máximo valor entero posible
		int INF = Integer.MAX_VALUE;
		int cantAristas = 0;
		
		// Arreglo para marcar nodos visitados
		boolean[] visitado = new boolean[size];
		
		for (int ind = 0; ind < size; ind++) 
			visitado[ind] = false;

		visitado[0] = true;

		System.out.println("\n\tArista : Peso");

		while (cantAristas < size - 1) {
    	
	    	int min = INF;
		    int indFil = 0; 
		    int indCol = 0;
		    
	        // Se itera sobre todos los nodos visitados para encontrar la arista mínima que conecta un nodo visitado con uno no visitado
		    for (int ind1 = 0; ind1 < size; ind1++) {
	    	
		    	if (visitado[ind1] == true) {
        	
		    		for (int ind2 = 0; ind2 < size; ind2++) {
            
		    			if (!visitado[ind2] && matriz[ind1][ind2] != 0) {
		    				
                            // Se actualiza la mínima arista y los índices de fila y columna si se encuentra una menor
		    				if (min > matriz[ind1][ind2]) {
            	  
		    					min = matriz[ind1][ind2];
		    					indFil = ind1;
		    					indCol = ind2;
		    				}
		    			}
		    		}
		    	}
		    }
	    
		    System.out.println("\t"+indFil + " - " + indCol + " :  " + matriz[indFil][indCol]);
		    visitado[indCol] = true;
		    cantAristas++;
		}	
	}
	
	public static int encontrarVertice(int padre[], int i) {
		
	    while (padre[i] != i)
	        i = padre[i];
	    
	    return i;
	}
	 
	public static void union(int padre[], int i, int j) {
		
		int a = encontrarVertice(padre, i);
	    int b = encontrarVertice(padre, j);
	    padre[a] = b;
	}
	
	public void kruskal(int matriz[][]) {
		
	    // Arreglo para almacenar los padres de los nodos en los conjuntos
		int[] padre = new int[size];
		int costoMinimo = 0;
		int INF = Integer.MAX_VALUE;
		
	    // Se inicializa cada nodo como su propio padre.
	    for (int ind = 0; ind < size; ind++)
	        padre[ind] = ind;
	    
	    int cantAristas = 0;
	    while (cantAristas < size - 1) {
	    	
	        int min = INF, a = -1, b = -1;
	        // Se itera sobre todas las aristas para encontrar la mínima que no forme un ciclo
	        for (int ind1 = 0; ind1 < size; ind1++) {
	            for (int ind2 = 0; ind2 < size; ind2++) {
	            	
	            	/* Se actualiza la arista mínima y sus nodos asociados si los nodos no pertenecen al mismo conjunto
	            	 * y el peso de la arista es menor que el mínimo actual */
	                if (encontrarVertice(padre, ind1) != encontrarVertice(padre, ind2) && matriz[ind1][ind2] < min) {
	                	
	                    min = matriz[ind1][ind2];
	                    a = ind1;
	                    b = ind2;
	                }
	            }
	        }
	 
	        union(padre, a, b);
	        System.out.printf("\n\tArista %d:(%d, %d) costo:%d \n", cantAristas++, a, b, min);
	        costoMinimo += min;
	    }
	    
	    System.out.printf("\n\tCosto mínimo = %d \n", costoMinimo);
	}
	
	public void dijkstra(int[][] matriz, int verticeIn, int nodoDest, Grafo graf) {

	    boolean[] verticesVisitados = new boolean[size];
	    int[] distancias = new int[size];
	    int[] predecesores = new int[size];
	    Map<Integer, Integer> idToIndMap = new HashMap<>();
	    
	    for (int ind = 0; ind < size; ind++) {
	        verticesVisitados[ind] = false;
	        distancias[ind] = Integer.MAX_VALUE;
	        predecesores[ind] = -1; 
	        idToIndMap.put(grafo.get(ind).getId(), ind);
	    }
	    int indOrigen = idToIndMap.get(graf.buscarNodoById(verticeIn).getId());
	    int indDestino = idToIndMap.get(graf.buscarNodoById(nodoDest).getId());
	    distancias[indOrigen] = 0;

	    while (!verticesVisitados[indDestino]) {
	        int aux = encontrarMenorDistancia(distancias, verticesVisitados);
	        verticesVisitados[aux] = true;

	        for (int ind1 = 0; ind1 < size; ind1++) {
	            if (!verticesVisitados[ind1] && matriz[aux][ind1] != 0 &&
	                (distancias[aux] + matriz[aux][ind1] < distancias[ind1])) {
	                distancias[ind1] = distancias[aux] + matriz[aux][ind1];
	                predecesores[ind1] = aux;
	            }
	        }
	    }

	    imprimirCamino(predecesores, indDestino, indOrigen, distancias);
	}
    
	// Esta función tiene como finalidad encontrar el vértice con la distancia mínima entre los vértices no visitados
	private static int encontrarMenorDistancia(int[] distancias, boolean[] verticesVisitados) {
		
		int distanciaMinima = Integer.MAX_VALUE;  
		int distanciaMinimaAlVertice = -1;
		int cantDistancias = distancias.length;
		
		for (int ind = 0; ind < cantDistancias; ind++) {
			
			if (!verticesVisitados[ind] && distancias[ind] < distanciaMinima) {  
				distanciaMinima = distancias[ind];  
				distanciaMinimaAlVertice = ind;  
			}  
		}  
		
		return distanciaMinimaAlVertice;  
	} 
	
	
	private static void imprimirCamino(int[] predecesores, int verticeDestino, int verticeInicial, int[] distancias) {
		
		// Lista encargada de almacenar el camino
		List<Integer> camino = new ArrayList<>();
	    int verticeActual = verticeDestino;
	    
	    // Se recorre el camino desde el vértice destino hasta el inicial, mediante los predecesores
	    while (verticeActual != -1 && verticeActual != verticeInicial) {
	        camino.add(0, verticeActual);
	        verticeActual = predecesores[verticeActual];
	    }
	    camino.add(0, verticeInicial);
	    
	    // Despliegue organizado del camino y el peso total
	    System.out.print("\n\tCamino desde " + verticeInicial + " hasta " + verticeDestino + ": ");
	    int pesoTotal = distancias[verticeDestino];
	    for (int ind = 0; ind < camino.size(); ind++) {
	        System.out.print(camino.get(ind));
	        if (ind != camino.size() - 1) {
	            System.out.print(" -> ");
	        }
	    }
	    
	    System.out.println(" | Peso total: " + pesoTotal);
	}
	
	public int[][] floydwarshall(int matrizAdyacencia[][]) {  
		
		int INF = Integer.MAX_VALUE; 
		int sizeMatriz = matrizAdyacencia.length;  
		int distancias[][] = crearMatrizDeDistancias(matrizAdyacencia);  
		
	    // Se realiza el proceso de actualización de las distancias mínimas entre todos los pares de vértices
		for (int ind1 = 0; ind1 < sizeMatriz; ind1++) {  
			for (int ind2 = 0; ind2 < sizeMatriz; ind2++) {  
				for (int ind3 = 0; ind3 < sizeMatriz; ind3++) {
	                // Si existe un camino entre los vértices ind2 e ind3 pasando por el vértice ind1 y es más corto que el camino directo...
					if (distancias[ind2][ind1] < INF && distancias[ind1][ind3] < INF)  
						distancias[ind2][ind3] = Math.min(distancias[ind2][ind3], distancias[ind2][ind1] + distancias[ind1][ind3]);  
				}  
			}  
		}  
		
		// Matriz contenedora de las distancias mínimas entre todos los pares de vértices
		return distancias;
	}
	
	// Función para crear una matriz de distancias inicializada a partir de una matriz de adyacencia.
	private int[][] crearMatrizDeDistancias(int matrizAdyacencia[][]) {
		
		int INF = Integer.MAX_VALUE; 
		int distancias[][] = new int[size][size];  
		
		for (int ind1 = 0; ind1 < size; ind1++) {  
			for (int ind2 = 0; ind2 < size; ind2++) {  
    		   
				if (ind1 == ind2)  
					distancias[ind1][ind2] = 0;  
				else if (matrizAdyacencia[ind1][ind2] == 0)  
					distancias[ind1][ind2] = INF;  
				else  
					distancias[ind1][ind2] = matrizAdyacencia[ind1][ind2];  
			}  
		}
       
		return distancias;  
	}
	
}
