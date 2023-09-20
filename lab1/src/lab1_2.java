import java.util.Scanner;
public class lab1_2 {
    public static void main(String[] args) {
        System.out.printf("Enter a number:" + "\n");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int amount = 0;
        for(int i=0; i < n; i++){
            int num=console.nextInt();
            if (i % 2 == 0)
            {
                amount += num;
            }
            else
            {
                amount -= num;
            }
        }
        System.out.printf("Amount = " + amount);
    }
}