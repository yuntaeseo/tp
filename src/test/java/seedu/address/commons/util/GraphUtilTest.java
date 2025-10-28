package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.GraphUtil.getShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class GraphUtilTest {
    @Test
    public void getShortestPath_emptyGraph_returnsEmptyList() {
        List<List<Integer>> adjList = new ArrayList<>();
        assertTrue(getShortestPath(adjList, 0, 1).isEmpty());
    }

    @Test
    public void getShortestPath_sameStartAndEnd_returnsEmptyList() {
        // Set up adjacency list, 3 nodes
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of(1, 2));
        adjList.add(List.of(0, 2));
        adjList.add(List.of(0, 1));

        assertTrue(getShortestPath(adjList, 0, 0).isEmpty());
    }

    @Test
    public void getShortestPath_unconnectedGraph_returnsEmptyList() {
        // Set up adjacency list, 4 nodes
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of());
        adjList.add(List.of(2));
        adjList.add(List.of(1, 3));
        adjList.add(List.of(2));

        assertTrue(getShortestPath(adjList, 0, 3).isEmpty());
    }

    @Test
    public void getShortestPath_clique_returnsLength2() {
        int numOfNodes = 5;
        List<List<Integer>> adjList = createClique(numOfNodes);
        assertEquals(2, getShortestPath(adjList, 0, numOfNodes - 1).size());
    }

    @Test
    public void getShortestPath_connectedGraph_correctStartAndEnd() {
        int numOfNodes = 5;
        int start = 0;
        int end = numOfNodes - 1;

        List<List<Integer>> adjList = createClique(numOfNodes);
        List<Integer> shortestPath = getShortestPath(adjList, start, end);
        assertEquals(start, shortestPath.get(0));
        assertEquals(end, shortestPath.get(shortestPath.size() - 1));
    }

    @Test
    public void getShortestPath_connectedGraph_returnsCorrectPath() {
        int start = 0;
        int end = 3;

        // set up adjacency list, 4 nodes
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of(1));
        adjList.add(List.of(2, 3));
        adjList.add(List.of(3));
        adjList.add(List.of());

        // Possible paths: 0 -> 1 -> 2 -> 3, and 0 -> 1 -> 3
        List<Integer> expectedPath = List.of(0, 1, 3);
        assertEquals(expectedPath, getShortestPath(adjList, start, end));
    }

    /**
     * Test utility function which creates a fully connected graph.
     * @param numOfNodes number of nodes in the graph.
     * @return constructed adjacency list.
     */
    private List<List<Integer>> createClique(int numOfNodes) {
        List<Integer> allNodes = Stream.iterate(0, n -> n + 1).limit(numOfNodes).toList();

        // Create adj list for fully connected graph
        List<List<Integer>> adjList = allNodes.stream()
                .map(u -> allNodes.stream()
                        .filter(v -> u != v)
                        .toList())
                .toList();

        return adjList;
    }
}
