package cz.jirka.test.service.impl;

import cz.jirka.test.service.DeviceService;
import cz.jirka.test.domain.Device;
import cz.jirka.test.repository.DeviceRepository;
import cz.jirka.test.repository.search.DeviceSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Device}.
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;

    private final DeviceSearchRepository deviceSearchRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceSearchRepository deviceSearchRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceSearchRepository = deviceSearchRepository;
    }

    @Override
    public Device save(Device device) {
        log.debug("Request to save Device : {}", device);
        Device result = deviceRepository.save(device);
        deviceSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> findAll() {
        log.debug("Request to get all Devices");
        return deviceRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findOne(Long id) {
        log.debug("Request to get Device : {}", id);
        return deviceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Device : {}", id);
        deviceRepository.deleteById(id);
        deviceSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> search(String query) {
        log.debug("Request to search Devices for query {}", query);
        return StreamSupport
            .stream(deviceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
