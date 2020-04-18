import java.net.*;//all network classes
import java.io.*;//all read/write operations

public class MyMultiThreadedServer{
   public static void main(String[] args){
      
      new MyMultiThreadedServer();
            
   }//main
   
   public MyMultiThreadedServer(){
      
         try{
         
         //these 2 lines will get op address of this client/machine
         System.out.println("getLocalHost: "+ InetAddress.getLocalHost() );
         System.out.println("getByName: "+ InetAddress.getByName("localhost") );
         
         ServerSocket ss = new ServerSocket(16789); //creates/opens the socket for the server
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