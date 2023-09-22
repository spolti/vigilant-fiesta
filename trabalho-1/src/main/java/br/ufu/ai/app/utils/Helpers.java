package br.ufu.ai.app.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public abstract class Helpers {

    public static boolean hasDuplicate(int[] array) {
        Set<Integer> seen = new HashSet<>();
        for (int num : array) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    /**
     * Generate the goal state, if random generates automatically, otherwise expect user's input
     * @return
     */
    public static int[] generateState(int size, boolean random) {
        if (random){
            int[] array = new int[size];

            // Initialize the array with values from 0 to size-1
            for (int i = 0; i < size; i++) {
                array[i] = i;
            }

            // Shuffle the array using Fisher-Yates algorithm
            Random rand = new Random();
            for (int i = size - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            return array;

        } else {
            Scanner scanner = new Scanner(System.in);
            int[] goalState = new int[9];
            // From keyboard input
            while (true) {
                System.out.println("Goal State: (e.g. 012345678)");
                String line = scanner.nextLine();
                while (true) {
                    for (int i = 0; i < line.toCharArray().length; i++) {
                        goalState[i] = Character.getNumericValue(line.toCharArray()[i]);
                    }
                    if (Helpers.hasDuplicate(goalState)) {
                        plot(goalState, "Goal State - Duplicate found");
                        System.out.println("Duplicate found, try again: ");
                        line = scanner.nextLine();
                    } else {
                        break;
                    }
                }
                plot(goalState, "Goal State - definition");
                System.out.println("Is this correct? (y/n)");
                if (scanner.nextLine().equals("y")) {
                    break;
                }
            }
            return goalState;
        }
    }



    /**
     * Plot the array in a 3x3 matrix
     * @param array
     * @param state
     */
    public static void plot(int[] array, String state) {
        StringBuilder b = new StringBuilder(state);
        for (int i = 0; i < array.length; i++) {
            if (i % 3 == 0) {
                b.append("\n");
            }
            b.append(array[i] + " | ");
        }
        System.out.println(b);
    }
}
