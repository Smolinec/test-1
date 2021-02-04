package cz.jirka.test.service.impl;

import cz.jirka.test.service.TemperatureService;
import cz.jirka.test.domain.Temperature;
import cz.jirka.test.repository.TemperatureRepository;
import cz.jirka.test.repository.search.TemperatureSearchRepository;
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
 * Service Implementation for managing {@link Temperature}.
 */
@Service
@Transactional
public class TemperatureServiceImpl implements TemperatureService {

    private final Logger log = LoggerFactory.getLogger(TemperatureServiceImpl.class);

    private final TemperatureRepository temperatureRepository;

    private final TemperatureSearchRepository temperatureSearchRepository;

    public TemperatureServiceImpl(TemperatureRepository temperatureRepository, TemperatureSearchRepository temperatureSearchRepository) {
        this.temperatureRepository = temperatureRepository;
        this.temperatureSearchRepository = temperatureSearchRepository;
    }

    @Override
    public Temperature save(Temperature temperature) {
        log.debug("Request to save Temperature : {}", temperature);
        Temperature result = temperatureRepository.save(temperature);
        temperatureSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Temperature> findAll() {
        log.debug("Request to get all Temperatures");
        return temperatureRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Temperature> findOne(Long id) {
        log.debug("Request to get Temperature : {}", id);
        return temperatureRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Temperature : {}", id);
        temperatureRepository.deleteById(id);
        temperatureSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Temperature> search(String query) {
        log.debug("Request to search Temperatures for query {}", query);
        return StreamSupport
            .stream(temperatureSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
