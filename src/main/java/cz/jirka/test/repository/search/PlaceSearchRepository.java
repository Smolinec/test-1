package cz.jirka.test.repository.search;

import cz.jirka.test.domain.Place;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Place} entity.
 */
public interface PlaceSearchRepository extends ElasticsearchRepository<Place, Long> {
}
