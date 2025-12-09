package ch.zkb.gry.aoc2025.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day5Part1.class);

    public static long compute(InputStream is) {
        List<long[]> freshRanges = new ArrayList<>();
        List<Long> availableIds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean readingRanges = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    if (readingRanges) {
                        readingRanges = false;
                    }
                    continue;
                }

                if (readingRanges) {
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
                            logger.warn("Invalid range format (non-numeric): {}", line);
                        }
                    }
                } else {
                    // Parse available ingredient ID
                    try {
                        long id = Long.parseLong(line);
                        availableIds.add(id);
                    } catch (NumberFormatException e) {
                        logger.warn("Invalid ingredient ID: {}", line);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
            return 0;
        }

        long freshCount = 0;
        for (long id : availableIds) {
            for (long[] range : freshRanges) {
                if (id >= range[0] && id <= range[1]) {
                    freshCount++;
                    break; // No need to check further ranges
                }
            }
        }

        logger.info("Number of fresh ingredient IDs: {}", freshCount);
        return freshCount;
    }
}
