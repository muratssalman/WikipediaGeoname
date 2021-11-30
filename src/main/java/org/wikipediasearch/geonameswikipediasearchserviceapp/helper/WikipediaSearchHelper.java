package org.wikipediasearch.geonameswikipediasearchserviceapp.helper;
//Sonnnnn

import org.springframework.stereotype.Component;
import org.wikipediasearch.geonameswikipediasearchserviceapp.model.WikipediaSearch;
import org.wikipediasearch.geonameswikipediasearchserviceapp.repository.IWikipediaSearchRepository;

@Component
public class WikipediaSearchHelper {
    private final IWikipediaSearchRepository m_wikipediaSearchRepository;

    public WikipediaSearchHelper(IWikipediaSearchRepository m_wikipediaSearchRepository) {
        this.m_wikipediaSearchRepository = m_wikipediaSearchRepository;
    }

    public Iterable<WikipediaSearch> getAllDb()
    {
        return m_wikipediaSearchRepository.findAll();
    }


    public Iterable<WikipediaSearch> findSearchsByText(String text)
    {
        return  m_wikipediaSearchRepository.findByText(text);
    }

    public boolean exitsByText(String text)
    {
        return  m_wikipediaSearchRepository.existsAllByText(text);
    }

    public Iterable<WikipediaSearch> saveAllSearchs(Iterable<WikipediaSearch> wikipediaSearches)
    {
        return m_wikipediaSearchRepository.saveAll(wikipediaSearches);
    }

    public Iterable<WikipediaSearch> deleteRecordByText(String text)
    {
        m_wikipediaSearchRepository.deleteByText(text);

        return m_wikipediaSearchRepository.findAll();
    }
}
