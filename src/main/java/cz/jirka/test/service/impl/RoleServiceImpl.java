package cz.jirka.test.service.impl;

import cz.jirka.test.service.RoleService;
import cz.jirka.test.domain.Role;
import cz.jirka.test.repository.RoleRepository;
import cz.jirka.test.repository.search.RoleSearchRepository;
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
 * Service Implementation for managing {@link Role}.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    private final RoleSearchRepository roleSearchRepository;

    public RoleServiceImpl(RoleRepository roleRepository, RoleSearchRepository roleSearchRepository) {
        this.roleRepository = roleRepository;
        this.roleSearchRepository = roleSearchRepository;
    }

    @Override
    public Role save(Role role) {
        log.debug("Request to save Role : {}", role);
        Role result = roleRepository.save(role);
        roleSearchRepository.save(result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        log.debug("Request to get all Roles");
        return roleRepository.findAllWithEagerRelationships();
    }


    public Page<Role> findAllWithEagerRelationships(Pageable pageable) {
        return roleRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findOne(Long id) {
        log.debug("Request to get Role : {}", id);
        return roleRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Role : {}", id);
        roleRepository.deleteById(id);
        roleSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> search(String query) {
        log.debug("Request to search Roles for query {}", query);
        return StreamSupport
            .stream(roleSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
