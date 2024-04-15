package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Scanner;

public class Grafo {
	
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
	
	public Nodo buscarNodoById(int idNodo) {
		
		Nodo nodoABuscar = null;
		boolean encontrado = false;
		int ind = 0;
		
		while (!encontrado && ind < grafo.size()) {
			
			if (grafo.get(ind).getId() == idNodo) {
				
				nodoABuscar = grafo.get(ind);
				encontrado = true;
				
			}
			
			ind++;
		}
		
		return nodoABuscar;
	}
	
	// Creación inicial de la estructura grafo, lista para añadir aristas en cualquier nodo
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
		 * se retorna para tenerlo a mano en caso necesario */
		Nodo nuevoNodo = new Nodo();
		nuevoNodo.setId(id);
		grafo.add(nuevoNodo);
		id++;
		size++;
		
		return nuevoNodo;
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		
		// Se añade una nueva arista en ambos nodos, ya que el grafo es no dirigido
		Arista nuevaArista1 = new Arista();
		nuevaArista1.setOrigen(origen);
		nuevaArista1.setDestino(destino);
		nuevaArista1.setPeso(peso);
		
		grafo.get(origen).getAristas().add(nuevaArista1);
		
		Arista nuevaArista2 = new Arista();
		nuevaArista2.setOrigen(destino);
		nuevaArista2.setDestino(origen);
		nuevaArista2.setPeso(peso);
		
		grafo.get(destino).getAristas().add(nuevaArista2);
		
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
	
	public void eliminarArista(Arista aristaAEliminar) {
		
		Nodo nodoOrigen = buscarNodoById(aristaAEliminar.getOrigen());
		Nodo nodoDestino = buscarNodoById(aristaAEliminar.getDestino());
		
		int ind = 0, cantAristas = nodoOrigen.getAristas().size();
		boolean eliminado = false;

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
		
		while (ind < cantAristas && !eliminado) {
			
			if (nodoDestino.getAristas().get(ind).getOrigen() == aristaAEliminar.getDestino() &&
				nodoDestino.getAristas().get(ind).getDestino() == aristaAEliminar.getOrigen()) {
				nodoDestino.getAristas().remove(ind);
				eliminado = true;
			}
				
			
			ind++;
		}
		
	}
	
	public int[][] generarMatrizAdyacencia() {
		
	    // Inicializar la matriz de adyacencia con ceros
	    int matrizAdyacencia[][] = new int[size][size];

	    // Asociamiento de IDs de los nodos con sus índices en la matriz de adyacencia
	    Map<Integer, Integer> idToIndMap = new HashMap<>();
	    for (int ind = 0; ind < grafo.size(); ind++)
	        idToIndMap.put(grafo.get(ind).getId(), ind);

	    // Guardado de los pesos de las aristas en la matriz de adyacencia
	    for (Nodo nodo : grafo) {
	    	
	        int indOrigen = idToIndMap.get(nodo.getId());
	        for (Arista arista : nodo.getAristas()) {
	        	
	            int indDestino = idToIndMap.get(arista.getDestino());
	            matrizAdyacencia[indOrigen][indDestino] = arista.getPeso();
	            matrizAdyacencia[indDestino][indOrigen] = arista.getPeso();
	        }
	    }

	    return matrizAdyacencia;
	}
	
	public void imprimirMatrizAdyacencia() {
		
		int matriz[][] = generarMatrizAdyacencia();
		
		for (int ind = 0; ind < size; ind++) {
			System.out.print("\t"+grafo.get(ind).getId()+" ");
            System.out.println(Arrays.toString(matriz[ind]));
		}
	}
	
	public void imprimirMatrizAdyacencia2() {
		
		int matriz[][] = generarMatrizAdyacencia();
		
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

		int INF = Integer.MAX_VALUE;
		int cantAristas = 0;

		boolean[] visitado = new boolean[size];
		
		for (int ind = 0; ind < size; ind++) 
			visitado[ind] = false;

		visitado[0] = true;

		System.out.println("Arista : Peso");

		while (cantAristas < size - 1) {
    	
	    	int min = INF;
		    int indFil = 0; 
		    int indCol = 0;

		    for (int ind1 = 0; ind1 < size; ind1++) {
	    	
		    	if (visitado[ind1] == true) {
        	
		    		for (int ind2 = 0; ind2 < size; ind2++) {
            
		    			if (!visitado[ind2] && matriz[ind1][ind2] != 0) {
            	
		    				if (min > matriz[ind1][ind2]) {
            	  
		    					min = matriz[ind1][ind2];
		    					indFil = ind1;
		    					indCol = ind2;
		    				}
		    			}
		    		}
		    	}
		    }
	    
		    System.out.println(indFil + " - " + indCol + " :  " + matriz[indFil][indCol]);
		    visitado[indCol] = true;
		    cantAristas++;
		}	
	}
	
	static int encontrarVertice(int padre[], int i) {
		
	    while (padre[i] != i)
	        i = padre[i];
	    
	    return i;
	}
	 
	static void union(int padre[], int i, int j) {
		
		int a = encontrarVertice(padre, i);
	    int b = encontrarVertice(padre, j);
	    padre[a] = b;
	}
	
	public void kruskal(int matriz[][]) {
		
		int[] padre = new int[size];
		int costoMinimo = 0;
		int INF = Integer.MAX_VALUE;
		
	    for (int ind = 0; ind < size; ind++)
	        padre[ind] = ind;
	    
	    int cantAristas = 0;
	    while (cantAristas < size - 1) {
	    	
	        int min = INF, a = -1, b = -1;
	        for (int ind1 = 0; ind1 < size; ind1++) {
	            for (int ind2 = 0; ind2 < size; ind2++) {
	            	
	                if (encontrarVertice(padre, ind1) != encontrarVertice(padre, ind2) && matriz[ind1][ind2] < min) {
	                	
	                    min = matriz[ind1][ind2];
	                    a = ind1;
	                    b = ind2;
	                }
	            }
	        }
	 
	        union(padre, a, b);
	        System.out.printf("Arista %d:(%d, %d) costo:%d \n", cantAristas++, a, b, min);
	        costoMinimo += min;
	    }
	    
	    System.out.printf("\n Costo mínimo = %d \n", costoMinimo);
	}
	
	public void dijkstra(int[][] matriz, int verticeInicial, int nodoDestino) {

	    boolean[] verticesVisitados = new boolean[size];
	    int[] distancias = new int[size];
	    int[] predecesores = new int[size];

	    for (int ind = 0; ind < size; ind++) {
	        verticesVisitados[ind] = false;
	        distancias[ind] = Integer.MAX_VALUE;
	        predecesores[ind] = -1; 
	    }

	    distancias[verticeInicial] = 0;

	    while (!verticesVisitados[nodoDestino]) {
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

	    imprimirCamino(predecesores, nodoDestino, verticeInicial, distancias);
	} 
    
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
	    List<Integer> camino = new ArrayList<>();
	    int verticeActual = verticeDestino;
	    while (verticeActual != -1 && verticeActual != verticeInicial) {
	        camino.add(0, verticeActual);
	        verticeActual = predecesores[verticeActual];
	    }
	    camino.add(0, verticeInicial);

	    System.out.print("Camino desde " + verticeInicial + " hasta " + verticeDestino + ": ");
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
   
		for (int ind1 = 0; ind1 < sizeMatriz; ind1++) {  
			for (int ind2 = 0; ind2 < sizeMatriz; ind2++) {  
				for (int ind3 = 0; ind3 < sizeMatriz; ind3++) {  
					if (distancias[ind2][ind1] < INF && distancias[ind1][ind3] < INF)  
						distancias[ind2][ind3] = Math.min(distancias[ind2][ind3], distancias[ind2][ind1] + distancias[ind1][ind3]);  
				}  
			}  
		}  
    
		return distancias;
	}
	
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
