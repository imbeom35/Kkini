package com.kkini.core.domain.own.service;

import com.kkini.core.domain.own.entity.Own;
import com.kkini.core.domain.own.repository.OwnRepository;
import com.kkini.core.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OwnServiceImpl implements OwnService {

    private final OwnRepository ownRepository;

    @Override
    public void changeSelectedBadge(Long originBadgeId, Long newBadgeId) {
        Own oldOwn = ownRepository.findById(originBadgeId).orElseThrow(() -> new NotFoundException(Own.class, originBadgeId));
        Own newOwn = ownRepository.findById(newBadgeId).orElseThrow(() -> new NotFoundException(Own.class, newBadgeId));

        oldOwn.changeSelected();
        newOwn.changeSelected();
    }

}
