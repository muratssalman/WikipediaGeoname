package org.wikipediasearch.GeonamesWikipediaSearchServiceApp;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.wikipediasearch.geonameswikipediasearchserviceapp.controller.WikipediaSearchRestController;
import org.wikipediasearch.geonameswikipediasearchserviceapp.service.WikipediaSearchService;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class App {

    @Spy
    @InjectMocks
    private WikipediaSearchRestController wikipediaSearchRestController;

    @Mock
    private WikipediaSearchService wikipediaSearchService;


    private final static String mainUrl = "http://api.geonames.org/wikipediaSearchJSON?formatted=true&q=bursa&maxRows=1&username=murat&style=full";

    @Test
    public void givenPlace_thenReturnJson()
            throws Exception {

        /**/
    }
}