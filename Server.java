import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;



public class Server {

    private ServerSocket serverSocket;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();

    public Server(int port) throws Exception{
        this.serverSocket = new ServerSocket(port);
    }

    public void serve(int client) throws Exception{
        for (int i = 0; i < client; i++) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String key = in.readLine();
            if (!key.equals("12345")) {
                out.println("couldn't handshake");
                out.flush();
                socket.close();
                continue;
            }
            connectedTimes.add(LocalDateTime.now());
            ClientHandler handler = new ClientHandler(socket, in, out);
            new Thread(handler).start();       
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes(){
        ArrayList<LocalDateTime> copy = new ArrayList<>(connectedTimes);
        Collections.sort(copy);
        return copy;
    }

    public void disconnect(){
        try { 
            serverSocket.close();
        } 
        catch (Exception e) {
        }
    }

    private class ClientHandler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

              
        public ClientHandler(Socket socket, BufferedReader in, PrintWriter out) {
            this.socket = socket;
            this.in = in;
            this.out = out;
        }
      

        @Override
        public void run(){

            try {
                String read = in.readLine();
                long num = Long.parseLong(read);
                if (num > Integer.MAX_VALUE) {
                    throw new Exception("too big");
                }

                int count = 0;
                long lim = (long) Math.sqrt(num);
                for (long i = 1; i <= lim; i++) {
                    if (num % i == 0) {
                        if (i == num / i) {
                            count++;
                        } else {
                            count += 2;
                        }
                    }
                }
                String result = "The number " + num + " has " + count + " factors";
                out.println(result);
                out.flush();
            } catch (Exception e) {
                out.println("There was an exception on the server");
                out.flush();
            }

            try {
                socket.close();
            }
            catch (Exception e) {
            }
        }
    }

}
