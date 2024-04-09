package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationDAO extends CrudRepository<Publication, Long> {

    List<Publication> findAllByUserId(Long id);
}
