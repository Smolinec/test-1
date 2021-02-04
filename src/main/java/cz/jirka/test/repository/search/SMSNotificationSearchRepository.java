package cz.jirka.test.repository.search;

import cz.jirka.test.domain.SMSNotification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link SMSNotification} entity.
 */
public interface SMSNotificationSearchRepository extends ElasticsearchRepository<SMSNotification, Long> {
}
