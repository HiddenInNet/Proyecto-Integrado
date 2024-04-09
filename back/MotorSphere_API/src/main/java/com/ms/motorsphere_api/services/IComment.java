package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.CommentDTO;
import com.ms.motorsphere_api.model.entity.Comment;

import java.util.List;

public interface IComment {

    List<Comment> findAll();
    Comment findById(Long id);
    Boolean remove(Long id);
    Comment save(CommentDTO commentDTO);
}
