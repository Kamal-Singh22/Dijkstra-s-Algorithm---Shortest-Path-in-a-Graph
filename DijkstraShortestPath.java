import java.util.*;

class DijkstraShortestPath {
    static class Node implements Comparable<Node> {
        int vertex, distance;
        
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }

    public static int[] dijkstra(int n, int[][] edges, int source) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;

            for (Node neighbor : graph.get(u)) {
                int v = neighbor.vertex, weight = neighbor.distance;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
            {0, 1, 10}, {0, 3, 5}, {1, 2, 1}, {3, 1, 3}, {3, 2, 9}, {3, 4, 2}, {4, 2, 6}
        };
        int source = 0;

        int[] shortestDistances = dijkstra(n, edges, source);
        System.out.println("Shortest distances from node " + source + ": " + Arrays.toString(shortestDistances));
    }
}
