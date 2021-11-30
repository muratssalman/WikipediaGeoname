package org.wikipediasearch.geonameswikipediasearchserviceapp.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wikipediasearch.geonameswikipediasearchserviceapp.helper.WikipediaSearchHelper;
import org.wikipediasearch.geonameswikipediasearchserviceapp.model.WikipediaSearch;
import org.wikipediasearch.geonameswikipediasearchserviceapp.service.mapper.IWikipediaSearchMapper;
import org.wikipediasearch.geonameswikipediasearchserviceapp.viewmodel.WikipediaSearchViewModel;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@Transactional
public class WikipediaSearchService {
    private final WikipediaSearchHelper wikipediaSearchHelper;
    private final RestTemplate restTemplate;
    private final IWikipediaSearchMapper wikipediaSearchMapper;
    @Value("${geonames.url}")
    private String urlTemplate;
    static final Logger logger = LoggerFactory.getLogger(WikipediaSearchService.class);


    public WikipediaSearchService(WikipediaSearchHelper wikipediaSearchHelper, RestTemplate restTemplate, IWikipediaSearchMapper wikipediaSearchMapper) {
        this.wikipediaSearchHelper = wikipediaSearchHelper;
        this.restTemplate = restTemplate;
        this.wikipediaSearchMapper = wikipediaSearchMapper;
    }

    private WikipediaSearchViewModel getByRest(String text)
    {
        logger.info("Service: Fetching user with id {}", text);

        String url = String.format(urlTemplate, text);
        return restTemplate.getForObject(url, WikipediaSearchViewModel.class);
    }

    private WikipediaSearchViewModel getBySearchs(Iterable<WikipediaSearch> wikipediaSearches)
    {
        return new WikipediaSearchViewModel(StreamSupport.stream(wikipediaSearches.spliterator(), false)
                .map(wikipediaSearchMapper::wikipediaSearchToWikipediaSearchInfo)
                .collect(Collectors.toList()));
    }

    private WikipediaSearchViewModel getSearchInfoCallback(String text)
    {
        if (wikipediaSearchHelper.exitsByText(text))
            return getBySearchs(wikipediaSearchHelper.findSearchsByText(text));

        WikipediaSearchViewModel model = getByRest(text);
        Iterable<WikipediaSearch> list = model.getGeonames().stream()
                .map(wikipediaSearchMapper::wikipediaSearchInfoToWikipediaSearch)
                .peek(ws -> ws.setText(text))
                .collect(Collectors.toList());
        wikipediaSearchHelper.saveAllSearchs(list);

        return model;
    }

    public WikipediaSearchViewModel getSearchInfo(String text)
    {
        return getSearchInfoCallback(text);
    }

    public WikipediaSearchViewModel deleteByText(String text)
    {
        wikipediaSearchHelper.deleteRecordByText(text);

        return getBySearchs(wikipediaSearchHelper.getAllDb());
    }

}
