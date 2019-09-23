package util;

public class DayReportBuffer {

    private static Long earnings;

    private static Long soldCars ;

    private DayReportBuffer(Long earnings, Long soldCars) {
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public static DayReportBuffer getInstance(){
        if (earnings == null || soldCars == null) {
            earnings = 0L;
            soldCars = 0L;
        }
        return new DayReportBuffer(earnings, soldCars);
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public Long getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(Long soldCars) {
        this.soldCars = soldCars;
    }

    public void reset() {
        earnings = 0L;
        soldCars = 0L;
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                ", earnings= " + earnings +
                ", soldCars= " + soldCars +
                " } ";
    }
}
