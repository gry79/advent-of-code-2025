package ch.zkb.gry.aoc2025.day08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Day8Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day8Part1.class);
    private static final int CONNECTIONS_TO_MAKE = 1000;

    static class Point {
        final int x, y, z;
        final int id;

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

        Edge(int u, int v, long distSquared) {
            this.u = u;
            this.v = v;
            this.distSquared = distSquared;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.distSquared, o.distSquared);
        }
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] size;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int pos) {
            if (parent[pos] != pos) {
                parent[pos] = find(parent[pos]);
            }
            return parent[pos];
        }

        void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            if (size[rx] < size[ry]) {
                int temp = rx; rx = ry; ry = temp;
            }
            parent[ry] = rx;
            size[rx] += size[ry];
        }

        List<Integer> getComponentSizes() {
            List<Integer> sizes = new ArrayList<>();
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == i) {
                    sizes.add(size[i]);
                }
            }
            return sizes;
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
        if (n == 0) return 0;

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
                edges.add(new Edge(a.id, b.id, distSq));
            }
        }

        // Sort edges by distance squared (ascending)
        edges.sort(Edge::compareTo);

        UnionFind uf = new UnionFind(n);

        int connectionsMade = 0;
        for (Edge edge : edges) {
            if (connectionsMade >= CONNECTIONS_TO_MAKE) break;
            uf.union(edge.u, edge.v);
            connectionsMade++;
        }

        // Get all component sizes and sort descending
        List<Integer> sizes = uf.getComponentSizes();
        sizes.sort(Comparator.reverseOrder());

        if (sizes.size() < 3) {
            logger.warn("Fewer than 3 circuits after connections");
            return 0;
        }

        long result = (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
        logger.info("Three largest circuits: {}, {}, {} → product = {}", sizes.get(0), sizes.get(1), sizes.get(2), result);
        return result;
    }
}
