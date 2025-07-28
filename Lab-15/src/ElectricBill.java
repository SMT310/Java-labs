public class ElectricBill {
    private double kwh;

    public ElectricBill(double kwh) {
        this.kwh = kwh;
    }

    public double calculateCost() {
        return kwh * 2000;
    }
}
