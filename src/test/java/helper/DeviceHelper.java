package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeviceHelper {
    public static String executeBash(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        final String[] message = {""};

        new Thread(()->{
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while (true){
                try {
                    if ((line = input.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                message[0] += line + "\n";
            }
            System.out.println(message[0]);
        }).start();
        try {
            p.waitFor();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return message[0];
    }
}
