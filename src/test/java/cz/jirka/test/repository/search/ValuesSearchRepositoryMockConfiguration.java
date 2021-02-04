package cz.jirka.test.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ValuesSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ValuesSearchRepositoryMockConfiguration {

    @MockBean
    private ValuesSearchRepository mockValuesSearchRepository;

}
