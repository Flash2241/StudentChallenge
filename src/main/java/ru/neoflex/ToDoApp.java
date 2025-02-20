package ru.neoflex;

import java.io.*;
import java.util.*;

public class ToDoApp {
    private static final String FILE_NAME = "tasks.txt";
    private static final List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Выберите действие: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                System.out.println("🔍 DEBUG: Введённое число: " + choice); // Отладка

                if (isSecretNumber(choice)) {
                    System.out.println("🚀 DEBUG: Число подходит! Открываем секретное меню."); // Отладка
                    secretMenu(scanner);
                    continue;
                }

                switch (choice) {
                    case 1 -> addTask(scanner);
                    case 2 -> showTasks();
                    case 3 -> deleteTask(scanner);
                    case 4 -> markTaskDone(scanner);
                    case 5 -> searchTasks(scanner);
                    case 6 -> sortTasks();
                    case 7 -> System.out.println("🔧 Данный пункт в разработке...");
                    case 8 -> {
                        saveTasks();
                        System.out.println("💾 Задачи сохранены. До свидания!");
                        running = false;
                    }
                    default -> System.out.println("⚠ Ошибка: неверный ввод.");
                }
            } else {
                System.out.println("⚠ Ошибка: введите число.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Показать задачи");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Отметить выполненной");
        System.out.println("5. 🔍 Поиск задачи (заглушка)");
        System.out.println("6. 🔄 Сортировка задач (заглушка)");
        System.out.println("7. 🚧 Разработка новой функции");
        System.out.println("8. Сохранить и выйти");
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Введите задачу: ");
        tasks.add("[ ] " + scanner.nextLine());
        System.out.println("✅ Задача добавлена!");
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("⚠ Нет задач.");
            return;
        }
        System.out.println("\n📋 Ваши задачи:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Введите номер задачи для удаления: ");
        int taskIndex = scanner.nextInt() - 1;
        if (isValidIndex(taskIndex)) {
            tasks.remove(taskIndex);
            System.out.println("🗑 Задача удалена!");
        } else {
            System.out.println("⚠ Ошибка: неверный номер задачи.");
        }
    }

    private static void markTaskDone(Scanner scanner) {
        System.out.print("Введите номер задачи для отметки: ");
        int taskIndex = scanner.nextInt() - 1;
        if (isValidIndex(taskIndex)) {
            tasks.set(taskIndex, tasks.get(taskIndex).replace("[ ]", "[✔]"));
            System.out.println("✅ Задача выполнена!");
        } else {
            System.out.println("⚠ Ошибка: неверный номер задачи.");
        }
    }

    private static void searchTasks(Scanner scanner) {
        System.out.println("🔍 Функция поиска пока не реализована.");
    }

    private static void sortTasks() {
        System.out.println("🔄 Функция сортировки пока не реализована.");
    }

    private static void secretMenu(Scanner scanner) {
        System.out.println("\n🚀 Тайное меню активировано! Введите 'admin' для доступа.");
        String input = scanner.nextLine();
        if ("admin".equalsIgnoreCase(input)) {
            System.out.println("\n💻 Админ-панель:");
            System.out.println("1. Режим суперпользователя (ничего не делает, но звучит круто)");
            System.out.println("2. Выключить систему (шутка)");
            System.out.println("3. Вернуться в меню");
            scanner.nextInt();
        } else {
            System.out.println("❌ Неверный ввод.");
        }
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.out.println("⚠ Ошибка при сохранении.");
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("⚠ Ошибка при загрузке.");
        }
    }

    private static boolean isSecretNumber(int number) {
        int hash = (number * 7 + 3) % 256;
        return hash == 297 % 256 || hash == 696 % 256;
    }
}
