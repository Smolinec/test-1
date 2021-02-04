package cz.jirka.test.repository.search;

import cz.jirka.test.domain.WebUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link WebUser} entity.
 */
public interface WebUserSearchRepository extends ElasticsearchRepository<WebUser, Long> {
}
