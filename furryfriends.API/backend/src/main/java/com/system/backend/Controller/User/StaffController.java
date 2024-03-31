package com.system.backend.Controller.User;

import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Dto.User.UserUpdateRequest;
import com.system.backend.Entity.PostManagement;
import com.system.backend.Entity.User;
import com.system.backend.Repository.ProductRepository;
import com.system.backend.Service.*;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT+Link.USER.STAFF)
public class StaffController { //member(view users(getUsers), delete, update),product, post
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostManagementService postManagementService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PetService petService;
    @Autowired
    private ClearUserService clearUserService;

    //get one user
    @GetMapping(Link.USER.STAFFCRUD.GET)
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer user_id) {
        UserResponse user = userService.getMemberById(user_id);
        return ResponseEntity.ok(user);
    }

    //get allUsers
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllMember() {
        List<UserResponse> list = userService.getAllMember();
        return ResponseEntity.ok(list);
    }

    //update detail
    @PutMapping(Link.USER.STAFFCRUD.PUT)
    public String updateDetailUser(@PathVariable Integer user_id,
                                   @RequestBody UserUpdateRequest userUpdateDTO) {
        String message = userService.updateMemberById(user_id, userUpdateDTO);
        return message;
    }

    //dellete
    @DeleteMapping(Link.USER.STAFFCRUD.DELETE)
    public String deleteUser(@PathVariable Integer user_id) {
        String message = "";
        // co bai post, xoa post trc xoa user
        List<PostManagement> postManagement = postManagementService.getPostManagementByUser_id(user_id);
        if(postManagement != null){
            UserResponse user = userService.getMemberById(user_id);
            if(user.getAccount() != null){
                String mess = "";
                tokenService.deleteTokenByUser_id(user_id);
                clearUserService.clearDataFromUser(user.getAccount());
                for(PostManagement postManagement1: postManagement){
                    mess = postService.deleteDataByStaff(postManagement1.getPostDetail().getPost_id());
                }
                message = userService.deleteMemberById(user_id);
            } else{
                message = "account loi";
            }
        } else {
            message = userService.deleteMemberById(user_id);
        }
        return message;
    }


    @DeleteMapping(Link.USER.STAFFCRUD.DELETEPOST)
    public String deletePostByStaff(@PathVariable Integer post_id) {
        String message = "";
        message = userService.deletePostByStaff(post_id);
        return message;
    }
    @DeleteMapping(Link.USER.STAFFCRUD.DELETEPRODUCT)
    public String deleteProductByStaff(@PathVariable Integer product_id) {
        String message = "";
        message = userService.deleteProductByStaff(product_id);
        return message;
    }
    @DeleteMapping(Link.USER.STAFFCRUD.DELETEPET)
    public String deletePetByStaff(@PathVariable Integer pet_id) {
        String message = "";
        message = userService.deletePetByStaff(pet_id);
        return message;
    }

}
