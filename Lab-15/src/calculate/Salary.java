package calculate;
public class Salary {
    private double grossSalary;
    private static final double TAX_RATE = 0.15;

    public Salary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double calculateNetSalary() {
        return grossSalary * (1 - TAX_RATE);
    }
}
