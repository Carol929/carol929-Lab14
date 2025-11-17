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
        out.println("12345");
        out.flush();
    }

    public String request(String number) throws Exception{
        out.println(number);
        out.flush();
        return in.readLine();
    }

    public void disconnect() throws Exception{
        try { 
            in.close(); 
        } 
        catch (Exception e) {
        }
        try { 
            out.close(); 
        } 
        catch (Exception e) {
        }
        try { 
            socket.close(); 
        } 
        catch (Exception e) {
        }
    }
}
