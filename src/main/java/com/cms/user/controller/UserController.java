//package com.cms.user.controller;
//
//import com.cms.user.request.RegisterUserRequest;
//import com.cms.user.request.ResetPasswordRequest;
//import com.cms.user.request.VarifyOTPRequest;
//import com.cms.user.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private IUserService service;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequest newUserRequest) throws IOException {
//        return new ResponseEntity<>(service.registerUser(newUserRequest), HttpStatus.OK);
//    }
//
//    @PostMapping("/varify_otp")
//    public ResponseEntity<String> varifyOtp(@RequestBody VarifyOTPRequest request) throws IOException {
//        return new ResponseEntity<>(service.varifyOtp(request),HttpStatus.OK);
//    }
//
//    @PostMapping("/resend_otp")
//    public ResponseEntity<String> resendOtp(@RequestParam String email,
//                                            @RequestParam Long userId) throws IOException {
//        return new ResponseEntity<>(service.resendOtp(email,userId),HttpStatus.OK);
//    }
//
//    @PostMapping("/reset_password")
//    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) throws IOException {
//        return new ResponseEntity<>(service.resetPassword(request),HttpStatus.OK);
//    }
//
//}
