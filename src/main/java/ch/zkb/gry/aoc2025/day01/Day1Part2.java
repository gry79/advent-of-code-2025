package ch.zkb.gry.aoc2025.day01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Day1Part2 {

    private static final Logger logger = LoggerFactory.getLogger(Day1Part2.class);

    public static long computePassword(InputStream is) {
        int position = 50;
        long count = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                char direction = line.charAt(0);
                int distance = Integer.parseInt(line.substring(1));

                int dir = (direction == 'R') ? 1 : -1;
                int target;
                if (dir == 1) {
                    target = (100 - position) % 100;
                } else {
                    target = position % 100;
                }
                int r = (target == 0) ? 100 : target;
                long passes = 0;
                if (r <= distance) {
                    passes = 1L + (distance - r) / 100L;
                }
                count += passes;

                int effective = distance % 100;
                if (dir == 1) {
                    position = (position + effective) % 100;
                } else {
                    position = (position - effective + 100) % 100;
                }
            }
        } catch (IOException e) {
            logger.error("Error reading input", e);
        }

        logger.info("Total passes over 0: {}", count);
        return count;
    }

}
