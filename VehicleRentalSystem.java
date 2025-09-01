class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName;
    private static int rentalDays = 0;
    private static int vehicleCounter = 0;

    public Vehicle(String brand, String model, double rentPerDay) {
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.vehicleId = generateVehicleId();
        totalVehicles++;
    }

    public double rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle not available: " + vehicleId);
            return 0;
        }
        double amount = calculateRent(days);
        isAvailable = false;
        totalRevenue += amount;
        rentalDays += days;
        System.out.println("Vehicle rented: " + vehicleId + " for " + days + " days.");
        return amount;
    }

    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Vehicle returned: " + vehicleId);
        }
    }

    public double calculateRent(int days) {
        return rentPerDay * days;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID : " + vehicleId);
        System.out.println("Brand      : " + brand);
        System.out.println("Model      : " + model);
        System.out.println("Rent/Day   : " + rentPerDay);
        System.out.println("Available  : " + isAvailable);
        System.out.println("-----------------------------");
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        return rentalDays == 0 ? 0 : totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue : " + totalRevenue);
        System.out.println("Average Rent/Day: " + getAverageRentPerDay());
    }

    private static String generateVehicleId() {
        vehicleCounter++;
        return String.format("V%03d", vehicleCounter);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("Fast Rentals");
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Vehicle("Toyota", "Corolla", 1000);
        vehicles[1] = new Vehicle("Honda", "City", 1200);
        vehicles[2] = new Vehicle("Ford", "Fiesta", 900);

        vehicles[0].rentVehicle(5);
        vehicles[1].rentVehicle(3);
        vehicles[0].returnVehicle();
        vehicles[2].rentVehicle(7);

        for (Vehicle v : vehicles) v.displayVehicleInfo();

        Vehicle.displayCompanyStats();
    }
}
