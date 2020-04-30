import java.net.*;//all network classes
import java.io.*;//all read/write operations
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Server{
   public static void main(String[] args){
      
      new Server();
            
   }//main
   
   public Server(){
      
         try{
         
         //these 2 lines will get op address of this client/machine
         //System.out.println("getLocalHost: "+ InetAddress.getLocalHost() );
         //System.out.println("getByName: "+ InetAddress.getByName("localhost") );
         
         ServerSocket ss = new ServerSocket(5555); //creates/opens the socket for the server
         Socket cs = null;
         
         while(true){
            
            System.out.println("Waiting for client to connect...");
            cs = ss.accept(); //waiting here for a conection from a client
            
            ThreadServer ts = new ThreadServer(cs);
            ts.start();
            System.out.println("Have a client "+ cs);
            
            }//keep listening for connections
      }catch(UnknownHostException e){
         System.out.println(e.getMessage());
      }catch(BindException e){
         System.out.println("Port is in use");
      }catch(IOException e){
         System.out.println(e.getMessage());
      }catch(Exception e){
         System.out.println(e.getMessage());
      }

   }//constructor
   
   //thread to handle aclient
   class ThreadServer extends Thread{
      
      Socket cs;
      
      public ThreadServer(Socket cs){
         this.cs = cs;
      }
      
      public String generateRandom() {
         ArrayList<Integer> randomString = new ArrayList<Integer>();
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
         return "LOC" + random;
      }
      
      public void run(){
            BufferedReader br;
            PrintWriter pw;
            
            try{
               //in from the client
               br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
               //out to client
               pw = new PrintWriter(new OutputStreamWriter(cs.getOutputStream()));
               
               String clientMsg;
               
               do{
                  clientMsg = br.readLine();
                  
                  if(clientMsg == null) break;
                  
                  clientMsg = clientMsg.toUpperCase();
                  
                  pw.println(clientMsg);
                  pw.flush(); //cleans the pw and sends the message
                  
                  System.out.println("Sent to client: " + clientMsg);
               }while( !clientMsg.equalsIgnoreCase("STOP"));
               
               //terminating the connection
               br.close();
               pw.close();
               cs.close();
               
              }catch(Exception e){
                  System.out.println(e.getMessage());
              }

      }//run
   }//ThreadServer
}//class