import java.util.Scanner;
public class lab1_4 {
    public static void main(String[] args) {
        System.out.printf("Enter number of roads:" + "\n");
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int maxRoad = 0;
        int maxHeight = 0;
        for (int i = 1; i <= n; i++) {
            System.out.printf("Enter number of tunnels:" + "\n");
            int numberOfTunnels = console.nextInt();
            int currentHeight = Integer.MAX_VALUE;
            for (int j = 1; j <= numberOfTunnels; j++) {
                System.out.printf("Enter height of tunnel " + j + "\n");
                int tunnelHeight = console.nextInt();
                if (tunnelHeight < currentHeight) {
                    currentHeight = tunnelHeight;
                }
            }
            if (currentHeight > maxHeight) {
                maxRoad = i;
                maxHeight = currentHeight;
            }
        }
        System.out.printf("Number of road:" + maxRoad + "\n" + "Max height:" + maxHeight);
    }
}