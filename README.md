# Sistema de Gestión de Grafos en Java
Este proyecto consiste en un sistema de gestión de grafos desarrollado en Java que permite agregar, editar y eliminar nodos, facilitando al usuario total libertad al momento de trabajar con la estructura de datos. Además de lo anteriormente mencionado, el programa también permite utilizar algoritmos clásicos (Dijkstra, Prim, Kruskal y Floyd-Warshall) para calcular y optimizar rutas a conveniencia. Estos cálculos se podrán realizar en base a dos propiedades distintas de peso: la distancia y el tiempo, atributos que ayudarán a obtener una idea más precisa al momento de modelar situaciones de la vida real.

## Funcionalidades
- Agregar nodos al grafo.
- Modificar nodos existentes:
  - Agregar aristas.
  - Eliminar aristas.
  - Editar distancia y tiempo de aristas.
- Eliminar nodos del grafo.
- Mostrar información sobre el grafo:
  - Matriz de adyacencia actual por distancia o tiempo.
  - Ruta más corta entre dos nodos.
  - Ruta más corta de todos los nodos.
  - Árbol de expansión mínima

## Implementación
Luego de realizar un análisis exhaustivo y, para cumplir con los requisitos planteados, se tomó la decisión de contar con cuatro clases: **(1) Arista**, **(2) Nodo**, **(3) Grafo** y **(4) Main**. La clase Arista posee cuatro atributos, los cuales son el id del nodo Origen, el id del nodo Destino, la distancia y el tiempo, siendo los últimos dos valores representativos del peso. La clase Nodo posee un ArrayList de aristas, ya que un solo nodo puede tener más de una de estas, y, en adición a esto, posee un identificador id, representativo del número del nodo. Después, donde se forja la estructura de datos abstracta, es con la clase Grafo, la cual posee un ArrayList de nodos, a partir del cual se genera la matriz de adyacencia con la que se trabajará a lo largo del proyecto. Finalmente, el Main, donde todo cobra sentido mediante la asignación de opciones a un menú interactivo en consola, capaz de mostrar en todo momento los cambios realizados a la estructura.

### Clase Grafo
Al ser esta clase la más relevante, a continuación, se presentan algunos de los métodos más importantes:
- agregarNodo(): Este método permite agregar un nuevo nodo al grafo. Genera un ID único para el nodo y lo agrega a la lista grafo.
- modificarArista(Nodo nodoOrigen, boolean modificarDistancia): Permite modificar una arista de un nodo, ya sea editando la distancia o el tiempo. Recibe como parámetros el nodo al que pertenece la arista y un booleano usarDistancia que indica si se modificará la distancia (true) o el tiempo (false) de la arista.
- eliminarNodo(int nodoAEliminar): Elimina un nodo del grafo junto con todas las aristas asociadas a él. Para mantener la integridad del grafo, se actualizan los índices de los nodos en el mapa hash que enlaza los IDs de los nodos con sus índices en la matriz de adyacencia.
- mostrarRutaMasCorta(): Calcula y muestra la ruta más corta entre dos nodos utilizando el algoritmo de Dijkstra. Utiliza un método privado dijkstra(int[][] matriz, int verticeIn, int nodoDest) para realizar el cálculo.
- floydwarshall(int matrizAdyacencia[][]): Implementa el algoritmo de Floyd-Warshall para encontrar las distancias más cortas entre todos los pares de vértices en el grafo. Retorna una matriz que contiene estas distancias mínimas.
- generarMapaHash(): Genera un mapa hash que enlaza los IDs de los nodos con sus índices en la matriz de adyacencia.
- generarMatrizAdyacencia(boolean usarDistancia): Genera una matriz de adyacencia que representa las conexiones entre los nodos del grafo. Se puede especificar si se usarán las distancias o los tiempos de las aristas como pesos.
- imprimirMatrizAdyacencia(int distancias[][]): Imprime de forma organizada la matriz de adyacencia del grafo.

## Retos y aprendizajes
El manejo de la representación del grafo y la implementación de los algoritmos de ruta más corta y árboles de expansión mínima, fueron los mayores retos en el desarrollo de este proyecto. La necesidad de mantener la coherencia entre los datos y la eficiencia en los cálculos nos llevó a diseñar cuidadosamente la lógica de estos métodos, así como a implementar estructuras de datos auxiliares, como mapas hash, para optimizar las operaciones. Además, durante el proceso de desarrollo, nos enfrentamos a la complejidad de manejar los casos en los que era necesario eliminar nodos no consecutivos, lo que implicaba la actualización de los índices de los nodos en la matriz de adyacencia y en el mapa hash, garantizando la integridad y consistencia del grafo en todo momento. En este sentido, la planificación y la atención a los detalles fueron fundamentales para superar estos desafíos y lograr una implementación robusta y eficiente de nuestra clase Grafo.
