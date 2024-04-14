package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	
	// Creaci�n inicial de la estructura grafo, lista para a�adir aristas en cualquier nodo
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
		
		/* Se a�ade el nuevo nodo luego de ser identificado, para poder agregarle aristas, adem�s,
		 * se retorna para tenerlo a mano en caso necesario */
		Nodo nuevoNodo = new Nodo();
		nuevoNodo.setId(id);
		grafo.add(nuevoNodo);
		id++;
		size++;
		
		return nuevoNodo;
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		
		// Se a�ade una nueva arista en ambos nodos, ya que el grafo es no dirigido
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
		
		for (Nodo nodo : grafo) {
			
			/* Recorrido de todas las aristas de todos los nodos, para verificar si el nodo que
			 * se desea eliminar, est� presente en alguna de las aristas o conexiones de otro nodo.
			 * Se realiza en reversa para evitar problemas de manejo de �ndices al
			 * momento de borrar e iterar al mismo tiempo */
			int cantAristas = nodo.getAristas().size();
			for (int ind = cantAristas-1; ind >= 0; ind--) {
				
				if (nodo.getAristas().get(ind).getOrigen() == nodoAEliminar ||
					nodo.getAristas().get(ind).getDestino() == nodoAEliminar)
					nodo.getAristas().remove(ind);
				
			}
			
		}
		
		// Eliminaci�n del nodo que, finalmente, queda sin aristas
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

	    // Asociamiento de IDs de los nodos con sus �ndices en la matriz de adyacencia
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
	
}
