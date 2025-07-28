import java.util.Scanner;

public class Main {
    private static ExpenseManager expenseManager = new ExpenseManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    calculateElectricBill();
                    break;
                case "2":
                    calculateTaxiBill();
                    break;
                case "3":
                    calculateSalary();
                    break;
                case "4":
                    displayTotalIncome();
                    break;
                default:
                    System.out.println("\n===== THANKS FOR USING OUR CONSOLE APPLICATION! =====\n\n");
                    scanner.close();
                    return;
            }
        }
    }

    private static double getPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please no negative number!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter some valid (not the word or special character)!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=====  PERSONAL SPENDING MANAGEMENT =====\n\n");
        System.out.println("1. Calculate electricity at the end of the month");
        System.out.println("2. Calculating taxi at the end of the month");
        System.out.println("3. Calculate income the end of the month (tax = 15%)");
        System.out.println("4. Calculate the total income after spending at the end of the month");
        System.out.print("\n\nPlease select the function [1-> 4] or press another key to exit: ");
    }

    private static void calculateElectricBill() {
        double kwh = getPositiveDouble(" Enter the use number (KWh): ");
        ElectricBill electricBill = new ElectricBill(kwh);
        double cost = electricBill.calculateCost();
        expenseManager.addExpense(cost);
        System.out.println(" Electricity at the end of the month: " + String.format("%,.0f", cost) + " VND");
    }

    private static void calculateTaxiBill() {
        double km = getPositiveDouble("Enter the number of kilometers: ");
        TaxiBill taxiBill = new TaxiBill(km);
        double cost = taxiBill.calculateCost();
        expenseManager.addExpense(cost);
        System.out.println("Taxi at the end of the month: " + String.format("%,.0f", cost) + " VND");
    }

    private static void calculateSalary() {
        double grossSalary = getPositiveDouble("Enter income before tax (VND): ");
        System.out.println("Income befor tax: " + String.format("%,0f", grossSalary) + "VND");
        Salary salary = new Salary(grossSalary);
        double netSalary = salary.calculateNetSalary();
        expenseManager.setIncome(netSalary);
        System.out.println("Income after tax (15%): " + String.format("%,.0f", netSalary) + " VND");
    }

    private static void displayTotalIncome() {
        double total = expenseManager.calculateTotalIncome();
        System.out.println("Total income after expense: " + String.format("%,.0f", total) + " VND");
    }
}
