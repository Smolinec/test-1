package cz.jirka.test.repository.search;

import cz.jirka.test.domain.Role;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Role} entity.
 */
public interface RoleSearchRepository extends ElasticsearchRepository<Role, Long> {
}
