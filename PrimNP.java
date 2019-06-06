package Project4;

public class PrimNP {

    /*
    Objective: Convert Prims MST algorithm into a degree-constrained MST
    Constrain: Maximum vertex (num of connected edges) of a node limited to constrain 'k'

    P vs NP
        P: Polynomial-time problems (O(n^k)), easy to solve and verify solution (Addition of two integers)
        NP: Nondeterministic polynomial-time problems, hard to solve, easy to verify (Finding factors of a large number is hard,
        but verifying if two given integers are factors of a large number is easy). There could be solutions of shortcuts that we
        do not know about that could lead to an NP problem becoming a P type problem. NP problems have not been proven to be solved
        in polynomial time

        NP Complete: Hardest possible NP problem
        NP Hard: At least as hard as any other NP problem

    SAT Problem
        SAT can be transformed into any existing NP problem. Since SAT = NP, if we proved that SAT was easy to solve, then we could prove P = NP,
        since P is within NP.

    **SOLUTION**
        To create a degree-constrained MST, the maximum vertex degree is set in the MST method. As the MST is created, the weight for each edge is added to find
        the total MST vertex degree. If the vertex degree goes above the constraint, an MST is not possible.

     */

    public int maxVertexDegree; //Constraint 'k'
    public int vertNum = 5; //Number of vertices (nodes)
    public int counter = 0;

    //Method to remove nodes that are above the maximum vertex degree for a single node
     /*   public void setConstraint(int graph[][], int maxVertexDegree) {
            this.maxVertexDegree = maxVertexDegree;
            int counter = 0; //Counts number of vertex degrees (edges) from a node
            for(int i = 0; i < graph.length; i++) { //Iterate through 2d array
                for(int j = 0; j < graph[i].length; j++) {
                    if(graph[i][j] != 0) { //Count number of edges
                        counter++;
                    }
                }
                if(counter > maxVertexDegree) { //If a node has more than the max number of allowed edges, remove it from the MST
                    for(int j = 0; j < graph[i].length; j++) {
                        graph[i][j] = 0; //Zero out node
                    }
                }
            }
        }*/

    int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < vertNum; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    //CONSTRAINT METHOD
    void printMST(int parent[], int n, int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertNum; i++)
            if (counter < maxVertexDegree) { //Check to see if the total weight is above the given constraint
                System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
                counter += graph[i][parent[i]]; //Add edge weight to total MST weight
            } else {
                //If total MST weight at current node is above constraint, then a MST is not possible
                System.out.println("Minimum Spanning Tree not possible with current constraint");
                System.out.println("Constraint: " + maxVertexDegree + " -  Current minimum weight: " + counter);
                break;
            }
    }

    void primMST(int graph[][], int maxVertexDegree) {

        this.maxVertexDegree = maxVertexDegree; //Constraint

        int parent[] = new int[vertNum];
        int key[] = new int[vertNum];
        Boolean mstSet[] = new Boolean[vertNum];

        for (int i = 0; i < vertNum; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;

        for (int count = 0; count < vertNum - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < vertNum; v++)

                if (graph[u][v] != 0 && mstSet[v] == false &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, vertNum, graph);
    }

    public static void main(String[] args) {
        /* Let us create the following graph
           2     3
        (0)--(1)--(2)
         |   / \   |
       6 | 8/   \5 | 7
         | /     \ |
        (3)-------(4)
              9
        */
        PrimNP t = new PrimNP();
        int graph[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        // Print the solution
        //t.setConstraint(graph, 3);
        t.primMST(graph, 4);
    }
}
