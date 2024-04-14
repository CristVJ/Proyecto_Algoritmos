package clases;

import java.util.ArrayList;

public class Grafo {
	
	private int size;
	private int idVertice;
	private ArrayList<ArrayList<Integer>> matrizAdyacencia;
	
	public Grafo() {
		super();
		this.matrizAdyacencia = new ArrayList<ArrayList<Integer>>();
	}

	public int getSize() {
		return size;
	}
	
	public int getIdVertice() {
		return idVertice;
	}
	
	public ArrayList<ArrayList<Integer>> getMatrizAdyacencia() {
		return matrizAdyacencia;
	}

	public void setMatrizAdyacencia(ArrayList<ArrayList<Integer>> matrizAdyacencia) {
		this.matrizAdyacencia = matrizAdyacencia;
	}
	
	public void inicializarMatriz(int cantVertices) {
		
		size = cantVertices;
		
		for (int ind1 = 0; ind1 < cantVertices; ind1++) {
		
			ArrayList<Integer> nuevoVertice = new ArrayList<Integer>();
			for (int ind2 = 0; ind2 < cantVertices; ind2++)
				nuevoVertice.add(0);
			
			matrizAdyacencia.add(nuevoVertice);
		}
	}
	
	// Añadir un nuevo vértice al grafo, además de agregar un nuevo espacio disponible en cada vértice
	public void crearVertice(int cantVertices) {
		
		ArrayList<Integer> nuevoVertice = new ArrayList<Integer>();
		for (int ind = 0; ind < cantVertices; ind++) {
			nuevoVertice.add(0);
			if (ind < cantVertices-1)
				matrizAdyacencia.get(ind).add(0);
		}
		
		matrizAdyacencia.add(nuevoVertice);
		size++;
	}
	
	public void eliminarVertice(int verticeAEliminar) {
		
		for (ArrayList<Integer> vertice : matrizAdyacencia)
			vertice.remove(verticeAEliminar);
		
		matrizAdyacencia.remove(verticeAEliminar);
		size--;
		
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		
		matrizAdyacencia.get(origen).set(destino, peso);
		matrizAdyacencia.get(destino).set(origen, peso);
	}
	
	public void imprimirMatriz() {
		
		for (int ind1 = 0; ind1 < size; ind1++) {
			
			for (int ind2 = 0; ind2 < size; ind2++) 
				System.out.print(matrizAdyacencia.get(ind1).get(ind2)+" ");
			
			System.out.println();
		}
		
	}
	
}
