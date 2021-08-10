package com.baiye.redscarf.common.util;

import com.baiye.redscarf.common.enums.ResultCodeEnum;
import com.baiye.redscarf.common.result.Result;
import io.jsonwebtoken.*;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baiye
 * @since 2021/8/10 10:23 上午
 **/
public class RSAUtils {

    public static String createJwt(String id) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
        String key = readResourceKey("rsa_private_key.pem");
        byte[] keyBytes;
        try {
            keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setIssuer("marco").setSubject("token").signWith(signatureAlgorithm, privateKey);
            builder.claim("id", id);
            return builder.compact();
        } catch (Exception e) {
            throw Result.toBizException(ResultCodeEnum.SERVER_UNUSERFUL);
        }
    }

    public static Claims verifyJwt(String jwt) {
        Claims claims;
        try {
            String key = readResourceKey("rsa_public_key.pem");   // 生成签名公钥
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            throw Result.toBizException(ResultCodeEnum.LOGIN_ILLEGAL);
        }
        return claims;
    }

    public static String readResourceKey(String fileName) {
        String key = null;
        try {
            Resource resource = new ClassPathResource(fileName);
            File file = resource.getFile();
            List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
            lines = lines.subList(1, lines.size() - 1);
            key = String.join("", lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void main(String[] args) {
        String rsa_private_key = readResourceKey("rsa_private_key.pem");
        System.out.println(rsa_private_key);
        String jwt = createJwt("1");
        System.out.println(jwt);
        Claims claims = verifyJwt("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJtYXJjbyIsInN1YiI6InRva2VuIiwiaWQiOiIxIn0.BUfrTpvnoob33fg4pdD1NpQGF7of8aFYovl407ZFaMCl63UqbH3HLyGhHl-t3_lU_D1c1C1Gd-4IHHDN2j5rkOI694QjMsPdhnuhiALPXp3NmzsSPxpeknL30965fH4v0ajZPk9JFGbiQNOYkZaeUT-xdmxk2vNr7-9kY3nsG5");
        System.out.println(claims);
    }
}
