package cz.jirka.test.repository.search;

import cz.jirka.test.domain.DeviceProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link DeviceProfile} entity.
 */
public interface DeviceProfileSearchRepository extends ElasticsearchRepository<DeviceProfile, Long> {
}
