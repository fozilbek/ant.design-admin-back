package com.efa.windoor.core.repositories;

import com.efa.windoor.core.entities.RuleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RuleRepository extends JpaRepository<RuleEntity, Long>, JpaSpecificationExecutor<RuleEntity> {
    @Query(value = "from RuleEntity r where 1=1 ")
    Page<RuleEntity> findAll(Pageable pageable);
}
