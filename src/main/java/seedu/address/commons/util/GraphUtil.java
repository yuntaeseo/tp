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

    /** Denotes an invalid node assuming nodes are non-negative integer indices. */
    private static final int INVALID_NODE = -1;

    /**
     * Returns the shortest path from {@code start} to {@code end}.
     * Assumes 0 <= {@code start}, {@code end} < adjList.size().
     * @param adjList graph to traverse.
     * @param start starting node index.
     * @param end ending node index.
     * @return The shortest path from the starting node to the ending node using indices denoted by {@code adjList}.
     *         Returns an empty list if {@code end} is not reachable from {@code start}.
     */
    public static ArrayList<Integer> getShortestPath(List<? extends List<? extends Integer>> adjList,
             int start, int end) {
        // Finds shortest path using BFS
        requireNonNull(adjList);
        int numOfNodes = adjList.size();
        assert 0 <= start && start < numOfNodes : "start node should be a valid index";
        assert 0 <= end && end < numOfNodes : "end node should be a valid index";

        if (start == end) {
            return new ArrayList<>();
        }

        // Set up
        int[] parent = new int[numOfNodes];
        Arrays.fill(parent, INVALID_NODE);
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

        return constructPath(parent, end);
    }

    /**
     * Returns the path constructed by tracing through {@code parent} from {@code end}.
     * @param parent array denoting the predecessor of each node in the graph traversal.
     * @param end the ending node.
     * @return the constructed path.
     */
    private static ArrayList<Integer> constructPath(int[] parent, int end) {
        ArrayList<Integer> result = new ArrayList<>();

        // no path at all
        if (parent[end] == INVALID_NODE) {
            return result;
        }

        for (int cur = end; cur != INVALID_NODE; cur = parent[cur]) {
            result.add(cur);
        }

        // reverse to get path from start to end
        Collections.reverse(result);

        return result;
    }
}
