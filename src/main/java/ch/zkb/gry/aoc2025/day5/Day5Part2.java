package ch.zkb.gry.aoc2025.day5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day5Part2.class);

    public static long compute(InputStream is) {
        List<long[]> freshRanges = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean passedBlankLine = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    passedBlankLine = true;
                    continue;
                }

                // After the blank line, we ignore everything (available IDs section)
                if (passedBlankLine) {
                    continue;
                }

                // Parse fresh range: low-high
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    try {
                        long low = Long.parseLong(parts[0].trim());
                        long high = Long.parseLong(parts[1].trim());
                        if (low <= high) {
                            freshRanges.add(new long[]{low, high});
                        } else {
                            logger.warn("Invalid range (low > high): {}", line);
                        }
                    } catch (NumberFormatException e) {
                        logger.warn("Invalid range format: {}", line);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
            return 0;
        }

        // Merge overlapping and adjacent ranges
        if (freshRanges.isEmpty()) {
            logger.info("No fresh ranges found.");
            return 0;
        }

        freshRanges.sort(Comparator.comparingLong(a -> a[0]));

        List<long[]> merged = new ArrayList<>();
        long[] current = freshRanges.get(0);

        for (int i = 1; i < freshRanges.size(); i++) {
            long[] next = freshRanges.get(i);
            if (current[1] + 1 >= next[0]) {  // Overlap or adjacent
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);

        // Calculate total number of unique fresh IDs
        long totalFresh = 0;
        for (long[] range : merged) {
            totalFresh += range[1] - range[0] + 1;
        }

        logger.info("Total number of fresh ingredient IDs (after merging ranges): {}", totalFresh);
        return totalFresh;
    }
}
