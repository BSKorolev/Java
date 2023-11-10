import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Board board = new Board();
        board.setColorGaming('w');
        board.init();

        boolean game = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (game) {
            board.print_board();
            System.out.println();
            System.out.println("Commands: ");
            System.out.println("----- exit: ");
            System.out.println("------x1 y1 x2 y2: Move figure from x1, y1 to x2, y2");


            System.out.println("Taken White:"+board.getTakeWhite().toString());
            System.out.println("Taken Black:"+board.getTakeBlack().toString());

            switch (board.getColorGaming()){
                case 'w': System.out.println("White to move:");break;
                case 'b': System.out.println("Black to move:");break;
            }

            String inputLine = reader.readLine();
            if (inputLine.equals("exit")){
                System.out.println("The game is over.");
                reader.close();
                break;
            }
            int x1, y1, x2, y2;
            String[] coords = inputLine.split(" ");
            x1 = coords[0].charAt(0) - 'a';
            y1 = coords[1].charAt(0) - '1';
            x2 = coords[2].charAt(0) - 'a';
            y2 = coords[3].charAt(0) - '1';

            while (!board.move_figure(y1,x1, y2,x2)){
                System.out.println("Wrong move, re-enter your move!");
                inputLine = reader.readLine();
                coords = inputLine.split(" ");
                x1 = coords[0].charAt(0) - 'a';
                y1 = coords[1].charAt(0) - '1';
                x2 = coords[2].charAt(0) - 'a';
                y2 = coords[3].charAt(0) - '1';
            };

            switch (board.getColorGaming()){
                case 'w': board.setColorGaming('b'); break;
                case 'b': board.setColorGaming('w'); break;
            }
        }
    }
}