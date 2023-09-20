import java.util.Scanner;
public class lab1_5 {
    public static void main(String[] args) {
        System.out.printf("Enter a three-digit number:" + "\n");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int n1,n2,n3;
        n1 = n / 100;
        n2 = n / 10 % 10;
        n3 = n % 10;
        System.out.println(n1+ "\n" + n2 + "\n" + n3);
        int sum = n1 + n2 + n3;
        int multiply = n1 * n2 * n3;
        if((sum % 2 == 0) && (multiply % 2 == 0)){
            System.out.println("Number is double-even");
        } else {
            System.out.println("Number is not double-even");
        }
    }
}