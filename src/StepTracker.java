import java.util.Scanner;

public class StepTracker<userInput> {
    int[][] monthToData;
    int goalSteps = 10000; //  цель на день.
    Scanner scanner = new Scanner(System.in);
    Converter converter = new Converter();


    StepTracker() {
        monthToData = new int [12][30];
    }

    void saveSteps() {
        for (int i = 0; i < monthToData.length; i++) {
            System.out.println("За какой месяц вы хотите ввести шаги? Введите от 0 до 11, где 0-ЯНВ, а 11-ДЕК");
            int month = scanner.nextInt();
            System.out.println("За какой день вы хотите ввести шаги? Введите от 0 до 29, где 0-первый день, а 29-тридцатый день");
            int day = scanner.nextInt();
            System.out.println("Введите количество пройденных шагов:");
            int steps = scanner.nextInt();
            monthToData[month][day] = steps;
            break;
        }
    }

    void getMonthStatistic() {
        System.out.println("За какой месяц вы хотите получить статистику? Введите от 0 до 11, где 0-ЯНВ, а 11-ДЕК");
        int month = scanner.nextInt();
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

    int setNewGoal() { // меняем цель по шагам
        System.out.println("Ваша текущая цель по количеству шагов в день: " + goalSteps + ". ");
        System.out.println("Чтобы изменить Вашу цель, нажмите 1. Если хотите оставить без изменений, нажмите 0.");
        int userInput = scanner.nextInt();
        if (userInput == 1) {
            System.out.println("Введите вашу новую цель:");
            goalSteps = scanner.nextInt();
            System.out.println("Ваша новая цель по количеству шагов в день: " + goalSteps + ". ");
        } else if (userInput == 0) {
            System.out.println("Ваша текущая цель осталась без измений. ");
        } else {
            System.out.println("Извините, такой команды пока нет.");
        }
        return goalSteps;
    }
}