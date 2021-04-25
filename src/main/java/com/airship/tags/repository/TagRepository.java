package com.airship.tags.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.airship.tags.domain.TagEntity;

public interface TagRepository extends MongoRepository<TagEntity, Long>{

}
