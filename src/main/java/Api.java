import com.google.gson.Gson;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import models.ApiResponse;
import models.Phone;
import models.Website;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Api {
    static {
        System.setProperty("PORT", "8080");
    }

    private static List<Website> listWebsite = new ArrayList<>();

    public static void main(String[] args) {
        init();
        Vertx vertx = Vertx.factory.vertx();
        HttpServerOptions httpServerOptions = new HttpServerOptions()
                .setMaxInitialLineLength(4096 * 4)
                .setCompressionSupported(true)
                .setMaxHeaderSize(8192 * 2);
        Router router = Router.router(vertx);
        HttpServer httpServer = vertx.createHttpServer(httpServerOptions);

        router.get("/get/").blockingHandler(context -> {
            String param = context.request().getParam("text");
            String response = crawlWebsite(param);
            context.response()
                    .setStatusCode(200)
                    .end(response);
        });

        httpServer.requestHandler(router::accept).listen(Integer.parseInt(System.getenv("PORT")), "0.0.0.0");
        System.out.println("Listen on port: " + System.getenv("PORT"));
    }

    private static void init() {
        listWebsite.add(new Website("https://www.thegioididong.com",
                "https://www.thegioididong.com/tim-kiem?key=",
                "https://www.thegioididong.com",
                "ul",
                "li",
                "img",
                "a",
                    "a h3",
                "a strong"
        ));
        listWebsite.add(new Website("https://fptshop.com.vn",
                "https://fptshop.com.vn/tim-kiem/",
                "https://fptshop.com.vn",
                "div#listProduct",
                "div.mf-plti",
                "a div.mf-plti-img img",
                "a.clearfix",
                "div.mf-plti-if h3.mf-plti-name",
                "div.mf-plti-if p.mf-plti-price"
        ));
        listWebsite.add(new Website("https://vienthonga.vn",
                "https://vienthonga.vn/tim-kiem?q=",
                "https://vienthonga.vn",
                "div#product_search",
                "div.masonry-item",
                "div.product div.product-image a figure img",
                "div.product div.product-image a",
                "div.product div.product-name div.name",
                "div.product div.product-name div.price div.price-1"
        ));

    }

    private static String crawlWebsite(String str) {
        String response;
        Document doc;
        List<Phone> phoneList = new ArrayList<>();
        for (Website website : listWebsite) {
            try {
                doc = Jsoup.connect(website.getUrlQuery() + str).timeout(10*1000).get();
                Element list = doc.select(website.getNodeList()).first();
                if (list == null) return doc.html();
                Elements parents = list.select(website.getNodeParent());

                for (Element parent : parents) {
                    String name = parent.select(website.getNodeName()).text();
                    String urlDetail = parent.select(website.getNodeUrlDetail()).attr("href");
                    String urlImg = parent.select(website.getNodeImg()).attr("src");
                    String imgOriginal = parent.select(website.getNodeImg()).attr("data-original");
                    if (imgOriginal != null && !imgOriginal.isEmpty()) urlImg = imgOriginal;
                    String pricing = "0";
                    if( parent.select(website.getNodePricing()).size()>0){
                      pricing = parent.select(website.getNodePricing()).first().text();
                    }
                    Phone phone = new Phone();
                    phone.setName(name);
                    phone.setUrlDetail(website.getConfigUrl() + urlDetail);
                    phone.setUrlImage(urlImg);
                    phone.setPricing(Long.parseLong(pricing.replaceAll("[^\\d]+", "").replace(".", "")));
                    phoneList.add(phone);
                }

            } catch (IOException e) {
                return e.getMessage();
            }
        }
        ApiResponse<List<Phone>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setData(phoneList);
        response = new Gson().toJson(apiResponse);
        System.out.println(response);
        return response;
    }
}
