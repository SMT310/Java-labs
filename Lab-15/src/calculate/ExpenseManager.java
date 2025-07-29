package calculate;
public class ExpenseManager {
    private double totalIncome;
    private double totalExpense;

    public ExpenseManager() {
        this.totalIncome = 0;
        this.totalExpense = 0;
    }

    public void addExpense(double expense) {
        this.totalExpense += expense;
    }

    public void setIncome(double income) {
        this.totalIncome = income;
    }

    public double calculateTotalIncome() {
        return totalIncome - totalExpense;
    }
}
