package com.jason;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String TOKEN_SECRET = "1234567890QWERTYUIOP";
    private static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwd2QiOiIxMjM0NTYiLCJ1c2VybmFtZSI6Imphc29uIn0.nHt-sZAxQElEgdoR-VabrCh8gDSHrXq66ov7g5yJTwc";
    private static String token2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwd2QiOiIxMjM0NTYiLCJ1c2VybmFtZSI6Imphc29uIn0.nHt-sZAxQElEgdoR-VabrCh8gDSHrXq66ov7g5yJTwc2";
    public static void main(String[] args) {
//        System.out.println(sign("jason","123456"));
//        System.out.println(verify(token2));
        System.out.println(getMsgFromToken(token,"username"));
        System.out.println(getMsgFromToken(token,"pwd"));
        System.out.println(getMsgFromToken(token,"password"));
    }

    private static String sign(String username,String pwd){
        try{
            Date date  = new Date(System.currentTimeMillis()+10000);
            Algorithm algo  = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String,Object> header = new HashMap<String,Object>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create().withHeader(header).withClaim("username",username).withClaim("pwd",pwd).sign(algo);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private static String verify(String token){
        try{
            Algorithm algo = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier  = JWT.require(algo).build();
            DecodedJWT jwt = verifier.verify(token);
            return "true";
        }catch (Exception e){
            e.printStackTrace();;
            return "false";
        }

    }

    private static String getMsgFromToken(String token,String msg){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(msg).asString();
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
