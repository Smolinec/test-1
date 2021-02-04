package cz.jirka.test.repository.search;

import cz.jirka.test.domain.Device;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Device} entity.
 */
public interface DeviceSearchRepository extends ElasticsearchRepository<Device, Long> {
}
