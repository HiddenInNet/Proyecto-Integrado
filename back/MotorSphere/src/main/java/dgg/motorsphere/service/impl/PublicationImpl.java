package dgg.motorsphere.service.impl;

import dgg.motorsphere.service.IPublicacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationImpl implements IPublicacion {

    /*
    @Autowired
    private PublicationDAO publicationDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Publication> findAll() {
        return (List<Publication>) publicationDAO.findAll();
    }

    @Override
    public Publication findById(Long id) {
        return publicationDAO.findById(id).orElse(null);
    }

    @Override
    public Boolean remove(Long id) {
        if (id != null && publicationDAO.existsById(id)) {
            publicationDAO.deleteById(id);
            return !publicationDAO.existsById(id);
        }
        return false;
    }

    @Override
    public Publication save(PublicationDTO publicationDTO) {
        if(publicationDTO != null){
            Publication publication = Publication.builder()
                    .id(publicationDTO.getPublicationId())
                    .likes(publicationDTO.getLikes())
                    .date(publicationDTO.getUploadDate())
                    .usuario(usuarioDAO.findById(publicationDTO.getUserId()).orElse(null))
                    .information(publicationDTO.getInformation())
                    .name(publicationDTO.getName())
                    .image(publicationDTO.getImage())
                    .build();
            return publicationDAO.save(publication);
        }

        return null;
    }

     */
}
