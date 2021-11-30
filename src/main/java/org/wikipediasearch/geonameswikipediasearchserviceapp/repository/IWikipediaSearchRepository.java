package org.wikipediasearch.geonameswikipediasearchserviceapp.repository;


import org.springframework.data.repository.CrudRepository;
import org.wikipediasearch.geonameswikipediasearchserviceapp.model.WikipediaSearch;

public interface IWikipediaSearchRepository extends CrudRepository<WikipediaSearch, Long> {
    Iterable<WikipediaSearch> findByText(String text);
    boolean existsAllByText(String text);
    void deleteByText(String text);
}
