package cz.jirka.test.repository.search;

import cz.jirka.test.domain.DeviceConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link DeviceConfiguration} entity.
 */
public interface DeviceConfigurationSearchRepository extends ElasticsearchRepository<DeviceConfiguration, Long> {
}
