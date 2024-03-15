package com.dedagroup.springboottraining.repository;

import com.dedagroup.springboottraining.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findGroupsByIdIn(List<Long> idList);

}
