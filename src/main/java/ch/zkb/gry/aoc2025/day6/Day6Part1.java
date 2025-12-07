package ch.zkb.gry.aoc2025.day6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day6Part1.class);

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

        // Ensure all lines are same length (pad with spaces if needed)
        for (int i = 0; i < rows; i++) {
            if (lines.get(i).length() < cols) {
                lines.set(i, String.format("%-" + cols + "s", lines.get(i)));
            }
        }

        long grandTotal = 0;
        int col = 0;

        while (col < cols) {
            // Skip separator columns (full columns of spaces)
            while (col < cols && isColumnEmpty(lines, col, rows)) {
                col++;
            }
            if (col >= cols) break;

            // Start of a new problem
            char operation = ' ';
            List<Long> numbers = new ArrayList<>();

            // Find operation in bottom row within this problem block
            for (int c = col; c < cols; c++) {
                if (isColumnEmpty(lines, c, rows)) break;
                char ch = lines.get(rows - 1).charAt(c);
                if (ch == '+' || ch == '*') {
                    operation = ch;
                    break;
                }
            }

            // Extract all numbers from the rows above the operation row
            for (int r = 0; r < rows - 1; r++) {
                String row = lines.get(r);
                int c = col;
                while (c < cols && !isColumnEmpty(lines, c, rows)) {
                    if (Character.isDigit(row.charAt(c))) {
                        // Extract full number starting at c
                        StringBuilder num = new StringBuilder();
                        while (c < cols && Character.isDigit(row.charAt(c))) {
                            num.append(row.charAt(c));
                            c++;
                        }
                        if (num.length() > 0) {
                            numbers.add(Long.parseLong(num.toString()));
                        }
                    } else {
                        c++;
                    }
                }
            }

            // Compute result if valid
            if (!numbers.isEmpty() && (operation == '+' || operation == '*')) {
                long result = operation == '+' ? 0 : 1;
                for (long num : numbers) {
                    if (operation == '+') {
                        result += num;
                    } else {
                        result *= num;
                    }
                }
                grandTotal += result;
                logger.debug("Problem solved: {} {} ... = {} (subtotal)", numbers, operation, result);
            }

            // Move to next problem
            while (col < cols && !isColumnEmpty(lines, col, rows)) {
                col++;
            }
        }

        logger.info("Grand total: {}", grandTotal);
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
