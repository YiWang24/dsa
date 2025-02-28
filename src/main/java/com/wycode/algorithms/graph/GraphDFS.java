package com.wycode.algorithms.graph;

import java.util.*;

/**
 * @author WY
 * @version 1.0
 **/

public class GraphDFS {
    private final Map<Integer, List<Integer>> graph;
    private final Set<Integer> visited;
    public GraphDFS() {
        graph = new HashMap<>();
        visited = new HashSet<>();
    }

    public void addEdge(int u,int v){
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public void dfsRecursive(int node){
        if(visited.contains(node)){
            return;
        }
        visited.add(node);
        System.out.println(node);
        for(int neighbor : graph.getOrDefault(node, new ArrayList<>())){
            dfsRecursive(neighbor);
        }
    }

    public static void main(String[] args) {
        GraphDFS g = new GraphDFS();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);

        System.out.println("DFS (Recursive):");
        g.dfsRecursive(0);
    }
}
