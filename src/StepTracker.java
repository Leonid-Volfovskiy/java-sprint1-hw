import java.util.Scanner;

public class StepTracker {
    private int[][] monthToData;
    private int goalSteps = 10000; //  цель на день
    private Scanner scanner;
    private Converter converter = new Converter();

    public StepTracker(Scanner scanner) {
        monthToData = new int [12][30];
        this.scanner = scanner;
    }

    public void saveSteps() {
        System.out.println("За какой месяц вы хотите ввести шаги?");
        System.out.println("Введите месяц (формат: 1 - январь ... 12 - декабрь): ");
        int month = this.scanner.nextInt() - 1;
        if (month < 0) {
            System.out.println("Ошибка! Ввод отрицательного значения.");
        } else {
            if (month > 11) {
                System.out.println("Ошибка! Ввод несуществующего значения.");
            } else {
                System.out.println("За какой день вы хотите ввести шаги?");
                System.out.println("Введите день (формат: 1 - 1 число ... 30 - 30 число): ");
                int day = this.scanner.nextInt() - 1;
                if (day < 0) {
                    System.out.println("Ошибка! Ввод отрицательного значения.");
                } else {
                    if (day > 29) {
                        System.out.println("Ошибка! Ввод несуществующего значения.");
                    } else {
                        System.out.println("Введите количество пройденных шагов:");
                        int steps = this.scanner.nextInt();
                        if (steps < 0) {
                            System.out.println("Ошибка! Ввод отрицательного значения.");
                        } else {
                            monthToData[month][day] = steps;
                        }
                    }
                }
            }
        }
    }

    public void getMonthStatistic() {
        System.out.println("Введите месяц (формат: 1 - январь ... 12 - декабрь):");
        int monthSumSteps = 0;
        int maxStepInMonth = 0;
        int averageStepInMonth = 0;
        int maxSeries = 0;
        int counterSteps = 0;
        int month = this.scanner.nextInt() - 1;
        if (month < 0) {
            System.out.println("Ошибка. Ввод отрицательного значения. ");
        } else {
            if (month > 11) {
                System.out.println("Ошибка! Ввод несуществующего значения.");
            } else {
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
                System.out.println("Общее количество шагов за месяц: " + monthSumSteps + "; ");
                System.out.println("Максимальное пройденное количество шагов в месяце: " + maxStepInMonth + "; ");
                System.out.println("Среднее количество шагов за месяц: " + averageStepInMonth + "; ");
                System.out.println("Пройденная дистанция (в км): " + Math.round(converter.convertStepToKm(monthSumSteps)) + " км; ");
                System.out.println("Количество сожжённых килокалорий: " + Math.round(converter.convertStepToKcal(monthSumSteps)) + " ккал; ");
                System.out.println("Лучшая серия: " + maxSeries + " дн.; ");
            }
        }
    }

    public int setNewGoal() { // меняем цель по шагам
        System.out.println("Ваша текущая цель по количеству шагов в день: " + goalSteps + ". ");
        System.out.println("Чтобы изменить Вашу цель, нажмите 1.");
        System.out.println("Если хотите оставить цель без изменений, нажмите 0.");
        int userInput = this.scanner.nextInt();
        int scan;
        if (userInput == 1) {
            System.out.println("Введите вашу новую цель:");
            scan = this.scanner.nextInt();
            if (scan < 0) {
                System.out.println("Ошибка. Ввод отрицательного значения. ");
            } else {
                goalSteps = scan;
                System.out.println("Ваша новая цель успешно записана: " + goalSteps + " шагов в день. ");
            }
        } else if (userInput == 0) {
            System.out.println("Ваша текущая цель осталась без измений. ");
        } else {
            System.out.println("Извините, такой команды пока нет.");
        }
        return goalSteps;
    }
}