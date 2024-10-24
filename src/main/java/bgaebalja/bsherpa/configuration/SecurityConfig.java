package bgaebalja.bsherpa.configuration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import bgaebalja.bsherpa.security.filter.JwtCheckFilter;
import bgaebalja.bsherpa.security.handler.CustomAccessDeniedHandler;
import bgaebalja.bsherpa.security.handler.LoginFailureHandler;
import bgaebalja.bsherpa.security.handler.LoginSuccessHandler;
import bgaebalja.bsherpa.util.JwtUtil;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtUtil jwtUtil;
  private final Environment env;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .csrf().disable()
        .formLogin()
        .loginPage("/users/login")
        .successHandler(new LoginSuccessHandler(jwtUtil, env))
        .failureHandler(new LoginFailureHandler())
        .and()
        .addFilterBefore(new JwtCheckFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(new CustomAccessDeniedHandler());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("https://bsherpa.com","http://localhost:5173"));
    configuration.setAllowedMethods(Arrays.asList(GET.name(), POST.name(), PUT.name(),
        PATCH.name(), DELETE.name(), OPTIONS.name()));
    configuration.setAllowedHeaders(List.of(AUTHORIZATION, CACHE_CONTROL, CONTENT_TYPE));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
