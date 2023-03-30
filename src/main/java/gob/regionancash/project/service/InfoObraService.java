package gob.regionancash.project.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Service
public class InfoObraService {

    private final static String BASE_URL = "https://apps.contraloria.gob.pe";

    private WebClient client;

    public InfoObraService() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        client = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT,
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
                .build();
    }

    public Map getProjectMap(String code) {
        Mono<String> response = client.post()
                .uri(uriBuilder -> uriBuilder.path("/ciudadano/wfm_obras_mostrar_1.aspx")
                        .queryParam("ID", code).build())
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        res -> res.bodyToMono(String.class).map(RuntimeException::new))
                .onStatus(HttpStatus.BAD_REQUEST::equals,
                        res -> res.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(String.class);
        Document doc = Jsoup.parse(response.block());
      

            return doc.body().select("article").first().select("> div.form-horizontal").stream()
                            .reduce(new HashMap<String,Object>(), (m, e) -> {

                                Elements tabs = e.select(".final_tab");

                                if (!tabs.isEmpty())
                                    tabs.stream().forEach((e2) -> {
                                        Elements elements = e2.select(".tab-content > .tab-pane");
                                        AtomicInteger count = new AtomicInteger(0);
                                        Map tabsm = new HashMap();
                                        e2.select("> ul > li").stream().forEach((e3) -> {
                                            String href = e3.child(0).attributes().get("href");
                                            Element content = elements.get(count.getAndIncrement());
                                            if (href != null) {
                                                content = e2.selectFirst(href);
                                            }

                                            Element table = content.select(".table-responsive > table").first();
                                            if (table != null) {
                                                tabsm.put(e3.text(), toList(table));
                                            } else {
                                                Elements items = content.select(".subtitulo_ficha");
                                                if (!items.isEmpty()) {
                                                    Map form = new HashMap<>();
                                                    items.stream().forEach((item) -> {
                                                        if (item.nextElementSibling() != null)
                                                            form.put(item.text(), item.nextElementSibling().text());
                                                    });
                                                    tabsm.put(e3.text(), form);
                                                } else
                                                    tabsm.put(e3.text(), content.text());
                                            }
                                        });
                                        m.put(e.select(".titulo").first().text(), tabsm);
                                    });
                                else {

                                    Element table = e.select(".table-responsive > table").first();
                                    if (table != null) {
                                        m.put(e.select(".titulo").first().text(), toList(table));
                                    } else {
                                        Map section = new HashMap();
                                        e.select(".fila > .titulo").stream().forEach((e2) -> {
                                            if (e2.nextElementSibling() != null)
                                                section.put(e2.text(), e2.nextElementSibling().text());
                                        });
                                        e.select(".modulo > h3").stream().forEach((e2) -> {
                                            if (e2.nextElementSibling() != null)
                                                section.put(e2.text(), e2.nextElementSibling().text());
                                        });
                                        m.put(e.select(".titulo").first().text(), section);
                                    }
                                }
                                return m;
                            },
                            (m, m2) -> {
                                m.putAll(m2);
                                return m;
                            });
   
    }

    private List toList(Element table) {
        Elements headers = table.select("> thead > tr > th");
        Elements rows = table.select("> tbody > tr");
        ArrayList l = new ArrayList();
        rows.stream().forEach((row) -> {
            AtomicInteger count = new AtomicInteger(0);
            HashMap item = new HashMap();
            headers.stream().forEach((header) -> {
                int c = count.getAndIncrement();
                Element cell=row.child(c);
                Element link=cell.select("a").first();
                if(link!=null){
                    item.put(header.text(),link.html());//.attributes().get("href"));
                }else
                    item.put(header.text(), cell.text());
            });
            l.add(item);
        });
        return l;
    }

    public InfoObraProject getProject(long pCOD_INFOBRAS) {
        Mono<String> response = client.post()
                .uri(uriBuilder -> uriBuilder.path("/ciudadano/Controladores/hdl_getResumenObra.ashx").build())
                .body(BodyInserters.fromFormData("pCOD_INFOBRAS", "" + pCOD_INFOBRAS).with("key", "value"))
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        res -> res.bodyToMono(String.class).map(RuntimeException::new))
                .onStatus(HttpStatus.BAD_REQUEST::equals,
                        res -> res.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(String.class);
        String code = response.block();
        String projectData[] = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8).split("~");
        /*
         * 0 - 74.53
         * 1 - GOBIERNO REGIONAL ANCASH
         * 2 - En ejecucion
         * 3 - 5,795,611.33
         * 4 - MEJORAMIENTO DEL SERVICIO DE SANEAMIENTO BASICO EN LOS CENTROS POBLADOS
         * DE SAN PEDRO DE UCHUPATA, YURAYACU Y RANCHAS, DISTRITO DE ACZO - PROVINCIA DE
         * ANTONIO RAYMONDI - DEPARTAMENTO DE ANCASH
         * 5 - 121959
         * 6 - Rggohš
         * 7 - imagen20220428173251.jpg
         * 8 - 0
         * 9 - 11
         * 10 - 0.00
         * 11 - 2
         * 12 - 2
         */
        return InfoObraProject.builder()
                .id(pCOD_INFOBRAS)
                .state(projectData[2])
                .code(projectData[6])
                .description(projectData[4])
                .build();
    }

}
