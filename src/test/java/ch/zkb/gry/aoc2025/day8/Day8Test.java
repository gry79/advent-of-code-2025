package ch.zkb.gry.aoc2025.day8;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {

    @Test
    void part1() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day8/input.txt")) {
            long count = Day8Part1.compute(fis);
            assertEquals(84968, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2() {
        try (FileInputStream fis = new FileInputStream("./src/test/resources/day8/input.txt")) {
            long count = Day8Part2.compute(fis);
            assertEquals(8663467782L, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
