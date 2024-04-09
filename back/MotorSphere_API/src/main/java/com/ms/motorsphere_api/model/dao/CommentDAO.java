package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDAO extends CrudRepository<Comment, Long> {
}
