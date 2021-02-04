package cz.jirka.test.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link DeviceProfileSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DeviceProfileSearchRepositoryMockConfiguration {

    @MockBean
    private DeviceProfileSearchRepository mockDeviceProfileSearchRepository;

}
