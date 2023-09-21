package com.ms.tourist_app.application.utils;

import com.ms.tourist_app.application.constants.AppEnv;
import com.ms.tourist_app.application.constants.AppStr;
import com.ms.tourist_app.application.dai.UserRepository;
import com.ms.tourist_app.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@DependsOn("appEnv")
public class JwtUtil {
    // chứa các phương thức cơ bản

    private static final String SECRET_KEY = AppEnv.jwtConfigSecretKey;

    private static final Integer TIME_EXPIRATION = AppEnv.jwtConfigTimeExpiration;

    private final UserRepository userRepository;
    //
    private static HttpServletRequest httpServletRequest;

    public JwtUtil(UserRepository userRepository, HttpServletRequest httpServletRequest) {
        this.userRepository = userRepository;
        JwtUtil.httpServletRequest = httpServletRequest;
    }

    public String extractUsername(String token) {
        // lấy username theo token
        Claims claims = Jwts.parser().setSigningKey(AppEnv.jwtConfigSecretKey).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    public Date extractExpiration(String token) {
        // lấy thời gian hết hạn
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        // kiểm tra đã hết hạn hay chưa
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        // kiểm tra token
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Long getUserIdFromToken() {
        // lấy header khác null và có chứa jwt
        if (httpServletRequest.getHeader(AppStr.Auth.authorization)!=null&&httpServletRequest.getHeader
                (AppStr.Auth.authorization).replace(AppStr.Auth.bearer.concat(AppStr.Base.whiteSpace), "").length() > 6) {
            // xóa bearer đầu tiên
            String jwt = httpServletRequest.getHeader(AppStr.Auth.authorization).replace(AppStr.Auth.bearer.concat(AppStr.Base.whiteSpace), "");
           // lấy id từ claims
            Claims claims = Jwts.parser().setSigningKey(AppEnv.jwtConfigSecretKey).parseClaimsJws(jwt).getBody();
            return Long.parseLong(claims.get("id").toString());
        } else {
            return null;
        }
    }

    public String generateToken(UserDetails userDetails) {
        // tạo token
        Map<String, Object> claim = new HashMap<>();
        System.out.println(new Date(System.currentTimeMillis()) + " " + TIME_EXPIRATION + " " + SECRET_KEY);
        User user = userRepository.findByEmail(userDetails.getUsername());
        claim.put(AppStr.AuthorityConstant.claimUUID, userDetails.getUsername());
        claim.put(AppStr.AuthorityConstant.claimId, user.getId());
        return Jwts.builder().setSubject(userDetails.getUsername()).setClaims(claim).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
