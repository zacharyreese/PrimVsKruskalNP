package Project4;

import java.util.Arrays;

public class KruskalNP {

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
        As the MST is being created, the degree-constraint is being tracked with each edge, and while the tree is being constructed, the constraint is
        constantly being checked against the counter, and if the counter goes above the constraint, then a MST is not possible
     */


    class Edge implements Comparable<Edge> {

        int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    class subset {
        int parent, rank;
    }

    int V, E;
    Edge edge[];
    KruskalNP(int v, int e) {

        V = v;
        E = e;
        edge = new Edge[E];

        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    int find(subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void Union(subset subsets[], int x, int y) {

        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    //Constraint method
    void KruskalMST(int constraint) {
        int counter = 0;

        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge);

        subset subsets[] = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            /*********************/

            //If the weight of the MST goes above the set constraint, then a MST is not possible
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
                counter += next_edge.weight; //Add the weight of each edge to the total weight counter
            }
            if(counter > constraint) {
                System.out.println("The minimum spanning tree does not exist with the set constraint");
                break;
            }
        }

        /************************************/

        System.out.println("Following are the edges in the constructed MST");

        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
    }

    public static void main(String[] args) {

        /* Let us create following weighted graph

                 10

            0--------1

            | \     |

           6|   5\   |15

            |      \ |

            2--------3

                4       */

        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        KruskalNP graph = new KruskalNP(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;
        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;
        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;
        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;
        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.KruskalMST(3);

    }
}
