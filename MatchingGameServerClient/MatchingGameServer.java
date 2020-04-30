import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class MatchingGameServer {

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(16789)) {
            System.out.println("Matching Game Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(listener.accept(), "PlayerOne"));
                pool.execute(game.new Player(listener.accept(), "PlayerTwo"));
            }
        }
    }
}

class Game {

    // Board cells numbered 0-8, top to bottom, left to right; null if empty
    private Player[] userinterface = new Player[12];

    Player currentPlayer;

//     public boolean hasWinner() {
//         //what makes them the winner goes here
//     }

    public synchronized void move(int location, Player player) {
        if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        }
    }

    class Player implements Runnable {
        String player;
        Player opponent;
        Socket socket;
        Scanner input;
        PrintWriter output;

        public Player(Socket socket, String player) {
            this.socket = socket;
            this.player = player;
        }

        @Override
        public void run() {
            try {
                setup();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (opponent != null && opponent.output != null) {
                    opponent.output.println("OTHER_PLAYER_LEFT");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + player);
            if (player == "PlayerOne") {
                currentPlayer = this;
                output.println("MESSAGE Waiting for opponent to connect");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
                // opponent.output.println("MESSAGE Your move");
            }
        }
    }
}