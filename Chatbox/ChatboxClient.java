import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatboxClient extends JFrame implements ActionListener {

   String username;
   PrintWriter pw;
   BufferedReader br;
   JTextArea jtaMessages;
   JTextArea jtaUsers;
   JTextField jtfInput;
   JButton btnSend;
   JButton btnExit;
   Socket client;

   public ChatboxClient(String username, String servername) throws Exception {
   
      super("Connected as: " + username);  // set title for frame
      this.username = username;
      client = new Socket(servername, 16789);
      br = new BufferedReader(new InputStreamReader(client.getInputStream()));
      pw = new PrintWriter(client.getOutputStream(), true);
      pw.println(username);  // send name to server
      
      //bring up the chat gui
      buildInterface();
      new MessagesThread().start();  // create thread that listens for messages
      
      if (jtaMessages.getText().contains("Has connected!")) {
            
         new UserListThread().start();  // create thread that listens for user to be connected
            
      }
   
   } //ChatboxClient
   
   public void buildInterface() {
   
      btnSend = new JButton("Send");
      btnExit = new JButton("Exit");
      
      //chat area
      jtaMessages = new JTextArea();
      jtaMessages.setRows(10);
      jtaMessages.setColumns(50);
      jtaMessages.setEditable(false);
      
      //online users list
      jtaUsers = new JTextArea();
      jtaUsers.setRows(10);
      jtaUsers.setColumns(10);
      jtaUsers.setEditable(false);
      
      //top panel (chat area and online users list
      JScrollPane chatPanel = new JScrollPane(jtaMessages, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      JScrollPane onlineUsersPanel = new JScrollPane(jtaUsers, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      JPanel tp = new JPanel(new FlowLayout());
      tp.add(chatPanel);
      tp.add(onlineUsersPanel);
      add(tp, "Center");
      
      //user input field
      jtfInput = new JTextField(50);
      
      //buttom panel (input field, send and exit)
      JPanel bp = new JPanel(new FlowLayout());
      bp.add(jtfInput);
      bp.add(btnSend);
      bp.add(btnExit);
      add(bp, "South");
      btnSend.addActionListener(this);
      jtfInput.addActionListener(this);//allow user to press Enter key in order to send message
      btnExit.addActionListener(this);
      setSize(500, 300);
      setVisible(true);
      pack();
      
   } //Build Interface
   
   @Override
   public void actionPerformed(ActionEvent e) {
   
      if (e.getSource() == btnExit) {
      
         pw.println("disconnecting");  // send end to server so that server know about the termination
         System.exit(0);
         
      }else{
      
         // send message to server
         pw.println(jtfInput.getText());
      
      }
      
   }//actionPerformed 
      
   public static void main(String args[]) {
   
      // take username from user
      String name = JOptionPane.showInputDialog(null, "Enter your name: ", "Username",
      JOptionPane.PLAIN_MESSAGE);
      String servername = "localhost";
      
      try {
      
         new ChatboxClient(name, servername);
         
      } catch (Exception ex) {
      
         out.println("Unable to connect to server.\nError: " + ex.getMessage());
      
      }
   
   } // end of main
   
   // inner class for Messages Thread
   class MessagesThread extends Thread {
   
      @Override
      public void run() {
      
         String line;

         try {
         
            while (true) {
            
            line = br.readLine();
            jtaMessages.append(line + "\n");
            jtaMessages.setCaretPosition(jtaMessages.getDocument().getLength());//auto scroll to last message
            
            } // end of while
         
         } catch (Exception ex) {}
         
      } //end of run
   
   }//end of inner class
   
   // inner class for Users Thread
   class UserListThread extends Thread {
   
      @Override
      public void run() {
      
         String users;
         
         try {
         
            while (true) {
            
               if (jtaMessages.getText().contains("Has connected!")) {
            
                  users = br.readLine(); /** trying to list users ------------- */
                  jtaUsers.append(users + "\n");
            
               }
            
            } // end of while
         
         } catch (Exception ex) {}
         
      } //end of run
   
   }//end of inner class   
   
} //  end of client