package cz.jirka.test.service.impl;

import cz.jirka.test.service.PushNotificationTokenService;
import cz.jirka.test.domain.PushNotificationToken;
import cz.jirka.test.repository.PushNotificationTokenRepository;
import cz.jirka.test.repository.search.PushNotificationTokenSearchRepository;
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
 * Service Implementation for managing {@link PushNotificationToken}.
 */
@Service
@Transactional
public class PushNotificationTokenServiceImpl implements PushNotificationTokenService {

    private final Logger log = LoggerFactory.getLogger(PushNotificationTokenServiceImpl.class);

    private final PushNotificationTokenRepository pushNotificationTokenRepository;

    private final PushNotificationTokenSearchRepository pushNotificationTokenSearchRepository;

    public PushNotificationTokenServiceImpl(PushNotificationTokenRepository pushNotificationTokenRepository, PushNotificationTokenSearchRepository pushNotificationTokenSearchRepository) {
        this.pushNotificationTokenRepository = pushNotificationTokenRepository;
        this.pushNotificationTokenSearchRepository = pushNotificationTokenSearchRepository;
    }

    @Override
    public PushNotificationToken save(PushNotificationToken pushNotificationToken) {
        log.debug("Request to save PushNotificationToken : {}", pushNotificationToken);
        PushNotificationToken result = pushNotificationTokenRepository.save(pushNotificationToken);
        pushNotificationTokenSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PushNotificationToken> findAll() {
        log.debug("Request to get all PushNotificationTokens");
        return pushNotificationTokenRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PushNotificationToken> findOne(Long id) {
        log.debug("Request to get PushNotificationToken : {}", id);
        return pushNotificationTokenRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PushNotificationToken : {}", id);
        pushNotificationTokenRepository.deleteById(id);
        pushNotificationTokenSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PushNotificationToken> search(String query) {
        log.debug("Request to search PushNotificationTokens for query {}", query);
        return StreamSupport
            .stream(pushNotificationTokenSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
