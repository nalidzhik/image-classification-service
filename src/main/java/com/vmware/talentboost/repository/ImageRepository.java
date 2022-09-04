package com.vmware.talentboost.repository;

import com.vmware.talentboost.data.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByUrl(final String url);
}
