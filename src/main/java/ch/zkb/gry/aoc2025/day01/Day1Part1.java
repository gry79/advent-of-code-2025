package ch.zkb.gry.aoc2025.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day1Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day1Part1.class);

    public static long computePassword(InputStream is) {
        int position = 50;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                char direction = line.charAt(0);
                int distance = Integer.parseInt(line.substring(1));

                int steps = distance % 100;

                if (direction == 'L') {
                    position = (position - steps + 100) % 100;
                } else if (direction == 'R') {
                    position = (position + steps) % 100;
                }

                if (position == 0) {
                    count++;
                }
            }
        } catch (IOException e) {
            logger.error("Error reading input", e);
        }

        logger.info("Total passes over 0: {}", count);
        return count;
    }

}
