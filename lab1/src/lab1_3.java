import java.util.Scanner;
public class lab1_3 {
    public static void main(String[] args) {
        System.out.printf("Enter the coordinates:" + "\n");
        Scanner console = new Scanner(System.in);
        int x = 0;
        int y = 0;
        int treasure_x = console.nextInt();
        int treasure_y = console.nextInt();
        boolean notfound = true;
        int step;
        int counter = 0;
        String str = "";
        while (true) {
            str = console.next();
            switch (str) {
                case "Stop":
                    notfound = false;
                    break;
                case "North":
                    step = console.nextInt();
                    y += step;
                    if (notfound) counter++;
                    if (x == treasure_x && y == treasure_y) notfound = false;
                    continue;
                case "West":
                    step = console.nextInt();
                    x -= step;
                    if (notfound) counter++;
                    if (x == treasure_x && y == treasure_y) notfound = false;
                    continue;
                case "East":
                    step = console.nextInt();
                    x += step;
                    if (notfound) counter++;
                    if (x == treasure_x && y == treasure_y) notfound = false;
                    continue;
                case "South":
                    step = console.nextInt();
                    y -= step;
                    if (notfound) counter++;
                    if (x == treasure_x && y == treasure_y) notfound = false;
                    continue;
            }
            System.out.printf("Steps to treasure " + counter);
        }
    }
}