import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public void serve(int expectedClients) throws Exception{

    }

    public ArrayList<LocalDateTime> getConnectedTimes(){

    }

    public void disconnect() throws Exception{

    }

    private void handleClient(Socket sock){

    }

    private int countFactors(long n){

    }
}
