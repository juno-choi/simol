package com.simol.ounapi.global.auth;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.simol.simolcommon.common.auth.entity.UserEntity;
import com.simol.simolcommon.common.auth.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiJwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UsersRepository usersRepository;


    public Authentication getAuthentication(HttpServletRequest request) {

        StringBuffer requestURL = request.getRequestURL();
        // actuator 요청이면 패스
        if(requestURL.toString().contains("/actuator")) {
            request.setAttribute("userId", "0");
            request.setAttribute("userRole", "ROLE_TESTER");
            return createTestAuthentication();
        }

        // test token이 아니면 패스
        String userId = request.getHeader("X-User-Id");
        String userRole = request.getHeader("X-User-Role").toString().toUpperCase(Locale.ROOT);

        if(isTestProccess(userId, userRole)) {
            log.info("test access");
            return createTestAuthentication();
        }

        return new UsernamePasswordAuthenticationToken(userId, "", List.of(new SimpleGrantedAuthority(userRole)));
    }


    private boolean isTestProccess(String userId, String userRole) {
        final String TEST_USER_ID = "0";
        final String TEST_ROLE = "ROLE_TESTER";
        return TEST_USER_ID.equals(userId) && TEST_ROLE.equals(userRole);
    }

    private UsernamePasswordAuthenticationToken createTestAuthentication() {
        final String TEST_EMAIL = "juno@simol.com";
        final String TEST_NAME = "테스터";
        final String TEST_PROFILE_IMAGE = "";
        final String TEST_ROLE = "ROLE_USER";
        UserEntity userEntity = usersRepository.findByEmail(TEST_EMAIL)
            .orElseGet(() -> UserEntity.create(TEST_EMAIL, TEST_NAME, TEST_PROFILE_IMAGE, TEST_ROLE));
        UserEntity saveUser = usersRepository.save(userEntity);
        return new UsernamePasswordAuthenticationToken(saveUser.getId(), "", List.of(new SimpleGrantedAuthority(TEST_ROLE)));
    }
    
}
