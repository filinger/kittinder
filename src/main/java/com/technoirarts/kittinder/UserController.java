package com.technoirarts.kittinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Random RAND = new Random();

    @Resource
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/like")
    public void likeUser(@RequestParam Long userId, @RequestParam Long originUserId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            user.getLikes().add(originUserId);
            userRepository.save(user);
        }
    }

    @ResponseBody
    @RequestMapping("/dislike")
    public void dislikeUser(@RequestParam Long userId, @RequestParam Long originUserId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            user.getDislikes().add(originUserId);
            userRepository.save(user);
        }
    }

    @ResponseBody
    @RequestMapping("/candidate")
    public User getCandidate(@RequestParam Long originUserId) {
        User user = getRandomUser();
        if (!user.getLikes().contains(originUserId) && !user.getDislikes().contains(originUserId)) {
            return user;
        }
        return getCandidate(originUserId);
    }

    @ResponseBody
    @RequestMapping("/find")
    public User findUser(@RequestParam Long id) {
        return userRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/count")
    public Long getUserCount() {
        return userRepository.count();
    }

    @ResponseBody
    @RequestMapping("/random")
    public User getRandomUser() {
        return userRepository.findOne((long) RAND.nextInt((int) userRepository.count()));
    }
}
