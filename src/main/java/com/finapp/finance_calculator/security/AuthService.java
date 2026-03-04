//package com.finapp.finance_calculator.security;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.finapp.finance_calculator.dto.LoginRequestDTO;
//import com.finapp.finance_calculator.dto.LoginResponseDTO;
//import com.finapp.finance_calculator.repo.UserRepository;
//import com.finapp.finance_calculator.vo.User;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//
//public class AuthService {
//
//	private final UserRepository userRepository = null;
//	private final PasswordEncoder passwordEncoder = null;
//	private final JwtUtil jwtUtil = new JwtUtil();
//
////	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
////		super();
////		this.userRepository = userRepository;
////		this.passwordEncoder = passwordEncoder;
////		this.jwtUtil = jwtUtil;
////	}
//
//	public LoginResponseDTO login(LoginRequestDTO request) {
////		System.out.println(passwordEncoder.encode("Password@123"));
//		User user = userRepository.findByEmail(request.getEmail())
//				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
//
//		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//			throw new InvalidCredentialsException("Invalid email or password");
//		}
//
//		if (!Boolean.TRUE.equals(user.getIsActive())) {
//			throw new RuntimeException("User account is deactivated");
//		}
//
//		String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
//		LoginResponseDTO loginResponseDTO= new LoginResponseDTO();
//		loginResponseDTO.setEmail(user.getEmail());
//		loginResponseDTO.setToken(token);
//		loginResponseDTO.setRole(user.getRole());
//		return loginResponseDTO;
//
//	}
//
//}