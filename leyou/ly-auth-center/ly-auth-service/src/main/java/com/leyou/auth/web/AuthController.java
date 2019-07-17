package com.leyou.auth.web;

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.service.AuthService;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private JwtProperties prop;

    @Autowired
    private AuthService authService;

    @Value("${ly.jwt.cookieName}")
    private String cookieName;

    /**
     * 登录授权
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletResponse response, HttpServletRequest request) {
        // 登录
        String token = authService.login(username, password);
        // TODO 写入cookie
        CookieUtils.setCookie(request, response, cookieName, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("LY_TOKEN") String token) {
        if (StringUtils.isBlank(token)) {
            // 如果没有token,证明未登录，返回403
            throw new LyException(ExceptionEnum.UNAUTHORIZED);
        }
        try {
            // 解析token
            UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());

            // 已登录，返回用户信息
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            // token已过期，或者  token被篡改
            throw new LyException(ExceptionEnum.UNAUTHORIZED);
        }
    }
}
