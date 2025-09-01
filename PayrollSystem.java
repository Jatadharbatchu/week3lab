class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    private static int empCounter = 0;

    public Employee(String empName, String department, double baseSalary) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "FullTime";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public Employee(String empName, String department, double hourlyRate, int hoursWorked) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "PartTime";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public Employee(String empName, String department, double fixedAmount, String contract) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = fixedAmount;
        this.empType = "Contract";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public double calculateSalary() {
        if (empType.equals("FullTime")) return baseSalary + 500;  
        if (empType.equals("PartTime")) return baseSalary;          
        return baseSalary;                                         
    }

    public double calculateTax() {
        if (empType.equals("FullTime")) return calculateSalary() * 0.1;
        if (empType.equals("PartTime")) return calculateSalary() * 0.05;
        return calculateSalary() * 0.08;
    }

    public void generatePaySlip() {
        System.out.println("=================================");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Name          : " + empName);
        System.out.println("Department    : " + department);
        System.out.println("Employee Type : " + empType);
        System.out.println("Salary        : " + calculateSalary());
        System.out.println("Tax           : " + calculateTax());
        System.out.println("=================================");
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + empId + ", Name: " + empName + ", Type: " + empType);
    }

    public static String generateEmpId() {
        empCounter++;
        return String.format("E%03d", empCounter);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Alice", "IT", 50000);
        employees[1] = new Employee("Bob", "HR", 200, 80);
        employees[2] = new Employee("Charlie", "Finance", 30000, "Contract");

        for (Employee e : employees) {
            e.generatePaySlip();
        }

        System.out.println("Total Employees: " + Employee.getTotalEmployees());
    }
}
