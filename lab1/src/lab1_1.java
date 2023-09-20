import java.util.Scanner;
public class lab1_1 {
    public static void main(String[] args) {
        System.out.printf("Enter a number:" + "\n");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int counter = 0;
        do{
            counter++;
            if(n % 2 == 0){
                n/=2;
            }
            else n = 3 * n + 1;
        }
        while(n!=1);
        System.out.printf("Number of steps:" + "\n" + counter);
    }
}