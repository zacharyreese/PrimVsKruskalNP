package Project4;

//Zachary Reese

public class Comparison {

    /*
    The main difference between the way the two degree-constrained algorithms are implemented is when the comparison for the constraint
    is made. In Prim's algorithm, the entire MST is created, and then the degree-constraint is checked against the maximum vertex degree, and a result is achieved.

    In Kruskal's algorithm, the constraint is checked every time a new edge and node are connected, making Kruskal's algorithm more efficient, as it does not have to create the entire MST
    before checking to see if it falls withing the degree constraint.

    As both of these problems are NP-Hard, and we could not reduce the problem into a polynomial time, we cannot prove that P = NP.

    This assignment has made me realize the importance of the question "Does P = NP", as it could lead to an extreme advancement in computer science, making many difficult to
    solve problems, very easy. It would also invalidate many forms of encryption as we rely on the hope that finding prime factors of extremely large numbers is hard, but
    would be made easy if we found that P = NP.

     */

    public static void main(String[] args) {
        PrimNP prim = new PrimNP();

        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        KruskalNP kruskal = new KruskalNP(V, E);


        /* Prim */
        System.out.println("Prim:");

         /* Let us create the following graph
           2     3
        (0)--(1)--(2)
         |   / \   |
       6 | 8/   \5 | 7
         | /     \ |
        (3)-------(4)
              9
        */

        int graph[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        // Print the solution
        //t.setConstraint(graph, 3);
        prim.primMST(graph, 4); //(Graph, constraint)

        System.out.println("\n");

        /* Kruskal */
        System.out.println("Kruskal:");

         /* Let us create following weighted graph

                 10
            0--------1
            | \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */

        // add edge 0-1
        kruskal.edge[0].src = 0;
        kruskal.edge[0].dest = 1;
        kruskal.edge[0].weight = 10;
        // add edge 0-2
        kruskal.edge[1].src = 0;
        kruskal.edge[1].dest = 2;
        kruskal.edge[1].weight = 6;
        // add edge 0-3
        kruskal.edge[2].src = 0;
        kruskal.edge[2].dest = 3;
        kruskal.edge[2].weight = 5;
        // add edge 1-3
        kruskal.edge[3].src = 1;
        kruskal.edge[3].dest = 3;
        kruskal.edge[3].weight = 15;
        // add edge 2-3
        kruskal.edge[4].src = 2;
        kruskal.edge[4].dest = 3;
        kruskal.edge[4].weight = 4;

        kruskal.KruskalMST(3);

    }
}
