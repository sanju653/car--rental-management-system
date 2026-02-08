
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

class Car {
    private String brand;
    private String model;
    private int id;
    private double pricePerDay;
    private boolean isAvailable;

    Car(String brand, String model, int id, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public int getCarid() {

        return id;
    }

    public String getCarModel() {
        return model;
    }

    public String getCarBrand() {
        return brand;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double totalprice(int days) {
        return pricePerDay * days;
    }

    public void carisOnRent() {
        isAvailable = false;
    }

    public void carReturned() {
        isAvailable = true;
    }

}

class Customer {
    private String name;
    private int id;
    private int age;

    Customer(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getCustomerName() {
        return name;
    }

    public int getCustomerId() {
        return id;
    }

    public int getCustomerAge() {
        return age;
    }
}

class Rental {
    private Car c;
    private Customer cs;
    private int days;

    public Rental(Car c, Customer cs, int days) {
        this.c = c;
        this.cs = cs;
        this.days = days;
    }

    public Car getCarDetails() {
        return c;

    }

    public Customer getCustomerDetails() {
        return cs;
    }

    public int getDays() {
        return days;
    }

}

public class Main {
    static ArrayList<Car> carList = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<>();
    static ArrayList<Rental> rentalList = new ArrayList<>();


    public static void main(String[] args) {
        Car c1 = new Car("Mahindra", "Thar", 1, 123.45);
        Car c2 = new Car("Tata", "Nexon", 2, 100.0);
        Car c3 = new Car(" Maruthi Suzuki", "swift", 3, 98.97);
        Car c4 = new Car("Hyundai", "Creta", 4, 134.3);
        carList.add(c1);
        carList.add(c2);
        carList.add(c3);
        carList.add(c4);
        boolean running = true;
        while (running) {
            Scanner ss = new Scanner(System.in);
            System.out.println("~~~~~~CAR RENTAL SYSTEM~~~~~~");
            System.out.println("1.Display Available Cars");
            System.out.println("2.Rent A Car");
            System.out.println("3.Return A Car");
            System.out.println("4.Exit");
            System.out.println("choose between 1 to 4");

            int choice = ss.nextInt();
            ss.nextLine();

            switch (choice) {
                case 1 -> displayAvailableCars();
                case 2 -> rentACar(ss);
                case 3 -> returnACar(ss);
                case 4 -> {
                    System.out.print("Thank you!");
                    running = false;
                    int i = 5;
                    while (i >= 0) {
                        System.out.print(".");
                        try {
                            Thread.sleep(250);
                            i--;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
                default -> System.out.println("choose between 1 to 4");
            }
        }


    }

    public static void displayAvailableCars() {
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-10s %-10s%n", "Brand", "Model", "ID", "Price/Day");
        System.out.println("-----------------------------------------------------------------");

        for (Car c : carList) {
            if (c.isAvailable()) {
                System.out.printf("%-15s %-15s %-10s $%-10.2f%n",
                        c.getCarBrand(),
                        c.getCarModel(),
                        c.getCarid(),
                        c.getPricePerDay());
            }
        }
        System.out.println("-----------------------------------------------------------------");
    }


    public static void rentACar(Scanner ss) {
        System.out.println("Enter your full name");
        String full_Name = ss.nextLine();
        System.out.println("Enter your age");
        int age = ss.nextInt();
        ss.nextLine();
        Customer cs1 = new Customer(full_Name, customerList.size() + 1, age);
        customerList.add(cs1);
        if (age < 18) {
            System.out.println(full_Name + " " + " You are underage!");
            return;
        } else {


            System.out.println("\n===== SELECT A CAR TO RENT =====");
            System.out.printf("%-10s | %-15s | %-15s%n", "ID", "Brand", "Model");
            System.out.println("---------------------------------------------");

            for (Car c : carList) {
                if (c.isAvailable()) {
                    System.out.printf("%-10s | %-15s | %-15s%n",
                            c.getCarid(),
                            c.getCarBrand(),
                            c.getCarModel());
                }
            }
            System.out.println("---------------------------------------------");
            System.out.print("[?] Enter the Car ID you wish to rent: ");




            int entered_CarId = ss.nextInt();

            ss.nextLine();
            Car selectedCar = null;
            for (Car c : carList) {
                if (entered_CarId == c.getCarid() && c.isAvailable()) {
                    selectedCar = c;
                    break;
                }
            }

            if (selectedCar != null) {
                System.out.println("\n>>> SELECTED CAR DETAILS <<<");
                System.out.println("--------------------------------------------------");
                // Using printf to align Brand, Model, and ID
                System.out.printf("%-12s | %-12s | ID: %s%n",
                        selectedCar.getCarBrand(),
                        selectedCar.getCarModel(),
                        selectedCar.getCarid());
                System.out.println("--------------------------------------------------");

            } else {
                System.err.println("Error: Car is currently not available for rent.");
                return;
            }

// Prompt for input with better visual cues
            System.out.print("\n[?] Enter number of rent days: ");
            int days = ss.nextInt();
            ss.nextLine();

            System.out.println("\n--------------------------------------------------");
            System.out.printf("Total Estimate for %d days: Rs %.2f%n", days, selectedCar.totalprice(days));
            System.out.print("Confirm car rent? (yes/no): ");


            String confirm = ss.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {

                System.out.println("\n========================================");
                System.out.println("          RENTAL INFORMATION          ");
                System.out.println("========================================");

                System.out.printf("%-15s : %s%n", "Full Name", cs1.getCustomerName());
                System.out.printf("%-15s : %d%n", "Age", cs1.getCustomerAge());
                System.out.printf("%-15s : %s%n", "Customer ID", cs1.getCustomerId());
                System.out.printf("%-15s : %d days%n", "Rent Duration", days);

                System.out.println("----------------------------------------");
                System.out.printf("%-15s : Rs %.2f%n", "Total Charges", selectedCar.totalprice(days));
                System.out.println("----------------------------------------");

                System.out.println("   >>> Car rented successfully! <<<   ");
                System.out.println("========================================\n");


                Rental r = new Rental(selectedCar, cs1, days);
                rentalList.add(r);
                selectedCar.carisOnRent();
            } else {
                System.out.println("Rental cancelled!");
            }

        }

    }

    public static void returnACar(Scanner ss) {

        System.out.print("\n[?] Enter Car ID to return: ");
        int entered_Id = ss.nextInt();
        ss.nextLine();

        Rental toReturnCar = null;
        for (Rental r : rentalList) {
            if (r.getCarDetails().getCarid() == entered_Id) {
                toReturnCar = r;
                break;
            }
        }

        System.out.println("\n-------------------------------------------");
        if (toReturnCar != null) {
            // Logic to update lists and car status
            rentalList.remove(toReturnCar);
            toReturnCar.getCarDetails().carReturned();

            // Formatted Success Message
            System.out.println(">>> RETURN CONFIRMED <<<");
            System.out.printf("%-15s : %s%n", "Car", toReturnCar.getCarDetails().getCarBrand() + " " + toReturnCar.getCarDetails().getCarModel());
            System.out.printf("%-15s : %s%n", "Customer", toReturnCar.getCustomerDetails().getCustomerName());
            System.out.println("-------------------------------------------");
            System.out.println("Status: Car is now available for rent.");
        } else {
            // Formatted Error Message
            System.err.println("ERROR: No active rental found for ID [" + entered_Id + "]");
            System.out.println("Please check the ID and try again.");
        }
        System.out.println("-------------------------------------------\n");




    }


    }




