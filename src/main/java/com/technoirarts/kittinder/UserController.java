package com.technoirarts.kittinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Random RAND = new Random();

    @Resource
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/like")
    public GenericResponse likeUser(@RequestParam Long userId, @RequestParam Long originUserId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            user.getLikes().add(originUserId);
            userRepository.save(user);
        }
        return new GenericResponse("ok");
    }

    @ResponseBody
    @RequestMapping("/dislike")
    public GenericResponse dislikeUser(@RequestParam Long userId, @RequestParam Long originUserId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            user.getDislikes().add(originUserId);
            userRepository.save(user);
        }
        return new GenericResponse("ok");
    }

    @ResponseBody
    @RequestMapping("/candidate")
    public GenericResponse getCandidate(@RequestParam Long originUserId) {
        List<User> users = userRepository.findNotLikedOrDislikedBy(originUserId);
        if (users.isEmpty()) {
            return new GenericResponse("fail");
        }
        User candidate = getRandomItem(users);
        return new GenericResponse("ok", candidate);
    }

    @ResponseBody
    @RequestMapping("/find")
    public GenericResponse findUser(@RequestParam Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new GenericResponse("fail");
        }
        return new GenericResponse("ok", user);
    }

    @ResponseBody
    @RequestMapping("/count")
    public GenericResponse getUserCount() {
        return new GenericResponse("ok", userRepository.count());
    }

    private <T> T getRandomItem(List<T> items) {
        return items.get(RAND.nextInt(items.size()));
    }
}
