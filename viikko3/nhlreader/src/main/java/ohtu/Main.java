package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        Arrays.stream(players)
            .filter(p -> p.getNationality().equals("FIN"))
            .sorted((p1, p2) -> p2.getScore() - p1.getScore())
            .forEach(p -> System.out.println(p));
    }
}

