package wx.miniprogram.todoList.security.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class ThirdLoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private ThirdLoginUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ThirdLoginAuthenticationProvider thirdLoginAuthenticationProvider = new ThirdLoginAuthenticationProvider();
        thirdLoginAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(thirdLoginAuthenticationProvider);
    }
}
