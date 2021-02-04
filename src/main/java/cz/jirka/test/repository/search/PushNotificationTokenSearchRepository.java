package cz.jirka.test.repository.search;

import cz.jirka.test.domain.PushNotificationToken;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PushNotificationToken} entity.
 */
public interface PushNotificationTokenSearchRepository extends ElasticsearchRepository<PushNotificationToken, Long> {
}
