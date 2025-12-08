package ch.zkb.gry.aoc2025.day8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Day8Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day8Part2.class);

    static class Point {
        final int id;
        final int x, y, z;

        Point(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        final int u, v;
        final long distSquared;
        final int x1, x2;  // X coordinates for the final answer

        Edge(int u, int v, long distSquared, int x1, int x2) {
            this.u = u;
            this.v = v;
            this.distSquared = distSquared;
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.distSquared, o.distSquared);
        }
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        private int components;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            components = n;
        }

        int find(int pos) {
            if (parent[pos] != pos) {
                parent[pos] = find(parent[pos]);
            }
            return parent[pos];
        }

        boolean union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return false;

            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
            components--;
            return true;
        }

        boolean isConnected() {
            return components == 1;
        }
    }

    public static long compute(InputStream is) {
        List<Point> points = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 3) continue;
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                int z = Integer.parseInt(parts[2].trim());
                points.add(new Point(id++, x, y, z));
            }
        } catch (IOException | NumberFormatException e) {
            logger.error("Error reading input", e);
            return 0;
        }

        int n = points.size();
        if (n <= 1) return 0;

        // Generate all possible edges
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point a = points.get(i);
                Point b = points.get(j);
                long dx = a.x - b.x;
                long dy = a.y - b.y;
                long dz = a.z - b.z;
                long distSq = dx * dx + dy * dy + dz * dz;
                edges.add(new Edge(a.id, b.id, distSq, a.x, b.x));
            }
        }

        // Sort edges by distance squared (ascending)
        edges.sort(Edge::compareTo);

        UnionFind uf = new UnionFind(n);

        long lastXProduct = 0;

        for (Edge edge : edges) {
            boolean merged = uf.union(edge.u, edge.v);
            if (merged && uf.isConnected()) {
                // This is the last connection that joins everything
                lastXProduct = (long) edge.x1 * edge.x2;
                logger.info("Final connecting edge between X={} and X={} → product = {}", edge.x1, edge.x2, lastXProduct);
                break;
            }
        }

        if (!uf.isConnected()) {
            logger.warn("Graph was not connected even after all edges");
            return 0;
        }

        return lastXProduct;
    }
}
