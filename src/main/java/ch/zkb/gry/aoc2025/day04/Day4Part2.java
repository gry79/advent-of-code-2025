package ch.zkb.gry.aoc2025.day04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day4Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day4Part2.class);

    private static final char ROLL = '@';
    private static final char EMPTY = '.';
    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    public static int compute(InputStream is) {
        List<char[]> grid = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    grid.add(line.toCharArray());
                }
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
            return 0;
        }

        if (grid.isEmpty()) {
            logger.info("Empty grid");
            return 0;
        }

        int rows = grid.size();
        int cols = grid.get(0).length;

        int totalRemoved = 0;
        boolean removedSomething;

        do {
            removedSomething = false;
            Queue<int[]> toRemove = new LinkedList<>();

            // Find all currently accessible rolls
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid.get(r)[c] == ROLL) {
                        int neighborCount = countNeighbors(grid, r, c, rows, cols);
                        if (neighborCount < 4) {
                            toRemove.add(new int[]{r, c});
                        }
                    }
                }
            }

            // Remove all accessible rolls in this wave
            int iterationRemoved = 0;
            while (!toRemove.isEmpty()) {
                int[] pos = toRemove.poll();
                int r = pos[0];
                int c = pos[1];
                if (grid.get(r)[c] == ROLL) {
                    grid.get(r)[c] = EMPTY;
                    totalRemoved++;
                    iterationRemoved++;
                    removedSomething = true;
                }
            }

            logger.debug("Removed {} rolls in this iteration. Total so far: {}", iterationRemoved, totalRemoved);

        } while (removedSomething);

        logger.info("Total rolls removed: {}", totalRemoved);
        return totalRemoved;
    }

    private static int countNeighbors(List<char[]> grid, int r, int c, int rows, int cols) {
        int count = 0;
        for (int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid.get(nr)[nc] == ROLL) {
                count++;
            }
        }
        return count;
    }
}
