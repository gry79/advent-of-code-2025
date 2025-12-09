package ch.zkb.gry.aoc2025.day06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day6Part2.class);

    public static long compute(InputStream is) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
            return 0;
        }

        if (lines.isEmpty()) {
            return 0;
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        // Pad all lines to same length with spaces
        for (int i = 0; i < rows; i++) {
            if (lines.get(i).length() < cols) {
                lines.set(i, String.format("%-" + cols + "s", lines.get(i)));
            }
        }

        long grandTotal = 0;
        int col = cols - 1;  // Start from the rightmost column

        while (col >= 0) {
            // Skip separator columns (full columns of spaces)
            while (col >= 0 && isColumnEmpty(lines, col, rows)) {
                col--;
            }
            if (col < 0) break;

            // Start of a new problem (from right)
            char operation = ' ';
            List<Long> numbers = new ArrayList<>();

            int problemStartCol = col;

            // Find the operator in the bottom row within this problem
            while (col >= 0 && !isColumnEmpty(lines, col, rows)) {
                char ch = lines.get(rows - 1).charAt(col);
                if (ch == '+' || ch == '*') {
                    operation = ch;
                }
                col--;
            }

            // Now col is at the leftmost column of the current problem block
            int leftCol = col + 1;

            // Extract numbers: each column in [leftCol .. problemStartCol] is one number
            for (int c = leftCol; c <= problemStartCol; c++) {
                StringBuilder numStr = new StringBuilder();

                // Build number from top to bottom (MSD to LSD)
                for (int r = 0; r < rows - 1; r++) {  // Exclude operator row
                    char ch = lines.get(r).charAt(c);
                    if (Character.isDigit(ch)) {
                        numStr.append(ch);
                    } else if (ch != ' ') {
                        // Non-digit, non-space → probably misaligned, skip this column or handle gracefully
                        numStr = new StringBuilder(); // invalidate
                        break;
                    }
                }

                if (numStr.length() > 0) {
                    try {
                        long number = Long.parseLong(numStr.toString());
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        logger.warn("Invalid number in column {}", c);
                    }
                }
            }

            // Compute result if valid
            if (!numbers.isEmpty() && (operation == '+' || operation == '*')) {
                long result = operation == '+' ? 0L : 1L;
                for (long num : numbers) {
                    if (operation == '+') {
                        result += num;
                    } else {
                        result *= num;
                    }
                }
                grandTotal += result;
                logger.debug("Problem columns {}..{}: {} {} ... = {}", leftCol, problemStartCol, numbers, operation, result);
            } else {
                logger.warn("Invalid or incomplete problem around column {}", problemStartCol);
            }

            // Move to the next problem (continue leftward)
            col = leftCol - 1;  // Will skip separators in the next loop
        }

        logger.info("Grand total (Part 2): {}", grandTotal);
        return grandTotal;
    }

    private static boolean isColumnEmpty(List<String> lines, int col, int rows) {
        for (String line : lines) {
            if (col < line.length() && line.charAt(col) != ' ') {
                return false;
            }
        }
        return true;
    }
}
