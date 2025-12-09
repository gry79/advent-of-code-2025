package ch.zkb.gry.aoc2025.day04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day4Part1.class);

    private static final char ROLL = '@';
    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    public static int compute(InputStream is) {
        List<String> grid = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    grid.add(line);
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
        int cols = grid.get(0).length();

        int accessibleCount = 0;

        for (int r = 0; r < rows; r++) {
            String row = grid.get(r);
            if (row.length() != cols) {
                logger.warn("Row {} has inconsistent length: {}", r, row.length());
                continue;
            }
            for (int c = 0; c < cols; c++) {
                if (row.charAt(c) == ROLL) {
                    int neighborCount = countNeighbors(grid, r, c, rows, cols);
                    if (neighborCount < 4) {
                        accessibleCount++;
                    }
                }
            }
        }

        logger.info("Number of accessible rolls (@ with fewer than 4 neighbors): {}", accessibleCount);
        return accessibleCount;
    }

    private static int countNeighbors(List<String> grid, int r, int c, int rows, int cols) {
        int count = 0;
        for (int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid.get(nr).charAt(nc) == ROLL) {
                count++;
            }
        }
        return count;
    }
}
