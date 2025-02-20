package ru.neoflex;

import java.util.Random;
import java.util.Scanner;

public class HackerChallenge {
    private static final int SEED = new Random().nextInt(50) + 10;
    private static final int FALSE_A = (SEED * 2 + 13) % 256;
    private static final int FALSE_B = (SEED * 3 + 7) % 256;

    private static final int HIDDEN_A = scrambleNumber(6, 7, 14);
    private static final int HIDDEN_B = scrambleNumber(11, 9, 18);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("💻 Добро пожаловать в Хакерский Челлендж!");
        System.out.println("🎯 Ваша цель: найти два скрытых числа.");
        System.out.println("⚠ Введите два числа через пробел:");

        int inputA = scanner.nextInt();
        int inputB = scanner.nextInt();
        scanner.nextLine();

        if (checkNumbers(inputA, inputB)) {
            System.out.println("🚀 Поздравляю! Ты взломал систему! Введи эти числа в другую программу.");
        } else {
            System.out.println("❌ Неверные числа! Попробуй снова.");
        }
    }

    private static int scrambleNumber(int x, int y, int shift) {
        int step1 = (x * y) << 1;
        int step2 = step1 ^ shift;
        int step3 = step2 % 256;
        return step3;
    }

    private static boolean checkNumbers(int a, int b) {
        return (descrambleNumber(a, 14) == 6 * 7) && (descrambleNumber(b, 18) == 11 * 9);
    }

    private static int descrambleNumber(int number, int shift) {
        int step1 = number % 256;
        int step2 = step1 ^ shift;
        int step3 = step2 >> 1;
        return step3;
    }
}
