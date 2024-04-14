package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
	
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
	
	public void inicializarGrafo(int cantNodos) {
		
		size = cantNodos;
		
		for (int ind = 0; ind < cantNodos; ind++) {
		
			Nodo nuevoNodo = new Nodo();
			nuevoNodo.setId(ind);
			grafo.add(nuevoNodo);
		}
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
		
		for (Nodo nodo : grafo) {
			
			int cantAristas = nodo.getAristas().size();
			for (int ind = cantAristas-1; ind >= 0; ind--) {
				
				if (nodo.getAristas().get(ind).getOrigen() == nodoAEliminar ||
					nodo.getAristas().get(ind).getDestino() == nodoAEliminar)
					nodo.getAristas().remove(ind);
				
			}
			
		}
		
		int ind = 0;
		boolean borrado = false;
		while (ind < size && !borrado) {
			
			if (grafo.get(ind).getId() == nodoAEliminar) {
				grafo.remove(ind);
				size--;
				borrado = true;
			}
			
			ind++;
		}
		
	}
	
	public int[][] generarMatrizAdyacencia() {
		
		int matrizAdyacencia[][] = new int[size][size];
		System.out.println(grafo.size());
		
		for (Nodo nodo : grafo) {
			
			for (Arista arista : nodo.getAristas()) {
				
				matrizAdyacencia[arista.getOrigen()][arista.getDestino()] = arista.getPeso();
				matrizAdyacencia[arista.getDestino()][arista.getOrigen()] = arista.getPeso();
			}
			
		}
	   
		return matrizAdyacencia;
	}
	
	public void imprimirMatrizAdyacencia() {
		
		int matriz[][] = generarMatrizAdyacencia();
		
		for (int ind = 0; ind < size; ind++)
            System.out.println(Arrays.toString(matriz[ind]));
	
	}
	
}
