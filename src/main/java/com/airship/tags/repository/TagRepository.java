package com.airship.tags.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airship.tags.domain.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long>{

}
