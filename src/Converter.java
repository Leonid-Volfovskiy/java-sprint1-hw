public class Converter {
    private final double stepInKcal = 0.005;
    private final double stepInKm = 0.00075;

    public double convertStepToKm(int monthSumSteps){
        return monthSumSteps * stepInKm;
    }

    public double convertStepToKcal(int monthSumSteps){
        return monthSumSteps * stepInKcal;
    }
}