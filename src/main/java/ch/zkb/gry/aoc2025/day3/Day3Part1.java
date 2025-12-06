package ch.zkb.gry.aoc2025.day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Day3Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day3Part1.class);

    public static long compute(InputStream is) {
        long total = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                int len = line.length();
                if (len < 2) {
                    logger.debug("Bank too short: {}", line);
                    continue;
                }

                long maxForBank = 0;
                for (int i = 0; i < len - 1; i++) {
                    for (int j = i + 1; j < len; j++) {
                        char d1 = line.charAt(i);
                        char d2 = line.charAt(j);
                        long value = (d1 - '0') * 10L + (d2 - '0');
                        if (value > maxForBank) {
                            maxForBank = value;
                        }
                    }
                }

                logger.debug("Bank: {} -> max joltage: {}", line, maxForBank);
                total += maxForBank;
            }
        } catch (IOException e) {
            logger.error("Error reading input stream", e);
        }

        logger.info("Total output joltage: {}", total);
        return total;
    }
}
