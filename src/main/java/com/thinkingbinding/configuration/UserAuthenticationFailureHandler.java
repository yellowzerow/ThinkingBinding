package com.thinkingbinding.configuration;

import com.thinkingbinding.exception.MemberException;
import com.thinkingbinding.type.MemberErrorCode;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request
            , HttpServletResponse response
            , AuthenticationException exception
    ) throws IOException, ServletException {

        if (exception instanceof InternalAuthenticationServiceException) {
            throw new MemberException(MemberErrorCode.LOGIN_FAIL);
        }

        setUseForward(true);
        setDefaultFailureUrl("/member/login?error=true");
        request.setAttribute("errorMessage", MemberErrorCode.LOGIN_FAIL.getDescription());

        super.onAuthenticationFailure(request, response, exception);
    }
}
