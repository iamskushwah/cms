//package com.cms.user.service.impl;
//
//import com.cms.user.entity.OTPVerification;
//import com.cms.user.repository.UserRepository;
//import com.cms.user.entity.User;
//import com.cms.user.repository.OTPVerificationRepository;
//import com.cms.user.request.RegisterUserRequest;
//import com.cms.user.request.ResetPasswordRequest;
//import com.cms.user.request.VarifyOTPRequest;
//import com.cms.user.service.IUserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class UserService implements IUserService {
//
//    Logger logger = LoggerFactory.getLogger(UserService.class);
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OTPVerificationRepository otpVarificationRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public String registerUser(RegisterUserRequest newUser) throws IOException {
//        try{
//            Optional<User> userExist = userRepository.findByEmail(newUser.getEmail());
//            if(userExist.isPresent()){
//                throw new IOException("User already exist");
//            }
//
//            User user = new User();
//            user.setFirstName(newUser.getFirstName());
//            user.setLastName(newUser.getLastName());
//            user.setEmail(newUser.getEmail());
//            user.setPhoneNumber(newUser.getPhoneNumber());
//            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
//            user.setRole("ADMIN");
//            userRepository.save(user);
//            logger.info("User registered Successfully");
//
////          SEND MAIL TO PROVIDED EMAIL ADDRESS
//            String otp = verificationCodeSender(newUser.getEmail());
//            logger.info("Verification Code has been sent successfully...");
//
////          CALL SAVE OPT METHOD
//            saveOTP(otp,user.getId());
//            logger.info("Verification code has been sent succesfully...");
//
//            return "User registered successfully";
//        }
//        catch (IOException e){
//            throw e;
//        }
//
//    }
//
//    @Override
//    public String varifyOtp(VarifyOTPRequest request) throws IOException {
//        try {
//            OTPVerification otpVarification = otpVarificationRepository.findByUserId(request.getUserId());
//            if (ObjectUtils.isEmpty(otpVarification)) {
//                throw new IOException("User id is invalid...");
//            }
//            if (otpVarification.getOtp().equals(request.getOtp())) {
//                Optional<User> user = userRepository.findById(request.getUserId());
//                if (ObjectUtils.isEmpty(user)) {
//                    throw new IOException("User id id invalid...");
//                } else {
//                    user.get().setEnabled(true);
//                    userRepository.save(user.get());
//                }
//                return "OTP Varify Successfully";
//            }
//            else{
//                throw new IOException("OTP is incorrect...");
//            }
//        }
//        catch (IOException e){
//            throw e;
//        }
//
//    }
//
//    @Override
//    public String resendOtp(String email, Long userId) {
//        try {
//            String otp = verificationCodeSender(email);
//            saveOTP(otp,userId);
//            return "Verification Code has been sent successfully...";
//        }catch (Exception e){
//            logger.error("Unable to send otp!");
//            throw e;
//        }
//    }
//
//    @Override
//    public String resetPassword(ResetPasswordRequest request) throws IOException {
//
//        Optional<User> user = userRepository.findByEmail(request.getEmail());
//        if(ObjectUtils.isEmpty(user)){
//            throw new IOException("Email is invalid...");
//        }
//        if(passwordEncoder.matches(request.getOldPassword(), user.get().getPassword())){
//            user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
//            userRepository.save(user.get());
//            return "Password has been changed...";
//        }else {
//            logger.error("Current password is not correct...");
//            throw new IOException("Current password is not correct...");
//        }
//
//    }
//
//
//    // SEND METHOD TO SEND VERIFICATION CODE TO PROVIDED EMAIL ADDRESS
//    private String verificationCodeSender(String email){
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            Random random = new Random();
//            String otp = String.format("%04d", random.nextInt(10000));
//            logger.info("OTP is : " + otp);
//            String text = "Email Varification OTP is : " + otp;
//            message.setFrom("sandeep001kushwah@gmail.com");
//            message.setTo(email);
//            message.setSubject("Verification Code");
//            message.setText(text);
//            mailSender.send(message);
//            return otp;
//        }catch (Exception e){
//            logger.error("Unable to send otp on provided email address...");
//            throw e;
//        }
//    }
//
////    SAVE OPT INTO DATABASE
//    private void saveOTP(String otp,Long userId){
//        OTPVerification token = new OTPVerification();
//        token.setOtp(otp);
//        token.setUserId(userId);
//        token.setExpirationTime(new Date());
//        otpVarificationRepository.save(token);
//        logger.info("Token save successfully...");
//    }
//
//}
