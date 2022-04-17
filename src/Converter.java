public class Converter {
    private final double STEP_IN_KM = 0.00075;
    private final double STEP_IN_CAL = 0.05;

    public double convertStepToKm(int monthSumSteps){
        return monthSumSteps * STEP_IN_KM;
    }
    public double convertStepToKcal(int monthSumSteps){
        return monthSumSteps * STEP_IN_CAL;
    }
}