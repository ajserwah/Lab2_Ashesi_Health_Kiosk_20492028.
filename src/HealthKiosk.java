import java.util.Scanner;
import java.util.Random;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        // Welcome message
        System.out.println("Welcome to Ashesi Health Center Self-Service Kiosk!\n");

        // TASK 1: Service Router
        System.out.print("Enter service code (P/L/T/C): ");
        char serviceCode = Character.toUpperCase(scanner.next().charAt(0));

        String serviceName;
        switch (serviceCode) {
            case 'P':
                serviceName = "PHARMACY";
                System.out.println("Go to: Pharmacy Desk");
                break;
            case 'L':
                serviceName = "LAB";
                System.out.println("Go to: Lab Desk");
                break;
            case 'T':
                serviceName = "TRIAGE";
                System.out.println("Go to: Triage Desk");
                break;
            case 'C':
                serviceName = "COUNSELING";
                System.out.println("Go to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
                scanner.close();
                return;
        }
        System.out.println();

        // TASK 2: Mini Health Metric (only for Triage)
        double metricValue = 0.0;
        String metricDisplay = "";
        int metricChoice = 0;

        if (serviceCode == 'T') {
            System.out.println("Health Metric Options:");
            System.out.println("1 - BMI quick calc");
            System.out.println("2 - Dosage round-up");
            System.out.println("3 - Simple trig helper");
            System.out.print("Enter your choice (1/2/3): ");
            metricChoice = scanner.nextInt();

            if (metricChoice == 1) {
                // BMI calculation
                System.out.print("Enter weight(kg): ");
                double weight = scanner.nextDouble();
                System.out.print("Enter height(m): ");
                double height = scanner.nextDouble();

                double bmi = Math.round(weight / Math.pow(height, 2) * 10) / 10.0;
                metricValue = bmi;

                String category = bmi < 18.5 ? "Underweight" :
                        bmi <= 24.9 ? "Normal" :
                                bmi <= 29.9 ? "Overweight" : "Obese";

                System.out.println("BMI: " + bmi + " Category: " + category);
                metricDisplay = "BMI=" + bmi;

            } else if (metricChoice == 2) {
                // Dosage calculation
                System.out.print("Enter required dosage (mg): ");
                int tablets = (int) Math.ceil(scanner.nextDouble() / 250.0);
                metricValue = tablets;

                System.out.println("Tablets needed: " + tablets);
                metricDisplay = "Tablets=" + tablets;

            } else if (metricChoice == 3) {
                // Trigonometry helper
                System.out.print("Enter angle in degrees: ");
                double degrees = scanner.nextDouble();
                double radians = Math.toRadians(degrees);

                double sinValue = Math.round(Math.sin(radians) * 1000) / 1000.0;
                double cosValue = Math.round(Math.cos(radians) * 1000) / 1000.0;
                metricValue = sinValue;

                System.out.println("sin(" + degrees + "°) = " + sinValue);
                System.out.println("cos(" + degrees + "°) = " + cosValue);
                metricDisplay = "Sin=" + sinValue;
            }
            System.out.println();
        }

        // TASK 3: ID Generation and Validation
        // Generate random character and digits
        char randomLetter = (char)('A' + random.nextInt(26));
        int d1 = random.nextInt(7) + 3;
        int d2 = random.nextInt(7) + 3;
        int d3 = random.nextInt(7) + 3;
        int d4 = random.nextInt(7) + 3;

        // Concatenate to form ID
        String shortID = "" + randomLetter + d1 + d2 + d3 + d4;

        // Validate the ID format
        if (shortID.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(shortID.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
        } else if (!Character.isDigit(shortID.charAt(1)) ||
                !Character.isDigit(shortID.charAt(2)) ||
                !Character.isDigit(shortID.charAt(3)) ||
                !Character.isDigit(shortID.charAt(4))) {
            System.out.println("Invalid: last 4 must be digits");
        } else {
            System.out.println("ID OK");
        }

        System.out.println("Generated ID: " + shortID + "\n");

        // TASK 4: "Secure" Display Code
        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        char baseCode = Character.toUpperCase(firstName.charAt(0));
        char shiftedLetter = (char)('A' + (baseCode - 'A' + 2) % 26);
        String lastTwo = shortID.substring(3);

        System.out.println("Base code = " + baseCode);
        System.out.println("Shifted letter of base code = " + shiftedLetter);
        System.out.println("Last two characters for ID (task 3): " + lastTwo);

        // Calculate metric integer based on choice
        int metricInt = serviceCode == 'T' ?
                (metricChoice == 1 ? (int) Math.round(metricValue) :
                        metricChoice == 2 ? (int) metricValue :
                                (int) Math.round(metricValue * 100)) : 0;

        String displayCode = shiftedLetter + lastTwo + "-" + metricInt;
        System.out.println("Display Code: " + displayCode + "\n");

        // TASK 5: Service Summary
        System.out.print("Summary: " + serviceName + " | ID=" + shortID);
        if (serviceCode == 'T' && !metricDisplay.isEmpty()) {
            System.out.print(" | " + metricDisplay);
        }
        System.out.println(" | Code=" + displayCode);

        scanner.close();
    }
}