public class TaxiBill {
    private double km;

    public TaxiBill(double km) {
        this.km = km;
    }

    public double calculateCost() {
        return km * 12000;
    }
}