package ru.neoflex;

import java.util.Scanner;

public class HackerChallenge {
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

    private static int scrambleNumber(int x, int y, int key) {
        return ((x * y) + key) * 2;
    }

    private static boolean checkNumbers(int a, int b) {
        return (descrambleNumber(HIDDEN_A, 14) == a) && (descrambleNumber(HIDDEN_B, 18) == b);
    }

    private static int descrambleNumber(int encoded, int key) {
        return (encoded / 2) - key;
    }
}
