package com.system.backend.Service.Implement;
import com.system.backend.Dto.User.UserAuthRequestDTO;
import com.system.backend.Dto.User.UserRegisterRequestDTO;
import com.system.backend.Dto.User.UserUpdateRequestDTO;
import com.system.backend.Entity.Token;
import com.system.backend.Entity.TokenType;
import com.system.backend.Entity.User;
import com.system.backend.Repository.TokenRepository;
import com.system.backend.Repository.UserRepo;
import com.system.backend.Service.UserService;
import com.system.backend.payload.response.LoginMessage;
import com.system.backend.util.JwtUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplement implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public String createToken(UserAuthRequestDTO authRequest) throws Exception  {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getAccount(),
                            authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid account/password");
        }
        // get user ( sure co)
        User user = userRepo.findUserByAccount(authRequest.getAccount());
        System.out.println(user.getUser_id() + user.getAccount());
        //  create Token
        String jwtToken = jwtUtil.generateToken(authRequest.getAccount());
        System.out.println(jwtToken);

        // revoke các token khác của user đã đăng nhập trc đó
        revokeAllUserTokens(user);
        // save token
        saveUserToken(user, jwtToken);
        return jwtToken; // true-token
    }
    @Override
    public String addUser(UserRegisterRequestDTO userUpdateDTO) {
        User userExist = userRepo.findUserByAccount(userUpdateDTO.getAccount()); //
        if(userExist == null){ // add new
            userExist = new User(null,
                    userRepo.findByRoleId(userUpdateDTO.getRole_id()),
                    userUpdateDTO.getAccount(),
                    this.passwordEncoder.encode(userUpdateDTO.getPassword()),
                    userUpdateDTO.getEmail(),
                    userUpdateDTO.getFirst_name(),
                    userUpdateDTO.getLast_name(),
                    userUpdateDTO.getPhone(),
                    userUpdateDTO.getAddress(),
                    userUpdateDTO.getBirth(),
                    userUpdateDTO.getSex()
            );
            userRepo.save(userExist);
        } else{
            return "tai khoan da ton tai!";
        }
        System.out.println(userUpdateDTO.getFirst_name());
        System.out.println(this.passwordEncoder.encode(userUpdateDTO.getPassword()
        ));

        return userExist.getFirst_name();

    }
    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUser_id());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .user(user)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public LoginMessage loginUser(UserAuthRequestDTO loginDTO) {
        String msg = "";
        User user = userRepo.findUserByAccount(loginDTO.getAccount());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            //check pass from user and encoded Password system
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepo.findUserByAccountAndPassword(loginDTO.getAccount(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Account not exits", false);
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> list = userRepo.findAll();
        return list;
    }

    @Override
    public User getUser(Integer user_id) {
        User user = userRepo.findByUser_id(user_id);
        return user;
    }

    @Override
    public String deleteUser(Integer user_id) {
        String mess = "";
        User user = userRepo.findByUser_id(user_id);
        if(user != null){
            userRepo.delete(user);
            mess = "deleted";
        } else{
            mess = "not exist";
        }
        return mess;
    }

    @Override
    public String updateUser(Integer user_id, UserUpdateRequestDTO userUpdateDTO) {
        String mess = "";
        User user = userRepo.findByUser_id(user_id);
        // nếu như tồn tại cái cũ thì check và update cái mới userUpdateDTO
        if(user != null){
            mess = this.updateUserDetail(userUpdateDTO, user);
        } else{
            mess = "not exist";
        }
        return mess;
    }

    @Override
    public String checkUserPassword(String password, String account) {
        User user = userRepo.findUserByAccount(account);
        String mess ="";
        if(user!= null){
            List<User> list = this.getUsers();
            // check pass vs pass cua list
            for (User u: list) {
                if(passwordEncoder.matches(password,u.getPassword())){
                    mess = "password match";
                } else{
                    mess = "password not match";
                }
            }
        } else{
            mess = "Account not exist";
        }
        return mess;
    }

    @Override
    public String setUserPassword(String password, String account) {
        User user = userRepo.findUserByAccount(account);
        String mess ="";
        if(user!= null){
            String encodedPassword = user.getPassword();
            // check có trùng mật khẩu cũ không: khong, thi set
            if(!this.passwordEncoder.matches(password,encodedPassword)){
                user.setPassword(this.passwordEncoder.encode(password));
                userRepo.save(user);
                mess = "Set success!";
            } else{
                mess = "New password duplicate old password!";
            }
        } else{
            mess = "Account not exist";
        }
        return mess;
    }


    public String updateUserDetail(UserUpdateRequestDTO userUpdateDTO, User userOld) {
        userOld.setEmail(userUpdateDTO.getEmail());
        userOld.setAddress(userUpdateDTO.getAddress());
        userOld.setBirth(userUpdateDTO.getBirth());
        userOld.setFirst_name(userUpdateDTO.getFirst_name());
        userOld.setLast_name(userUpdateDTO.getLast_name());
        userOld.setPhone(userUpdateDTO.getPhone());
        userOld.setSex(userUpdateDTO.getSex());
        userRepo.save(userOld);
        return "updated: " + userOld.getAccount();

    }

}
