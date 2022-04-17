import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker(scanner);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 0:
                    System.out.println("Программа завершена");
                    return;
                case 1:
                    stepTracker.saveSteps();
                    break;
                case 2:
                    stepTracker.getMonthStatistic();
                    break;
                case 3:
                    stepTracker.setNewGoal();
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println(" ");
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}