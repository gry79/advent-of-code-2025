package ch.zkb.gry.aoc2025.day2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Scanner;

public class Day2Part1 {

    private static final Logger logger = LoggerFactory.getLogger(Day2Part1.class);

    public static long compute(InputStream is) {
        String[] parts = new Scanner(is).nextLine().trim().split(",");
        long sum = 0;

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;
            String[] range = part.split("-");
            if (range.length != 2) continue;
            try {
                long low = Long.parseLong(range[0]);
                long high = Long.parseLong(range[1]);
                if (low > high) continue;
                for (long n = low; n <= high; n++) {
                    String s = String.valueOf(n);
                    int len = s.length();
                    if (len % 2 != 0) continue;
                    int half = len / 2;
                    if (s.substring(0, half).equals(s.substring(half))) {
                        sum += n;
                    }
                }
            } catch (NumberFormatException e) {
                logger.warn("Invalid range: low={}, high={}", range[0], range[1]);
            }
        }

        logger.info("Sum of invalid IDs: {}", sum);

        return sum;
    }

}
