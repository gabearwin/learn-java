package xyz.gabear.learn.javase.exercise;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;

@Slf4j
public class Connect {
    public static void main(String[] args) throws IOException, InterruptedException {
        log.info(new Connect().connectReachable("www.baidu.com").toString());
        log.info(new Connect().connectPing("www.baidu.com").toString());
        log.info(new Connect().connectSocket("localhost", 8888).toString());
        log.info(new Connect().connectGet("https://github.com").toString());
    }

    public Boolean connectReachable(String host) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(host);
        return inetAddress.isReachable(3 * 1000);
    }

    public Boolean connectPing(String host) throws IOException, InterruptedException {
        String cmd = String.format("ping -c 3 %s", host);
        Process ping = Runtime.getRuntime().exec(cmd);
        // This will return 0 if the host is reachable. Otherwise, you will get "2" as a return value.
        return ping.waitFor() == 0;
    }

    public Boolean connectSocket(String host, Integer port) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 3 * 1000);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Boolean connectGet(String urlPath) {
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            Object content = urlConnection.getContent();
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
