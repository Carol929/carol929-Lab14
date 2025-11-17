import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) throws Exception{
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void handshake() throws Exception{

    }

    public String request(String number) throws Exception{

    }

    public void disconnect() throws Exception{

    }
}
