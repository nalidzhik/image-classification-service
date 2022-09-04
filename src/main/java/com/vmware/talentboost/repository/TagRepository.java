package com.vmware.talentboost.repository;

import com.vmware.talentboost.data.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
