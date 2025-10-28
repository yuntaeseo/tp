package seedu.address.commons.util;

import java.util.*;

/**
 * Contains utility methods for graph-related features.
 */
public class GraphUtil {
    /**
     * Returns the shortest path from {@code start} to {@code end}.
     * @param adjList graph to traverse.
     * @param start starting node index.
     * @param end ending node index.
     * @return The shortest path from the starting node the ending node using indices denoted by {@code adjList}.
     *         Returns an empty list if {@code end} is not reachable from {@code start}.
     */
    public static List<Integer> getShortestPath(List<List<Integer>> adjList, int start, int end) {
        // Finds shortest path using BFS

        int numOfNodes = adjList.size();
        final int NO_PARENT = -1;

        // Set up
        int[] parent = new int[numOfNodes];
        Arrays.fill(parent, NO_PARENT);
        boolean[] visited = new boolean[numOfNodes];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        // BFS
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == end) {
                break;
            }

            for (int adj : adjList.get(cur)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    parent[adj] = cur;
                    queue.add(adj);
                }
            }
        }

        if (parent[end] == NO_PARENT) {
            return List.of();
        }

        // reconstruct the shortest path from end to start
        ArrayList<Integer> result = new ArrayList<>();
        for (int cur = end; cur != NO_PARENT; cur = parent[cur]) {
            result.add(cur);
        }

        // reverse to get path from start to end
        Collections.reverse(result);

        return result;
    }
}
