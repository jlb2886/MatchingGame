import java.net.*; //All network classes
import java.io.*; //All read/write operations
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class MyGui implements ActionListener{
   private JTextField jtfChat;
   private JTextField jtfUser;
   private JLabel jlUser;
   private Icon back;
   private Icon c2;
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
   private Socket s;
   private BufferedReader br;
   private PrintWriter pw;
   
   public MyGui() {
      //Chat
      JFrame jfClient = new JFrame("Matching Game Client");
      JPanel jpClient = new JPanel(new BorderLayout());
      jtaChat = new JTextArea(20,20);
      jtfChat = new JTextField(20);
      jtfUser = new JTextField(10);
      jlUser = new JLabel("Username: ");
      JButton jbChat = new JButton("Send");
      
      back = new ImageIcon("BK.png");
      c2 = new ImageIcon("C2.png");
      JButton jbCard1 = new JButton("1 ^^^");
      jlCard1 = new JLabel();
      jlCard1.setIcon(back);
      //Card 2
      JButton jbCard2 = new JButton("2 ^^^");
      jlCard2 = new JLabel();
      jlCard2.setIcon(back);
      //Card 3
      JButton jbCard3 = new JButton("3 ^^^");
      jlCard3 = new JLabel();
      jlCard3.setIcon(back);
      //Card 4
      JButton jbCard4 = new JButton("4 ^^^");
      jlCard4 = new JLabel();
      jlCard4.setIcon(back);
      //Card 5
      JButton jbCard5 = new JButton("5 ^^^");
      jlCard5 = new JLabel();
      jlCard5.setIcon(back);
      //Card 6
      JButton jbCard6 = new JButton("6 ^^^");
      jlCard6 = new JLabel();
      jlCard6.setIcon(back);
      //Card 7
      JButton jbCard7 = new JButton("7 ^^^");
      jlCard7 = new JLabel();
      jlCard7.setIcon(back);
      //Card 8
      JButton jbCard8 = new JButton("8 ^^^");
      jlCard8 = new JLabel();
      jlCard8.setIcon(back);
      //Card 9
      JButton jbCard9 = new JButton("9 ^^^");
      jlCard9 = new JLabel();
      jlCard9.setIcon(back);
      //Card 10
      JButton jbCard10 = new JButton("10 ^^^");
      jlCard10 = new JLabel();
      jlCard10.setIcon(back);
      //Card 11
      JButton jbCard11 = new JButton("11 ^^^");
      jlCard11 = new JLabel();
      jlCard11.setIcon(back);
      //Card 12
      JButton jbCard12 = new JButton("12 ^^^");
      jlCard12 = new JLabel();
      jlCard12.setIcon(back);
      //Card 13
      JButton jbCard13 = new JButton("13 ^^^");
      jlCard13 = new JLabel();
      jlCard13.setIcon(back);
      //Card 14
      JButton jbCard14 = new JButton("14 ^^^");
      jlCard14 = new JLabel();
      jlCard14.setIcon(back);
      //Card 15
      JButton jbCard15 = new JButton("15 ^^^");
      jlCard15 = new JLabel();
      jlCard15.setIcon(back);
      //Card 16
      JButton jbCard16 = new JButton("16 ^^^");
      jlCard16 = new JLabel();
      jlCard16.setIcon(back);
      
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
      
      //jfClient.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfClient.setSize(600, 600);
      jfClient.setVisible(true);
      jpClient.setVisible(true);
      jpChatSend.setVisible(true);
      jfClient.setLocationRelativeTo(null);
      jfClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      jbChat.addActionListener(this);
      jbCard1.addActionListener(this);
      
      //Game
      

   }
   //!!!!Fix the buttons so they work!!!!!
   public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("jbChat")){
            sendMessage();
        } else if(ae.getActionCommand().equals("jbCard1")) {
           jlCard1.setIcon(c2);
        }           
   }//action listener

   
   
   public void message(){
      try{
         
         //these 2 lines will get op address of this client/machine
        // System.out.println("getLocalHost: "+ InetAddress.getLocalHost() );
         //System.out.println("getByName: "+ InetAddress.getByName("localhost") );
         
         s = new Socket("127.0.0.1", 16788);
         
         //in from the clent
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));
         //out to client
         pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
         while(true){
            jtaChat.append(br.readLine()+"\n");
         }
         //terminate the program
         //br.close();
         //pw.close();
         //s.close();

      }catch(UnknownHostException e){
         System.out.println(e.getMessage());
      }catch(IOException e){
         System.out.println(e.getMessage());
      }catch(Exception e){
         System.out.println(e.getMessage());
      }

   }

   public void sendMessage(){
        String str;
        if(jtfUser.getText() == "") {
           str = "Client";
        }
        else {
           str = jtfUser.getText()+": "+jtfChat.getText();
        }
        pw.println(str);
        pw.flush();
        jtfChat.setText(null);
                    
   }
   public static void main(String[] args) {
      MyGui gui = new MyGui();
      gui.message();
   }//main
   
   

} //class