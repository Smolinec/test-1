package cz.jirka.test.service.impl;

import cz.jirka.test.service.DeviceProfileService;
import cz.jirka.test.domain.DeviceProfile;
import cz.jirka.test.repository.DeviceProfileRepository;
import cz.jirka.test.repository.search.DeviceProfileSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link DeviceProfile}.
 */
@Service
@Transactional
public class DeviceProfileServiceImpl implements DeviceProfileService {

    private final Logger log = LoggerFactory.getLogger(DeviceProfileServiceImpl.class);

    private final DeviceProfileRepository deviceProfileRepository;

    private final DeviceProfileSearchRepository deviceProfileSearchRepository;

    public DeviceProfileServiceImpl(DeviceProfileRepository deviceProfileRepository, DeviceProfileSearchRepository deviceProfileSearchRepository) {
        this.deviceProfileRepository = deviceProfileRepository;
        this.deviceProfileSearchRepository = deviceProfileSearchRepository;
    }

    @Override
    public DeviceProfile save(DeviceProfile deviceProfile) {
        log.debug("Request to save DeviceProfile : {}", deviceProfile);
        DeviceProfile result = deviceProfileRepository.save(deviceProfile);
        deviceProfileSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeviceProfile> findAll() {
        log.debug("Request to get all DeviceProfiles");
        return deviceProfileRepository.findAllWithEagerRelationships();
    }


    public Page<DeviceProfile> findAllWithEagerRelationships(Pageable pageable) {
        return deviceProfileRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceProfile> findOne(Long id) {
        log.debug("Request to get DeviceProfile : {}", id);
        return deviceProfileRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeviceProfile : {}", id);
        deviceProfileRepository.deleteById(id);
        deviceProfileSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeviceProfile> search(String query) {
        log.debug("Request to search DeviceProfiles for query {}", query);
        return StreamSupport
            .stream(deviceProfileSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
