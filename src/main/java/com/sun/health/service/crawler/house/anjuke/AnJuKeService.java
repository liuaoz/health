package com.sun.health.service.crawler.house.anjuke;

import com.sun.health.service.AbstractService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class AnJuKeService extends AbstractService {

    /**
     * 4175
     */
    public void handle(Integer houseId) {

        String url = "https://shanghai.anjuke.com/community/view/" + houseId;

        Connection connect = Jsoup.connect(url).userAgent("");

        try {
            Connection.Response response = connect.execute();

            Document document = response.parse();

            String respBody = response.body();

            System.out.println(respBody);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
