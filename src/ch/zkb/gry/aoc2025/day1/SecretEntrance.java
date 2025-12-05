package ch.zkb.gry.aoc2025.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecretEntrance {
    public static void main(String[] args) {
        int position = 50;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) break;

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
            e.printStackTrace();
        }

        System.out.println(count);
    }
}
