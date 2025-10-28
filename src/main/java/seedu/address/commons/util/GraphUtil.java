package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * Contains utility methods for graph-related features.
 */
public class GraphUtil {
    /**
     * Returns the shortest path from {@code start} to {@code end}.
     * Assumes 0 <= {@code start}, {@code end} < adjList.size().
     * @param adjList graph to traverse.
     * @param start starting node index.
     * @param end ending node index.
     * @return The shortest path from the starting node the ending node using indices denoted by {@code adjList}.
     *         Returns an empty list if {@code end} is not reachable from {@code start}.
     */
    public static List<Integer> getShortestPath(List<? extends List<? extends Integer>> adjList, int start, int end) {
        // Finds shortest path using BFS
        requireNonNull(adjList);

        int numOfNodes = adjList.size();

        if (start == end || adjList.isEmpty()) {
            return List.of();
        }

        final int noParent = -1;

        // Set up
        int[] parent = new int[numOfNodes];
        Arrays.fill(parent, noParent);
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

        if (parent[end] == noParent) {
            return List.of();
        }

        // reconstruct the shortest path from end to start
        ArrayList<Integer> result = new ArrayList<>();
        for (int cur = end; cur != noParent; cur = parent[cur]) {
            result.add(cur);
        }

        // reverse to get path from start to end
        Collections.reverse(result);

        return result;
    }
}
