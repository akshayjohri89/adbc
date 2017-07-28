import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by akjohri on 7/28/2017.
 */
public class SocketStuff {
    public static void sendData(String data) {
        System.out.println("inside SocketStuff.sendData");
        try {
            Socket socket = new Socket("localhost", 7175);
            OutputStream os = socket.getOutputStream();
            DataInputStream is = new DataInputStream(socket.getInputStream());

            PrintWriter out = new PrintWriter(os);
            String toSend = "";
            out.print(data);

            System.out.println("Sent Data");
            byte[] messageByte = new byte[1000];
            boolean end = false;
            String messageString = "";
            int bytesRead = 0;
            try
            {
                while((bytesRead = is.read(messageByte)) != -1)
                {
                    messageString += new String(messageByte, 0, bytesRead);
                }
                System.out.println("MESSAGE: " + messageString);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
    }
}
