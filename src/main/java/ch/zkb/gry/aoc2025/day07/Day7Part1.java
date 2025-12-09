package ch.zkb.gry.aoc2025.day07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Day7Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day7Part1.class);

    public static long compute(java.io.InputStream is) {
        List<String> grid = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                grid.add(line);
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
            return 0;
        }

        if (grid.isEmpty()) {
            return 0;
        }

        int rows = grid.size();
        int cols = grid.get(0).length();

        // Find starting column for S in row 0
        int startCol = -1;
        for (int c = 0; c < cols; c++) {
            if (grid.get(0).charAt(c) == 'S') {
                startCol = c;
                break;
            }
        }
        if (startCol == -1) {
            return 0;
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> hitSplitters = new HashSet<>();

        String initialKey = startCol + "," + 0;
        visited.add(initialKey);
        queue.add(new int[]{startCol, 0});

        long count = 0;

        while (!queue.isEmpty()) {
            int[] beam = queue.poll();
            int position = beam[0];
            int sr = beam[1];

            for (int r = sr; r < rows; r++) {
                char cell = grid.get(r).charAt(position);
                if (cell == '^') {
                    String splitterKey = r + "," + position;
                    if (hitSplitters.add(splitterKey)) {
                        count++;
                    }
                    if (position - 1 >= 0) {
                        String key = (position - 1) + "," + r;
                        if (!visited.contains(key)) {
                            visited.add(key);
                            queue.add(new int[]{position - 1, r});
                        }
                    }
                    if (position + 1 < cols) {
                        String key = (position + 1) + "," + r;
                        if (!visited.contains(key)) {
                            visited.add(key);
                            queue.add(new int[]{position + 1, r});
                        }
                    }
                    break;
                }
            }
        }

        logger.info("Total beam splits: {}", count);
        return count;
    }
}
