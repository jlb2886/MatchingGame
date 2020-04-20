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
   private JTextArea jtaChat;
   private Socket s;
   private BufferedReader br;
   private PrintWriter pw;
   
   public MyGui() {
      //Chat
      JFrame jfClient = new JFrame("Client");
      JPanel jpClient = new JPanel(new BorderLayout());
      jtaChat = new JTextArea(20,20);
      jtfChat = new JTextField(20);
      jtfUser = new JTextField(10);
      jlUser = new JLabel("Username: ");
      JButton jbChat = new JButton("Send");
      
      Icon icon1 = new ImageIcon("BK.png");
      JButton card1 = new JButton(icon1);
      
      JPanel jpChat = new JPanel(new GridLayout(2,1));
      JPanel jpChatSend = new JPanel(new GridLayout(3,1));
      JPanel jpGames = new JPanel(new GridLayout(2,5));
      JPanel jpUser = new JPanel(new GridLayout(1,2));
      
      jpChat.add(jtaChat);
      jpUser.add(jlUser);
      jpUser.add(jtfUser);
      jpChatSend.add(jpUser);
      jpChatSend.add(jtfChat);
      jpChatSend.add(jbChat);
      jpGames.add(card1);
      jpChat.add(jpChatSend);
      jpClient.add(jpChat, BorderLayout.WEST);
      jpClient.add(jpGames, BorderLayout.EAST);
      jfClient.add(jpClient);
      
      //jfClient.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfClient.setSize(600, 350);
      jfClient.setVisible(true);
      jpClient.setVisible(true);
      jpChatSend.setVisible(true);
      jfClient.setLocationRelativeTo(null);
      jfClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      jbChat.addActionListener(this);
      
      //Game
      

   }
   public void actionPerformed(ActionEvent ae){
        Object choice = ae.getActionCommand();
        sendMessage();
        //if(choice == "jbChat"){
        //    sendMessage();
        //}             
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