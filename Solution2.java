/*
 * Q.2 You are building a module loader for a large software system. Each module may depend on one or more other modules. Before loading, you must ensure that the dependencies do not contain any circular dependency, as that would lead to infinite loading loops.
Given a list of N modules and their dependency relationships, determine if the dependency graph contains a cycle.
Function Signature:
bool hasCircularDependency(int n, vector<vector<int>>& edges);
Input:
An integer n representing the number of modules labeled from 0 to n - 1.
A list of edges edges, where each edge[i] = {a, b} means module a depends on module b.
Output:
Return true if there is a circular dependency, otherwise return false.


Constraints:
1 <= n <= 10^4
0 <= edges.length <= 10^5
Dependencies form a directed graph
Self-dependencies like {a, a} are valid and considered a cycle
The graph can have multiple disconnected components
*/
//Solution2
import java.util.*;

public class Solution2 {
    
    public static boolean hasCircularDependency(int n, int[][] edges) {
        int[] inDegree = new int[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            inDegree[edge[1]]++;
        }
        
        int[] queue = new int[n];
        int front = 0, rear = 0;
        
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue[rear++] = i;
            }
        }
        
        int processed = 0;
        while (front < rear) {
            int node = queue[front++];
            processed++;
            
            for (int neighbor : graph[node]) {
                if (--inDegree[neighbor] == 0) {
                    queue[rear++] = neighbor;
                }
            }
        }
        
        return processed != n;
    }
    
    public static void main(String[] args) {
        //example 1
        System.out.println("Example 1:");
        int n = 4;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Input:");
        System.out.println("n = " + n);
        System.out.println("edges = " + Arrays.deepToString(edges));
        System.out.println("Output:");
        System.out.println(hasCircularDependency(n, edges));
        System.out.println();
        
        //example 2
        System.out.println("Example 2:");
        n = 4;
        edges = new int[][]{{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Input:");
        System.out.println("n = " + n);
        System.out.println("edges = " + Arrays.deepToString(edges));
        System.out.println("Output:");
        System.out.println(hasCircularDependency(n, edges));
    }
}