package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.model.dao.CommentDAO;
import com.ms.motorsphere_api.model.dao.PublicationDAO;
import com.ms.motorsphere_api.model.dao.UserDAO;
import com.ms.motorsphere_api.model.dto.CommentDTO;
import com.ms.motorsphere_api.model.entity.Bidder;
import com.ms.motorsphere_api.model.entity.Comment;
import com.ms.motorsphere_api.services.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentImpl implements IComment {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PublicationDAO publicationDAO;

    @Override
    public List<Comment> findAll() {
        return (List<Comment>) commentDAO.findAll();
    }

    @Override
    public Comment findById(Long id) {
        if(id != null){
            return commentDAO.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        if(id != null){
            commentDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Comment save(CommentDTO commentDTO) {
        if(commentDTO != null){

            Comment comment = Comment.builder()
                    .information(commentDTO.getInformation())
                    .date(commentDTO.getDate())
                    .id(commentDTO.getCommentId())
                    .user(userDAO.findById(commentDTO.getUserId()).orElse(null))
                    .publication(publicationDAO.findById(commentDTO.getPublicationId()).orElse(null))
                    .build();
            return commentDAO.save(comment);
        }
        return null;
    }
}
