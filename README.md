# PrimVsKruskalNP
>A comparison of finding a minimum spanning tree in NP time using Prim and Kruskals algorithms

The main difference between the way the two degree-constrained algorithms are implemented is when the comparison for the constraint
is made. In Prim's algorithm, the entire MST is created, and then the degree-constraint is checked against the maximum vertex degree, and a result is achieved.

In Kruskal's algorithm, the constraint is checked every time a new edge and node are connected, making Kruskal's algorithm more efficient, as it does not have to create the entire MST before checking to see if it falls withing the degree constraint.

As both of these problems are NP-Hard, and we could not reduce the problem into a polynomial time, we cannot prove that P = NP.

This assignment has made me realize the importance of the question "Does P = NP", as it could lead to an extreme advancement in computer science, making many difficult to solve problems, very easy. It would also invalidate many forms of encryption as we rely on the hope that finding prime factors of extremely large numbers is hard, but would be made easy if we found that P = NP.

## Objective
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
