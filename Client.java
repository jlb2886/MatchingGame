import java.net.*;//all network classes
import java.io.*;//all read/write operations

public class MyClient{

   public static void main(String[] args){
      
      try{
         
         //these 2 lines will get op address of this client/machine
         System.out.println("getLocalHost: "+ InetAddress.getLocalHost() );
         System.out.println("getByName: "+ InetAddress.getByName("localhost") );
         
         Socket s = new Socket("localhost", 16789);
         
         //in from the clent
         BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
         //out to client
         PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
         
         for(String txt : args){
            
            pw.println( txt ); //send message to server
            pw.flush();
            
            System.out.println(txt + " is now "+ br.readLine());
         }
         
         //terminate the program
         br.close();
         pw.close();
         s.close();

      }catch(UnknownHostException e){
         System.out.println(e.getMessage());
      }catch(IOException e){
         System.out.println(e.getMessage());
      }catch(Exception e){
         System.out.println(e.getMessage());
      }

   }//main
}//class