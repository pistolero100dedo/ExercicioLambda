package Application;

import Entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employee> employeeList = new ArrayList<>();

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                employeeList.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            System.out.print("Wich value to filter the employees salary? ");
            double salaryFilter = sc.nextDouble();

            List<String> salaryFilteredOrder = employeeList.stream()
                    .filter(e -> e.getSalary() >= salaryFilter)
                    .map(e -> e.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            System.out.printf("E-mail of people whose salary is more than $%.2f\n", salaryFilter);
            salaryFilteredOrder.forEach(System.out::println);

            double sum = employeeList.stream()
                    .filter(e -> e.getName().charAt(0) == 'M')
                    .map(e -> e.getSalary())
                    .reduce(0.0, (x, y) -> x + y);


            System.out.println();
            System.out.printf("Sum of salary of people whose starts with 'M' = $%.2f", sum);

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();

    }

}
