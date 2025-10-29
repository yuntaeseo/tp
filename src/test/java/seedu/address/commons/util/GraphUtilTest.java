package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.GraphUtil.getShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class GraphUtilTest {
    @Test
    public void getShortestPath_nullAdjList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> getShortestPath(null, 0, 0));
    }

    @Test
    public void getShortestPath_invalidAdjList_throwsAssertionError() {
        // List too short
        List<List<Integer>> adjList = new ArrayList<>();
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, 0, 1));
    }

    @Test
    public void getShortestPath_invalidEndPoints_throwsAssertionError() {
        // size 1 adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of(1));

        // invalid start point
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, 2, 0));
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, -1, 0));

        // invalid end point
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, 0, -1));
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, 0, 2));

        // invalid start and end points
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, -1, 2));
        assertThrows(AssertionError.class, () -> getShortestPath(adjList, 100, 200));
    }

    @Test
    public void getShortestPath_emptyGraph_returnsEmptyList() {
        // Set up adjacency list, 2 nodes
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of());
        adjList.add(List.of());

        assertTrue(getShortestPath(adjList, 0, 1).isEmpty());
    }

    @Test
    public void getShortestPath_sameStartAndEnd_returnsSingletonList() {
        // Set up adjacency list, 3 nodes
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of(1, 2));
        adjList.add(List.of(0, 2));
        adjList.add(List.of(0, 1));

        ArrayList<Integer> path = getShortestPath(adjList, 0, 0);
        assertEquals(1, path.size());
        assertEquals(0, path.get(0));
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
