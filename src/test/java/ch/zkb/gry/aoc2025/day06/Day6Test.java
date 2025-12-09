package ch.zkb.gry.aoc2025.day06;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day06/input.txt")) {
            long count = Day6Part1.compute(fis);
            assertEquals(6371789547734L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day06/input.txt")) {
            long count = Day6Part2.compute(fis);
            assertEquals(11419862653216L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
