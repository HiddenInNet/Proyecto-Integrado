package hiddeninnet.proyectointegrado.mappers.impl;


import hiddeninnet.proyectointegrado.api.dto.UserDTO;
import hiddeninnet.proyectointegrado.mappers.Mapper;
import hiddeninnet.proyectointegrado.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<User, UserDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO mapTo(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapFrom(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}

