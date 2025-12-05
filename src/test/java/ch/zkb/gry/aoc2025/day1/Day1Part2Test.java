package ch.zkb.gry.aoc2025.day1;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Part2Test {

    @Test
    void test() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day1/input.txt")) {
            long count = Day1Part2.computePassword(fis);
            assertEquals(6358, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
