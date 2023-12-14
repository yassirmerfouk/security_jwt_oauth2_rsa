package com.ym.security_jwt_oauth2_rsa;

import com.ym.security_jwt_oauth2_rsa.model.AppRole;
import com.ym.security_jwt_oauth2_rsa.model.AppUser;
import com.ym.security_jwt_oauth2_rsa.repository.AppRoleRepository;
import com.ym.security_jwt_oauth2_rsa.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityJwtOauth2RsaApplication {

	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtOauth2RsaApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			AppUserRepository appUserRepository,
			AppRoleRepository appRoleRepository
	){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				AppRole role1 = appRoleRepository.save(new AppRole(null, "ADMIN"));
				AppRole role2 = appRoleRepository.save(new AppRole(null, "USER"));

				AppUser user1 = appUserRepository.save(new AppUser(null, "admin", passwordEncoder.encode("123456"), List.of(role1)));
				AppUser user2 = appUserRepository.save(new AppUser(null, "user", passwordEncoder.encode("123456"), List.of(role2)));
			}
		};
	}
}
