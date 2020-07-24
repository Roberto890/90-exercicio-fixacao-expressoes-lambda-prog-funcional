package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
    
    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);
        ///projects/90-exercicio-fixacao-expressoes-lambda-prog-funcional/in.csv (diretorio pelo site)
        List<Employee> employee = new ArrayList<>();

        System.out.print("Digite o caminho do arquivo: ");
        String path = sc.nextLine();

        System.out.print("Digite o salario:");
        double salary = sc.nextDouble();

        char initialLetter = 'M';
     
        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                employee.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            
            //filtrando os email de acordo com o salario
            List<String> emails = employee.stream()
                                          .filter(e -> e.getSalary() > salary)
                                          .map(e -> e.getEmail())
                                          .sorted()
                                          .collect(Collectors.toList());
            
            //somando os salarios de acordo com a inicial do funcionario
            Double salarySum = employee.stream()
                                          .filter(e -> e.getName().charAt(0) == initialLetter)
                                          .map(e -> e.getSalary())
                                          .reduce(0.0, (x,y) -> x + y);
 
            //printando os resultados
            System.out.printf("Email dos funcionarios que tem salario maior que %.2f:" , salary);
            System.out.println();
            emails.forEach(System.out::println);

            System.out.printf("Soma do salario das pessoas que tem a inicial do nome em %s: '%.2f'" ,initialLetter, salarySum);

        } catch(IOException e ){
            System.out.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }

}