package br.ufs.hu.prevsep.web.api.config.security;

import br.ufs.hu.prevsep.web.api.config.security.filter.OAuth2JWTAuthenticationFilter;
import br.ufs.hu.prevsep.web.api.config.security.filter.OAuth2JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] SWAGGER_AUTH_WHITELIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/swagger-config",
            "/favicon.ico"
    };

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationEntryPoint entryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AccessDecisionManager accessDecisionManager;
    private final Long expirationTime;
    private final String jwtSecret;
    private final String tokenUrl;

    public WebSecurityConfig(UserDetailsServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder,
                             AuthenticationEntryPoint entryPoint, AccessDeniedHandler accessDeniedHandler,
                             AccessDecisionManager accessDecisionManager,
                             @Value("${prevsep.security.oauth.token.expires}") Long expirationTime,
                             @Value("${prevsep.security.oauth.token.secret}") String jwtSecret,
                             @Value("${prevsep.security.oauth.token.url}") String tokenUrl) {
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.entryPoint = entryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.accessDecisionManager = accessDecisionManager;
        this.expirationTime = expirationTime;
        this.jwtSecret = jwtSecret;
        this.tokenUrl = tokenUrl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .accessDecisionManager(accessDecisionManager)
                .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
                .antMatchers(tokenUrl).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new OAuth2JWTAuthenticationFilter(authenticationManager(), tokenUrl, expirationTime, jwtSecret))
                .addFilter(new OAuth2JWTAuthorizationFilter(authenticationManager(), entryPoint, jwtSecret))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(entryPoint);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}