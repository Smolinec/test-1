package cz.jirka.test.service.impl;

import cz.jirka.test.service.ValuesService;
import cz.jirka.test.domain.Values;
import cz.jirka.test.repository.ValuesRepository;
import cz.jirka.test.repository.search.ValuesSearchRepository;
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
 * Service Implementation for managing {@link Values}.
 */
@Service
@Transactional
public class ValuesServiceImpl implements ValuesService {

    private final Logger log = LoggerFactory.getLogger(ValuesServiceImpl.class);

    private final ValuesRepository valuesRepository;

    private final ValuesSearchRepository valuesSearchRepository;

    public ValuesServiceImpl(ValuesRepository valuesRepository, ValuesSearchRepository valuesSearchRepository) {
        this.valuesRepository = valuesRepository;
        this.valuesSearchRepository = valuesSearchRepository;
    }

    @Override
    public Values save(Values values) {
        log.debug("Request to save Values : {}", values);
        Values result = valuesRepository.save(values);
        valuesSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Values> findAll() {
        log.debug("Request to get all Values");
        return valuesRepository.findAllWithEagerRelationships();
    }


    public Page<Values> findAllWithEagerRelationships(Pageable pageable) {
        return valuesRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Values> findOne(Long id) {
        log.debug("Request to get Values : {}", id);
        return valuesRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Values : {}", id);
        valuesRepository.deleteById(id);
        valuesSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Values> search(String query) {
        log.debug("Request to search Values for query {}", query);
        return StreamSupport
            .stream(valuesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}