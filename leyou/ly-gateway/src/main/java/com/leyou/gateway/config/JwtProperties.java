package com.leyou.gateway.config;

import com.leyou.auth.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@Data
@ConfigurationProperties(prefix = "ly.jwt")
public class JwtProperties {
    private String pubKeyPath;

    private String cookieName;

    private PublicKey publicKey; // 公钥

    // 对象一旦实例化后，就应该读取公钥和私钥到内存当中
    @PostConstruct         //此方法在构造函数执行完之后 执行
    public void init() throws Exception {
        // 读取公钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    }
}
