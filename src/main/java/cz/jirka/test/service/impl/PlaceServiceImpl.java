package cz.jirka.test.service.impl;

import cz.jirka.test.service.PlaceService;
import cz.jirka.test.domain.Place;
import cz.jirka.test.repository.PlaceRepository;
import cz.jirka.test.repository.search.PlaceSearchRepository;
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
 * Service Implementation for managing {@link Place}.
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

    private final PlaceRepository placeRepository;

    private final PlaceSearchRepository placeSearchRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceSearchRepository placeSearchRepository) {
        this.placeRepository = placeRepository;
        this.placeSearchRepository = placeSearchRepository;
    }

    @Override
    public Place save(Place place) {
        log.debug("Request to save Place : {}", place);
        Place result = placeRepository.save(place);
        placeSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> findAll() {
        log.debug("Request to get all Places");
        return placeRepository.findAllWithEagerRelationships();
    }


    public Page<Place> findAllWithEagerRelationships(Pageable pageable) {
        return placeRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Place> findOne(Long id) {
        log.debug("Request to get Place : {}", id);
        return placeRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Place : {}", id);
        placeRepository.deleteById(id);
        placeSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> search(String query) {
        log.debug("Request to search Places for query {}", query);
        return StreamSupport
            .stream(placeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
