package com.proyecto.Zapateria;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proyecto.Zapateria.Servicios.MyUserDateilsService;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	@Autowired
	MyUserDateilsService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	String[] recur = new String[] {
			"/include/**","/imagenes/**","/img/**","/css/**","/js/**","/icons/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(recur).permitAll()
		.antMatchers("/Producto").access("hasAnyRole('ADMIN')")
		.antMatchers("/CrearProducto").access("hasAnyRole('ADMIN')")
		.antMatchers("/categorias").access("hasAnyRole('ADMIN')")
		.antMatchers("/registrar","/CrearRegistro","/compra").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/compra")
		.failureUrl("/login?error=true")
		.usernameParameter("username")
		.passwordParameter("password")
		.and()
		.logout().permitAll()
		.and() 
		.exceptionHandling().accessDeniedPage("/error_403");		
		http.csrf().disable();
		http.headers().contentTypeOptions();
		http.headers().frameOptions().sameOrigin();
		
	}

	//private HeadersConfigurer<HttpSecurity> headers() {
		// TODO Auto-generated method stub
		//return null;
	//}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userService)
		.passwordEncoder(passwordEncoder);
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/imagenes/**").addResourceLocations("file:/C:/imagenes/");
	}
}
