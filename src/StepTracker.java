import java.util.Scanner;

public class StepTracker {
    private int[][] monthToData;
    private int goalSteps = 10000; //  цель на день
    Scanner scanner;
    private Converter converter = new Converter();

    public StepTracker(Scanner scanner) {
        monthToData = new int [12][30];
        this.scanner = scanner;
    }

    public void saveSteps() {
        for (int i = 0; i < monthToData.length; i++) {
            System.out.println("За какой месяц вы хотите ввести шаги?");
            System.out.println("Введите месяц (формат: 0 - январь ... 11 - декабрь): ");
            int month = this.scanner.nextInt();
            if (month < 0) {
                System.out.println("Ошибка! Ввод несуществующего значения.");
                System.out.println("Выберите месяц повторно (формат: 0 - январь ... 11 - декабрь): ");
                month = this.scanner.nextInt();
            }

            System.out.println("За какой день вы хотите ввести шаги?");
            System.out.println("Введите день (формат: 0 - 1 число ... 29 - 30 число): ");
            int day = this.scanner.nextInt(); // так как мы работаем по массиву, индексы у него начинаются с 0. 0 - это первое чсило.
            if (day < 0) {
                System.out.println("Ошибка. Ввод несуществующего значения. ");
                System.out.println("Выберите день повторно (формат: 0 - 1 число ... 29 - 30 число): ");
                day = this.scanner.nextInt();
            }

            System.out.println("Введите количество пройденных шагов:");
            int steps = this.scanner.nextInt();
            if (steps < 0) {
                System.out.println("Ошибка. Ввод отрицательного значения.");
                System.out.println("Введите данные повторно: ");
                steps = this.scanner.nextInt();
            }
            monthToData[month][day] = steps;
            break;
        }
    }

    public void getMonthStatistic() {
        System.out.println("Введите месяц (формат: 0 - январь ... 11 - декабрь):");
        int month = this.scanner.nextInt();
        int monthSumSteps = 0;
        int maxStepInMonth = 0;
        int averageStepInMonth = 0;
        int maxSeries = 0;
        int counterSteps = 0;
        for (int i = 0; i < monthToData[month].length; i++) {
            monthSumSteps = monthSumSteps + monthToData[month][i]; // считаем шаги за месяц
            if (monthToData[month][i] > maxStepInMonth) { // макс число шагов за день в месяце
                maxStepInMonth = monthToData[month][i];
            }
            averageStepInMonth = monthSumSteps / monthToData[month].length; // среднее число шагов
            if(monthToData[month][i] >= goalSteps) { // лучшая серия шагов за месяц
                counterSteps++;
                if (counterSteps > maxSeries) {
                    maxSeries = counterSteps;
                }
            } else {
                counterSteps = 0;
            }
        }
        System.out.println("________________________");
        System.out.print("Общее количество шагов за месяц: " + monthSumSteps + "; ");
        System.out.println("\n");
        System.out.print("Максимальное пройденное количество шагов в месяце: " + maxStepInMonth + "; ");
        System.out.println("\n");
        System.out.print("Среднее количество шагов за месяц: " + averageStepInMonth + "; ");
        System.out.println("\n");
        System.out.print("Пройденная дистанция (в км): " + Math.round(converter.convertStepToKm(monthSumSteps)) + " км; ");
        System.out.println("\n");
        System.out.print("Количество сожжённых килокалорий: " + Math.round(converter.convertStepToKcal(monthSumSteps)) + " ккал; ");
        System.out.println("\n");
        System.out.print("Лучшая серия: " + maxSeries + " дн.; ");
    }

    public int setNewGoal() { // меняем цель по шагам
        System.out.println("Ваша текущая цель по количеству шагов в день: " + goalSteps + ". ");
        System.out.println("Чтобы изменить Вашу цель, нажмите 1.");
        System.out.println("Если хотите оставить цель без изменений, нажмите 0.");
        int userInput = this.scanner.nextInt();
        if (userInput == 1) {
            System.out.println("Введите вашу новую цель:");
            goalSteps = this.scanner.nextInt();
            if (goalSteps < 0) {
                System.out.println("Ошибка. Ввод отрицательного значения. ");
                System.out.println("Введите вашу новую цель повторно: ");
                goalSteps = this.scanner.nextInt();
            }
            System.out.println("Ваша новая цель по количеству шагов в день: " + goalSteps + ". ");
        } else if (userInput == 0) {
            System.out.println("Ваша текущая цель осталась без измений. ");
        } else {
            System.out.println("Извините, такой команды пока нет.");
        }
        return goalSteps;
    }
}