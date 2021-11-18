package com.example.zktraining.services.auth;

import com.example.zktraining.helper.AppConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Executions;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class AuthenticationService {
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationService.class);

    @Autowired
    private HttpSessionService httpSessionService;

    @Value("${auth.session.ttl}")
    private Integer SESSION_TIME_TO_LIFE; // don vi phut

    /**
     * - verify user
     * - create session authorized
     *
     * @return user info
     */

    /**
     * Destroyed session
     */


    /**
     * @return false else
     */
}
