package cz.jirka.test.repository.search;

import cz.jirka.test.domain.Values;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Values} entity.
 */
public interface ValuesSearchRepository extends ElasticsearchRepository<Values, Long> {
}
