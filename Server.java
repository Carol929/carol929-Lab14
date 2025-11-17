import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

private class ClientHandler implements Runnable {

    private ClientHandler(Socket socket){
    
    }

    @Override
    public void run(){

    }
}

public class Server {

    private int port;
    private ServerSocket serverSocket;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();

    public Server(int port) throws Exception{
        this.port = port;
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
           
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes(){
        ArrayList<LocalDateTime> copy = new ArrayList<>(connectedTimes);
        Collections.sort(copy);
        return copy;
    }

    public void disconnect() throws Exception{
        try { 
            serverSocket.close();
        } 
        catch (Exception e) {
        }
    }

}
