import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import static java.lang.System.out;
import java.util.Vector;
import java.util.*;

public class ChatboxServer {

   ArrayList<String> users = new ArrayList<String>();
   Vector<HandleClient> clients = new Vector<HandleClient>();
   
   public void process() throws Exception {
   
      ServerSocket server = new ServerSocket(16789);
      out.println("Server Started...");
      
      while (true) {
      
         Socket client = server.accept();
         //add incoming client to connected clients vector.
         HandleClient c = new HandleClient(client);
         clients.add(c);
         
      }  // end of while
      
   } //process
   
   public static void main(String... args) throws Exception {
   
      new ChatboxServer().process();
      
   } // end of main
   
   public void broadcast(String user, String message) {
   
      // send message to all connected users
      for (HandleClient c : clients) {
      
         c.sendMessage(user, message);
      
      }
      
   } //broadcast
      
   /*
    * Inner class, responsible of handling incoming clients.
    * Each connected client will set as it's own thread.
    */
   class HandleClient extends Thread {
   
      String name = "";//client name/username
      BufferedReader input;//get input from client
      PrintWriter output;//send output to client
      
      public HandleClient(Socket client) throws Exception {
      
         // get input and output streams
         input = new BufferedReader(new InputStreamReader(client.getInputStream()));
         output = new PrintWriter(client.getOutputStream(), true);
         
         // read name
         name = input.readLine();
         users.add(name); // add to users vector
         broadcast(name, " Has connected!");
         listUsers();
         start();
         
      } //HandleClient
      
      public void sendMessage(String uname, String msg) {
      
         output.println(uname + ": " + msg);
         
      }
      
      public void getOnlineUsers() {
      
         for (HandleClient c : clients) {
         
            for (int i = 0; i < users.size(); i++) {
            
               broadcast("", users.get(i));
            
            } //for
            
         } //for
         
      } //getOnlineUsers
      
      public void listUsers() { /* ---------------------------- */
   
         for (HandleClient c : clients) {
         
            for (int i = 0; i < users.size(); i++) {
            
               output.println(users.get(i));
            
            } //for
         
         } //for
      
      } //listUsers

      
      public String getUserName() {
      
         return name;
      
      } //getUserName accessor 
      
      public void run() {
         
         //attribute
         String line;
         
         try {
         
            while (true) {
            
               line = input.readLine();
               
               if (line.equals("disconnecting")) {
               
                  //notify all for user disconnection
                  broadcast(name, " Has disconnected!");
                  clients.remove(this);
                  users.remove(name);
                  break;
               
               } else if(line.equals("!getusers")){ /** if user types in !getusers it will print out */
               
                  getOnlineUsers();
                  break;
                  
               }
               
               broadcast(name, line); // method  of outer class - send messages to all
               
            } // end of while
         } // try
         
         catch (Exception ex) {
         
            System.out.println(ex.getMessage());
            
         }
         
      } // end of run()
      
   } // end of inner class
   
} // end of ChatboxServer