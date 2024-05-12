package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.RegisterDTO;

public interface IAuth {

    LoginDTO findUser(LoginDTO loginDTO);
}
