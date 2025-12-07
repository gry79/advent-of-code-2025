package ch.zkb.gry.aoc2025.day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day7Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day7Part2.class);

    private static long[][] memo;

    public static long compute(InputStream is) {
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
            logger.info("Empty grid");
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
            logger.warn("No starting 'S' found in row 0");
            return 0;
        }

        memo = new long[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                memo[r][c] = -1;
            }
        }

        long totalTimelines = ways(grid, rows, cols, 0, startCol);

        logger.info("Total timelines: {}", totalTimelines);
        return totalTimelines;
    }

    private static long ways(List<String> grid, int rows, int cols, int sr, int sc) {
        if (memo[sr][sc] != -1) {
            return memo[sr][sc];
        }

        long res = 0;
        boolean reachedBottom = true;

        for (int r = sr; r < rows; r++) {
            char cell = grid.get(r).charAt(sc);
            if (cell == '^') {
                reachedBottom = false;
                long left = 0;
                long right = 0;
                if (sc - 1 >= 0) {
                    left = ways(grid, rows, cols, r, sc - 1);
                }
                if (sc + 1 < cols) {
                    right = ways(grid, rows, cols, r, sc + 1);
                }
                res = left + right;
                break;
            }
            // Treat 'S' and '.' as pass-through
        }

        if (reachedBottom) {
            res = 1;
        }

        memo[sr][sc] = res;
        return res;
    }
}
