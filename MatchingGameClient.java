import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.*;
import java.util.*;
import java.net.*; //All network classes
import java.io.*; //All read/write operations
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * MatchingGame Client Class will create the GUI on the client side
 * This class will also allow for a client to message with another client
 * ISTE-121 Project
 * @authors Nathaniel Pellegrino Robert Heine Fenix Setiawan Vincent Valli Julianna Baker
 * @version 05-01-20
 */

public class MatchingGameClient implements ActionListener{
   
   //Variables
   private JTextField jtfChat;
   private JTextField jtfUser;
   private JLabel jlUser;
   private JButton jbChat;
   private JButton jbCard1;
   private JButton jbCard2;
   private JButton jbCard3;
   private JButton jbCard4;
   private JButton jbCard5;
   private JButton jbCard6;
   private JButton jbCard7;
   private JButton jbCard8;
   private JButton jbCard9;
   private JButton jbCard10;
   private JButton jbCard11;
   private JButton jbCard12;
   private JButton jbCard13;
   private JButton jbCard14;
   private JButton jbCard15;
   private JButton jbCard16;
   private Icon back = new ImageIcon("BK.png");
   private Icon c2 = new ImageIcon("C2.png");
   private Icon c6 = new ImageIcon("C6.png");
   private Icon cJ = new ImageIcon("CJ.png");
   private Icon d10 = new ImageIcon("D10.png");
   private Icon d5 = new ImageIcon("D5.png");
   private Icon d9 = new ImageIcon("D9.png");
   private Icon dQ = new ImageIcon("DQ.png");
   private Icon h4 = new ImageIcon("H4.png");
   private Icon h8 = new ImageIcon("H8.png");
   private Icon hK = new ImageIcon("HK.png");
   private Icon s3 = new ImageIcon("s3.png");
   private Icon sA = new ImageIcon("SA.png");
   private Icon[] cards = new Icon[] {c2, c6, cJ, d10, d5, d9, dQ, h4, h8, hK, s3, sA};
   private int[] loc =  {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   private JLabel jlCard1;
   private JLabel jlCard2;
   private JLabel jlCard3;
   private JLabel jlCard4;
   private JLabel jlCard5;
   private JLabel jlCard6;
   private JLabel jlCard7;
   private JLabel jlCard8;
   private JLabel jlCard9;
   private JLabel jlCard10;
   private JLabel jlCard11;
   private JLabel jlCard12;
   private JLabel jlCard13;
   private JLabel jlCard14;
   private JLabel jlCard15;
   private JLabel jlCard16;
   private JTextArea jtaChat;
   ArrayList<Integer> matched = new ArrayList<Integer>();
   private int selCard1;
   private int selCard2;
   
   //attributes for server & client connection
   private Socket socket;
   private PrintWriter out;
   private BufferedReader br;
   private Scanner in; //scanner
   
   /**
    * The constructor MatchingGameClient will create the GUI.
    */
   public MatchingGameClient() {
        
         selCard1 = -1;
         selCard2 = -1;
         //Chat
         JFrame jfClient = new JFrame("Matching Game Client");
         JPanel jpClient = new JPanel(new BorderLayout());
         jtaChat = new JTextArea(20,20);
         jtfChat = new JTextField(20);
         jtfUser = new JTextField(10);
         jlUser = new JLabel("Username: ");
         jbChat = new JButton("Send");
         
         jbCard1 = new JButton("1 ^^^"); //MOVE TO private^^
         jlCard1 = new JLabel();
         jlCard1.setIcon(back);
         //Card 2
         jbCard2 = new JButton("2 ^^^");
         jlCard2 = new JLabel();
         jlCard2.setIcon(back);
         //Card 3
         jbCard3 = new JButton("3 ^^^");
         jlCard3 = new JLabel();
         jlCard3.setIcon(back);
         //Card 4
         jbCard4 = new JButton("4 ^^^");
         jlCard4 = new JLabel();
         jlCard4.setIcon(back);
         //Card 5
         jbCard5 = new JButton("5 ^^^");
         jlCard5 = new JLabel();
         jlCard5.setIcon(back);
         //Card 6
         jbCard6 = new JButton("6 ^^^");
         jlCard6 = new JLabel();
         jlCard6.setIcon(back);
         //Card 7
         jbCard7 = new JButton("7 ^^^");
         jlCard7 = new JLabel();
         jlCard7.setIcon(back);
         //Card 8
         jbCard8 = new JButton("8 ^^^");
         jlCard8 = new JLabel();
         jlCard8.setIcon(back);
         //Card 9
         jbCard9 = new JButton("9 ^^^");
         jlCard9 = new JLabel();
         jlCard9.setIcon(back);
         //Card 10
         jbCard10 = new JButton("10 ^^^");
         jlCard10 = new JLabel();
         jlCard10.setIcon(back);
         //Card 11
         jbCard11 = new JButton("11 ^^^");
         jlCard11 = new JLabel();
         jlCard11.setIcon(back);
         //Card 12
         jbCard12 = new JButton("12 ^^^");
         jlCard12 = new JLabel();
         jlCard12.setIcon(back);
         //Card 13
         jbCard13 = new JButton("13 ^^^");
         jlCard13 = new JLabel();
         jlCard13.setIcon(back);
         //Card 14
         jbCard14 = new JButton("14 ^^^");
         jlCard14 = new JLabel();
         jlCard14.setIcon(back);
         //Card 15
         jbCard15 = new JButton("15 ^^^");
         jlCard15 = new JLabel();
         jlCard15.setIcon(back);
         //Card 16
         jbCard16 = new JButton("16 ^^^");
         jlCard16 = new JLabel();
         jlCard16.setIcon(back);
         
         //Creating GridLayouts
         JPanel jpChat = new JPanel(new GridLayout(2,1));
         JPanel jpChatSend = new JPanel(new GridLayout(3,1));
         JPanel jpGames = new JPanel(new GridLayout(4,4));
         JPanel jpCard1 = new JPanel(new GridLayout(2,1));
         JPanel jpCard2 = new JPanel(new GridLayout(2,1));
         JPanel jpCard3 = new JPanel(new GridLayout(2,1));
         JPanel jpCard4 = new JPanel(new GridLayout(2,1));
         JPanel jpCard5 = new JPanel(new GridLayout(2,1));
         JPanel jpCard6 = new JPanel(new GridLayout(2,1));
         JPanel jpCard7 = new JPanel(new GridLayout(2,1));
         JPanel jpCard8 = new JPanel(new GridLayout(2,1));
         JPanel jpCard9 = new JPanel(new GridLayout(2,1));
         JPanel jpCard10 = new JPanel(new GridLayout(2,1));
         JPanel jpCard11 = new JPanel(new GridLayout(2,1));
         JPanel jpCard12 = new JPanel(new GridLayout(2,1));
         JPanel jpCard13 = new JPanel(new GridLayout(2,1));
         JPanel jpCard14 = new JPanel(new GridLayout(2,1));
         JPanel jpCard15 = new JPanel(new GridLayout(2,1));
         JPanel jpCard16 = new JPanel(new GridLayout(2,1));
         JPanel jpUser = new JPanel(new GridLayout(1,2));
         
         //Adding to the panel
         jpChat.add(jtaChat);
         jpUser.add(jlUser);
         jpUser.add(jtfUser);
         jpChatSend.add(jpUser);
         jpChatSend.add(jtfChat);
         jpChatSend.add(jbChat);
         jpCard1.add(jlCard1);
         jpCard1.add(jbCard1);
         jpCard2.add(jlCard2);
         jpCard2.add(jbCard2);
         jpCard3.add(jlCard3);
         jpCard3.add(jbCard3);
         jpCard4.add(jlCard4);
         jpCard4.add(jbCard4);
         jpCard5.add(jlCard5);
         jpCard5.add(jbCard5);
         jpCard6.add(jlCard6);
         jpCard6.add(jbCard6);
         jpCard7.add(jlCard7);
         jpCard7.add(jbCard7);
         jpCard8.add(jlCard8);
         jpCard8.add(jbCard8);
         jpCard9.add(jlCard9);
         jpCard9.add(jbCard9);
         jpCard10.add(jlCard10);
         jpCard10.add(jbCard10);
         jpCard11.add(jlCard11);
         jpCard11.add(jbCard11);
         jpCard12.add(jlCard12);
         jpCard12.add(jbCard12);
         jpCard13.add(jlCard13);
         jpCard13.add(jbCard13);
         jpCard14.add(jlCard14);
         jpCard14.add(jbCard14);
         jpCard15.add(jlCard15);
         jpCard15.add(jbCard15);
         jpCard16.add(jlCard16);
         jpCard16.add(jbCard16);
         jpGames.add(jpCard1);
         jpGames.add(jpCard2);
         jpGames.add(jpCard3);
         jpGames.add(jpCard4);
         jpGames.add(jpCard5);
         jpGames.add(jpCard6);
         jpGames.add(jpCard7);
         jpGames.add(jpCard8);
         jpGames.add(jpCard9);
         jpGames.add(jpCard10);
         jpGames.add(jpCard11);
         jpGames.add(jpCard12);
         jpGames.add(jpCard13);
         jpGames.add(jpCard14);
         jpGames.add(jpCard15);
         jpGames.add(jpCard16);
         jpChat.add(jpChatSend);
         jpClient.add(jpChat, BorderLayout.WEST);
         jpClient.add(jpGames, BorderLayout.EAST);
         jfClient.add(jpClient);
         
         //setting visibilities and exit on close
         //jfClient.setDefaultCloseOperation(EXIT_ON_CLOSE);
         jfClient.setSize(600, 600);
         jfClient.setVisible(true);
         jpClient.setVisible(true);
         jpChatSend.setVisible(true);
         jpGames.setVisible(true);
         jfClient.setLocationRelativeTo(null);
         jfClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         //Adding JButtons to Action Listener
         jbChat.addActionListener(this);
         jbCard1.addActionListener(this);
         jbCard2.addActionListener(this);
         jbCard3.addActionListener(this);
         jbCard4.addActionListener(this);
         jbCard5.addActionListener(this);
         jbCard6.addActionListener(this);
         jbCard7.addActionListener(this);
         jbCard8.addActionListener(this);
         jbCard9.addActionListener(this);
         jbCard10.addActionListener(this);
         jbCard11.addActionListener(this);
         jbCard12.addActionListener(this);
         jbCard13.addActionListener(this);
         jbCard14.addActionListener(this);
         jbCard15.addActionListener(this);
         jbCard16.addActionListener(this);
         
    } //constructor
    
    /**
     * Action Performed method takes in an Action event. Determines what
     * happens to buttons when they are pressed.
     */
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == jbChat){
            sendMessage();
        } else if(ae.getSource() == jbCard1) {
           getIcon(0);
           if (selCard1 == -1 && !matched.contains(loc[0])) {
              selCard1 = 0;
           } else { if(selCard1 == 0) {} else { selCard2 = 0; }}
        } else if(ae.getSource() == jbCard2) {
           getIcon(1);
           if (selCard1 == -1 && !matched.contains(loc[1])) {
              selCard1 = 1;
           } else { if(selCard1 == 1) {} else { selCard2 = 1; }}
        } else if(ae.getSource() == jbCard3) {
           getIcon(2);
           if (selCard1 == -1 && !matched.contains(loc[2])) {
              selCard1 = 2;
           } else { if(selCard1 == 2) {} else { selCard2 = 2; }}
        } else if(ae.getSource() == jbCard4) {
           getIcon(3);
           if (selCard1 == -1 && !matched.contains(loc[3])) {
              selCard1 = 3;
           } else { if(selCard1 == 3) {} else { selCard2 = 3; }}
        } else if(ae.getSource() == jbCard5) {
           getIcon(4);
           if (selCard1 == -1 && !matched.contains(loc[4])) {
              selCard1 = 4;
           } else { if(selCard1 == 4) {} else { selCard2 = 4; }}
        } else if(ae.getSource() == jbCard6) {
           getIcon(5);
           if (selCard1 == -1 && !matched.contains(loc[5])) {
              selCard1 = 5;
           } else { if(selCard1 == 5) {} else { selCard2 = 5; }}
        } else if(ae.getSource() == jbCard7) {
           getIcon(6);
           if (selCard1 == -1 && !matched.contains(loc[6])) {
              selCard1 = 6;
           } else { if(selCard1 == 6) {} else { selCard2 = 6; }}
        } else if(ae.getSource() == jbCard8) {
           getIcon(7);
           if (selCard1 == -1 && !matched.contains(loc[7])) {
              selCard1 = 7;
           } else { if(selCard1 == 7) {} else { selCard2 = 7; }}
        } else if(ae.getSource() == jbCard9) {
           getIcon(8);
           if (selCard1 == -1 && !matched.contains(loc[8])) {
              selCard1 = 8;
           } else { if(selCard1 == 8) {} else { selCard2 = 8; }}
        } else if(ae.getSource() == jbCard10) {
           getIcon(9);
           if (selCard1 == -1 && !matched.contains(loc[9])) {
              selCard1 = 9;
           } else { if(selCard1 == 9) {} else { selCard2 = 9; }}
        } else if(ae.getSource() == jbCard11) {
           getIcon(10);
           if (selCard1 == -1 && !matched.contains(loc[10])) {
              selCard1 = 10;
           } else { if(selCard1 == 10) {} else { selCard2 = 10; }}
        } else if(ae.getSource() == jbCard12) {
           getIcon(11);
           if (selCard1 == -1 && !matched.contains(loc[11])) {
              selCard1 = 11;
           } else { if(selCard1 == 11) {} else { selCard2 = 11; }}
        } else if(ae.getSource() == jbCard13) {
           getIcon(12);
           if (selCard1 == -1 && !matched.contains(loc[12])) {
              selCard1 = 12;
           } else { if(selCard1 == 12) {} else { selCard2 = 12; }}
        } else if(ae.getSource() == jbCard14) {
           getIcon(13);
           if (selCard1 == -1 && !matched.contains(loc[13])) {
              selCard1 = 13;
           } else { if(selCard1 == 13) {} else { selCard2 = 13; }}
        } else if(ae.getSource() == jbCard15) {
           getIcon(14);
           if (selCard1 == -1 && !matched.contains(loc[14])) {
              selCard1 = 14;
           } else { if(selCard1 == 14) {} else { selCard2 = 14; }}
        } else if(ae.getSource() == jbCard16) {
           getIcon(15);
           if (selCard1 == -1 && !matched.contains(loc[15])) {
              selCard1 = 15;
           } else { if(selCard1 == 15) {} else { selCard2 = 15; }}
        }
        if (selCard1 != -1 && selCard2 != -1 && selCard1 != selCard2) {
           this.verifyMatch(selCard1, selCard2);
        }
   }//action listener

   /**
    * Checks to see wheter to cards are matched or not
    * if its matched it adds the card to the matched cards arraylist
    * once done it resets the selected cards -1
    */
   public void verifyMatch(int card1, int card2) {
      if (matched.contains(loc[card1]) == false && matched.contains(loc[card2]) == false) {
         if (loc[card1] != loc[card2]) {
            this.setBlank(card1);
            this.setBlank(card2);
         } else {
            matched.add(loc[card1]);
            if (matched.size() == 8) {
               jtaChat.append("YOU WON");
            }
         }
         selCard1 = -1;
         selCard2 = -1;
      }         
   }
   
   /**
    * Reads the line from the server that is sent from another player.
    */
   public void message(){
      try{
         socket = new Socket("localhost", 16788); //localhost for now
         
         //in from the clent
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         //out to client
         out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
         while(true){
            String read = br.readLine();
            String cmd = read.substring(0,3);
            if (cmd.equalsIgnoreCase("LOC") == true) {
               for(int p = 3; p <= 18; p++) {
                  int pos = p-3;
                  loc[pos] = Integer.parseInt(read.substring(p,p+1));
               }
            } else {
               jtaChat.append(read+"\n");
            }

         }

      }catch(UnknownHostException e){
         System.out.println(e.getMessage());
      }catch(IOException e){
         System.out.println(e.getMessage());
      }catch(Exception e){
         System.out.println(e.getMessage());
      }

   } //message
   
   /**
    * sendMessage allows for the client to send a message back to a player.
    */
   public void sendMessage(){
        String str = "";
        String user = jtfUser.getText();
        if(user.equals("") == true) {
           user = "Client";
           str = user + ": " + jtfChat.getText();
        }
        else {
           str = user + ": " + jtfChat.getText();
        }
        out.println(str);
        out.flush();
        jtfChat.setText(null);
   } //sendMessage
   
   /* 
    * Sets the image for the selected card to blank; only used if a card doesnt match
    */
   public void setBlank(int card) {
      if(card == 0) {
         jlCard1.setIcon(back);
      } else if(card == 1) {
         jlCard2.setIcon(back);
      } else if(card == 2) {
         jlCard3.setIcon(back);
      } else if(card == 3) {
         jlCard4.setIcon(back);
      } else if(card == 4) {
         jlCard5.setIcon(back);
      } else if(card == 5) {
         jlCard6.setIcon(back);
      } else if(card == 6) {
         jlCard7.setIcon(back);
      } else if(card == 7) {
         jlCard8.setIcon(back);
      } else if(card == 8) {
         jlCard9.setIcon(back);
      } else if(card == 9) {
         jlCard10.setIcon(back);
      } else if(card == 10) {
         jlCard11.setIcon(back);
      } else if(card == 11) {
         jlCard12.setIcon(back);
      } else if(card == 12) {
         jlCard13.setIcon(back);
      } else if(card == 13) {
         jlCard14.setIcon(back);
      } else if(card == 14) {
         jlCard15.setIcon(back);
      } else if(card == 15) {
         jlCard16.setIcon(back);
      }
   }

   
   /* getIcon will take a position that the card was click and will reveal the cards clicked
    * Sets the images for the cards once a button is clicked
    */
   public void getIcon(int inPosition){
      //Order of Card Images in Array: {c2, c6, cJ, d10, d5, d9, dQ, h4, h8, hK, s3, sA}
      
      if(inPosition == 0) {
         jlCard1.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 1) {
         jlCard2.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 2) {
         jlCard3.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 3) {
         jlCard4.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 4) {
         jlCard5.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 5) {
         jlCard6.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 6) {
         jlCard7.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 7) {
         jlCard8.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 8) {
         jlCard9.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 9) {
         jlCard10.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 10) {
         jlCard11.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 11) {
         jlCard12.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 12) {
         jlCard13.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 13) {
         jlCard14.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 14) {
         jlCard15.setIcon(cards[loc[inPosition]]);
      } else if(inPosition == 15) {
         jlCard16.setIcon(cards[loc[inPosition]]);
      }
   }//getIcon

    public static void main(String[] args) throws Exception {
        MatchingGameClient client = new MatchingGameClient();
        client.message();
    }//main
}