package com.example.zktraining.services.auth;

import com.example.zktraining.helper.AppConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class HttpSessionService implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(HttpSessionService.class);

    private static final Map<String, HttpSession> SESSION_AUTH_MAP = new HashMap<>();
    private static final Map<String, Desktop> DESKTOP_MAP = new HashMap<>();

    @Value("${auth.session.ttl}")
    private Integer SESSION_TIME_TO_LIFE; // don vi phut

    /**
     * Event created session && verify session authorized
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setMaxInactiveInterval(SESSION_TIME_TO_LIFE * 60); // Time to life

        // Chỉ add session authorized vào SESSION_AUTH_MAP, và hành động này đã được thực hiện ở AuthenticationService hàm
        // verify session
        /*if (verifySession(session)) {
            SESSION_AUTH_MAP.put(session.getId(), session);
            LOGGER.info("Create session authorized: " + session.getId());
        }*/
    }

    /**
     * Event destroyed session
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        LOGGER.info("Destroyed session authorized: " + event.getSession().getId());
        SESSION_AUTH_MAP.remove(event.getSession().getId());
    }

    /*private boolean verifySession(HttpSession session) {
        String accessToken = (String) session.getAttribute(AppConst.Auth.AUTH_KEY);
        if (accessToken == null) return false;

        String[] authStrArray = authenticationService.getAuthInfoByToken(accessToken);
        if (authStrArray != null) {
            if (!session.getId().equals(authStrArray[0])) {
                return false;
            }
            Date now = new Date();
            Long expTime = Long.parseLong(authStrArray[1]);
            if (expTime < now.getTime()) {
                LOGGER.info("Session expired: " + expTime + " - " + now.getTime());
                session.invalidate();
                return false;
            }

            return true;
        }

        return false;
    }*/

    /**
     * Destroyed session authorized
     */


    /**
     * Add manual session authorized
     */
    public void pushSession(HttpSession session) {
        LOGGER.info("Add session authorized into memory: " + session.getId());
        SESSION_AUTH_MAP.put(session.getId(), session);
    }

    /**
     * Find session authorized
     */
    public HttpSession find(String sessionId) {
        return SESSION_AUTH_MAP.get(sessionId);
    }

    /**
     * Function create session: new or old
     */
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    /**
     * logout remote by session ids
     */


    /**
     * logout remote by session id
     */


    /**
     * Reference data type => use must be careful
     */
    public Map<String, Desktop> getDesktopMap() {
        return DESKTOP_MAP;
    }

    /**
     * Reference data type => use must be careful
     */
    public static Map<String, HttpSession> getSessions() {
        return SESSION_AUTH_MAP;
    }

}
