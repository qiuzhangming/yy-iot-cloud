package cn.zzdz.security.config;

import cn.zzdz.security.shiro.CustomSessionManager;
import cn.zzdz.security.shiro.UserRealm;
import lombok.Data;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "spring.redis")
public class ShiroConfig {

    private String host;
    private int port;
    private String password;
    private int database;

    @Bean
    public Realm realm() {
        return new UserRealm();
    }
    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();

        //哪些请求可以匿名访问 anon:开放权限，可以理解为匿名用户或游客
        chain.addPathDefinition("/captcha.jpg", "anon");
        chain.addPathDefinition("/sys/login", "anon");
        chain.addPathDefinition("/sys/logout", "anon");
        chain.addPathDefinition("/page/401", "anon");
        chain.addPathDefinition("/page/403", "anon");
        chain.addPathDefinition("/page/index", "anon");
        chain.addPathDefinition("/autherror","anon");

        //swagger
        chain.addPathDefinition("/doc.html", "anon");
        chain.addPathDefinition("/api-docs.html", "anon");
        chain.addPathDefinition("/docs.html", "anon");

        chain.addPathDefinition("/swagger/**", "anon");
        chain.addPathDefinition("/v2/api-docs", "anon");
        chain.addPathDefinition("/swagger-ui.html", "anon");
        chain.addPathDefinition("/webjars/**", "anon");
        chain.addPathDefinition("/swagger-resources/**", "anon");


        chain.addPathDefinition("/sys/**", "anon");


        //除了以上的请求外，其它请求都需要登录。authc: 无参，需要认证
        chain.addPathDefinition("/**", "authc");
        return chain;
    }

    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        //redisManager.setPassword(password);
        redisManager.setDatabase(database);
        return redisManager;
    }

    /**
     * doGetAuthorizationInfo执行两次的解决方法
     * 对象需要id属性
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        // expire time in seconds
        //redisSessionDAO.setExpire(60*10);
        return redisSessionDAO;
    }


    @Bean
    public DefaultSessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }

    /**
     * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug，但是会造成验证2次
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        //defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
