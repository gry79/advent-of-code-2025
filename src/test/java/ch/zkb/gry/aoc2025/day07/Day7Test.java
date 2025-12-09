package ch.zkb.gry.aoc2025.day07;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day07/input.txt")) {
            long count = Day7Part1.compute(fis);
            assertEquals(1642, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day07/input.txt")) {
            long count = Day7Part2.compute(fis);
            assertEquals(47274292756692L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
