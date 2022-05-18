package wx.miniprogram.todoList.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import wx.miniprogram.todoList.security.handler.*;
import wx.miniprogram.todoList.security.handler.CustomizeAccessDeniedHandler;
import wx.miniprogram.todoList.security.handler.CustomizeAuthenticationEntryPoint;
import wx.miniprogram.todoList.security.validate.ThirdLoginAuthenticationSecurityConfig;

/**
 * Spring Security 核心配置类
 *
 * @author jitwxs
 * @since 2019/1/8 23:28
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ThirdLoginAuthenticationSecurityConfig thirdLoginAuthenticationSecurityConfig;
    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;
    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 配置登录方式为微信登录
                .apply(thirdLoginAuthenticationSecurityConfig);
        http
                // 如果有允许匿名的url，填在下面
                .authorizeRequests().antMatchers(
                "/webLogin","/v2/api-docs","/swagger-resources/configuration/ui","/crowdsources/user","/wxLogin",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 权限异常处理
                .exceptionHandling()
                // 登录了但没有权限
                    .accessDeniedHandler(customizeAccessDeniedHandler)
                // 未登录或者登录失效等
                    .authenticationEntryPoint(customizeAuthenticationEntryPoint)
                .and()
                //跨域问题
                .cors()
                .and().csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/assets/**");
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //此处可添加别的规则,目前只设置 允许双 //
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
