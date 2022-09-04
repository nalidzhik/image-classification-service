package com.vmware.talentboost.repository;

import com.vmware.talentboost.data.ImageTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageTagRepository extends JpaRepository<ImageTags,Integer> {
}
