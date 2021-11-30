package org.wikipediasearch.geonameswikipediasearchserviceapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wikipediasearch.geonameswikipediasearchserviceapp.service.WikipediaSearchService;
import org.wikipediasearch.geonameswikipediasearchserviceapp.viewmodel.WikipediaSearchInfo;
import org.wikipediasearch.geonameswikipediasearchserviceapp.viewmodel.WikipediaSearchViewModel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Log4j2
@RestController
@Api("Wikipedia Controller Rest Api")
@RequestMapping("api/wikipedia")
public class WikipediaSearchRestController {
    private final WikipediaSearchService wikipediaSearchService;

    Logger logger = LogManager.getLogger(WikipediaSearchRestController.class);

    public WikipediaSearchRestController(WikipediaSearchService wikipediaSearchService) {
        this.wikipediaSearchService = wikipediaSearchService;
    }

    @GetMapping("/search")
    @ApiOperation(value = "Get place from Wikipedia as Model")
    public ResponseEntity<WikipediaSearchViewModel> getSearchInfo(@RequestParam("q") String text)
    {
        logger.info("Controller: Fetching place with text {}", text);
        return ResponseEntity.ok(wikipediaSearchService.getSearchInfo(text));
    }

    @GetMapping("/searchiter")
    @ApiOperation(value = "Get place from Wikipedia as Array")
    public ResponseEntity<Iterable<WikipediaSearchInfo>> getSearchInfoIterable(@RequestParam("q") String text)
    {
        logger.info("Controller: Fetching WikiSearch with id {}", text);
        return ResponseEntity.ok(getSearchInfo(text).getBody().getGeonames());
    }

    @RequestMapping(value = "/deleterecord/{text}", method = RequestMethod.POST)
    @ApiOperation(value = "Delete record from database")
    public ResponseEntity<WikipediaSearchViewModel> deleteRecordByText(@PathVariable String text)
    {

        logger.info("Controller: Fetching record with text {}", text);

        wikipediaSearchService.deleteByText(text);

        return ResponseEntity.ok(wikipediaSearchService.deleteByText(text));

    }
}
