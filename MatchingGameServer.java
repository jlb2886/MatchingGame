import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class MatchingGameServer {

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> randomString = new ArrayList<Integer>();
        ArrayList pws = new ArrayList<PrintWriter>();
        ExecutorService pool;
        String random = "";
        for(int j = 0; j <16; j++) {
           randomString.add(j,-1);
        }
        for(int i=0; i<8; i++) {
           int card = ThreadLocalRandom.current().nextInt(0, 9);
           if (randomString.contains(card) == true) {
              System.out.println("CARD " + card + " already exists in the random string");
              while(randomString.contains(card) == true) {
                 card = ThreadLocalRandom.current().nextInt(0, 9);
              }
           } else {}
           System.out.println("CARD: " + card);
           int pos = ThreadLocalRandom.current().nextInt(0, 16);
           System.out.println("Position 1 of card: " + pos);
           int pos2 = ThreadLocalRandom.current().nextInt(0, 16);
           System.out.println("Position 2 of card: " + pos2);
           randomString.add(pos, card);
           randomString.add(pos2, card);
         }
         for(int temp : randomString) {
            if(temp != -1 ) {
               random = random + temp;
            } else {}
         }
           String loc = "LOC" + random;    
        try (ServerSocket listener = new ServerSocket(16788)) {
            System.out.println("Matching Game Server is Running...");
            pool = Executors.newFixedThreadPool(200);
            while (true) {
               Game game = new Game();
                pool.execute(game.new Player(listener.accept(), "PlayerOne", loc, pws));
                pool.execute(game.new Player(listener.accept(), "PlayerTwo", loc, pws));
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
        String order;
        ArrayList<PrintWriter> outs;

        public Player(Socket socket, String player, String randomized, ArrayList _outs) {
            this.socket = socket;
            this.player = player;
            this.order = randomized;
            this.outs = _outs;
        }

        @Override
        public void run() {
            try {
                setup();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               while (true) {
                  String msg = input.nextLine();
                  for (int i = 0; i < outs.size(); i++) {
                     outs.get(i).println(msg);
                     outs.get(i).flush();
                  }
                  if (opponent != null && opponent.output != null) {
                    opponent.output.println("OTHER_PLAYER_LEFT");
                    break;
                  }
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
            outs.add(output);
            output.println("WELCOME " + player);
            output.println(order);
            if (player == "PlayerOne") {
                currentPlayer = this;
                output.println("MESSAGE Waiting for opponent to connect");
                System.out.println("Sent");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
                // opponent.output.println("MESSAGE Your move");
            }
        }
    }
}