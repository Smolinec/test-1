package cz.jirka.test.repository.search;

import cz.jirka.test.domain.Temperature;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Temperature} entity.
 */
public interface TemperatureSearchRepository extends ElasticsearchRepository<Temperature, Long> {
}
