package com.system.backend.Controller.Dashboard;

import com.system.backend.Dto.User.UserResponse;
import com.system.backend.Service.UserService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.STATISTIC)
public class DashboardForUser {
    @Autowired
    private UserService userService;
    @GetMapping(Link.USER.STATISTICCRUD.STATISTICUSER)
    public Integer getAllMember()
    {
        List<UserResponse> list = userService.getAllMember();
        return list.size();
    }
}
