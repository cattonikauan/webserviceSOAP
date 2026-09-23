package calc.Service;

import javax.xml.ws.Endpoint;

public class MusicaPlaylistServerPublisher {

    public static void main(String[] args)
    {
        // Porta diferente da calculadora (9876) para os dois poderem rodar juntos
        String url = "http://127.0.0.1:9877/musicaplaylist";
        Endpoint.publish(url, new MusicaPlaylistServerImpl());
        System.out.println("Web Service publicado em " + url);
        System.out.println("WSDL: " + url + "?wsdl");
    }
}
