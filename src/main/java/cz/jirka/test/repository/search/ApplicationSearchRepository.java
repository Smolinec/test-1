package cz.jirka.test.repository.search;

import cz.jirka.test.domain.Application;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Application} entity.
 */
public interface ApplicationSearchRepository extends ElasticsearchRepository<Application, Long> {
}
