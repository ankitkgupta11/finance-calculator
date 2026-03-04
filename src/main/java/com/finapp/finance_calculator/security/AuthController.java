//package com.finapp.finance_calculator.security;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.finapp.finance_calculator.dto.LoginRequestDTO;
//import com.finapp.finance_calculator.dto.LoginResponseDTO;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//
//    public AuthController(AuthService authService) {
//        this.authService=authService;
//    }
//    
//
//    /**
//     * @param request
//     * @return
//     */
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
//        return ResponseEntity.ok(authService.login(request));
//    }
//}
// 
