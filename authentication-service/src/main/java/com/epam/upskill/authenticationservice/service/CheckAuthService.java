package com.epam.upskill.authenticationservice.service;

import com.epam.upskill.authenticationservice.model.Users;
import com.epam.upskill.authenticationservice.model.dtos.*;

/**
 * @description: Service interface for Training entity.
 * @date: 08 November 2023 $
 * @time: 5:43 AM 30 $
 * @author: Qudratjon Komilov
 */
public interface CheckAuthService {


    boolean checkAuth(String token,String path);
}

