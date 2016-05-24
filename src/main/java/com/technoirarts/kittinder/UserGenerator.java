package com.technoirarts.kittinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UserGenerator {

    private static final String[] PREFIXES = {"Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk"};
    private static final String[] SUFFIXES = {"air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak"};
    private static final String[] POSTFIXES = {"d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur"};

    private static final Logger LOG = LoggerFactory.getLogger(UserGenerator.class);
    private static final Random RAND = new Random();

    @Autowired
    private UserRepository userRepository;

    @Value("${kittinder.generator.userAmount}")
    private Integer userAmount;
    @Value("${kittinder.generator.photoAmount}")
    private Integer photoAmount;

    @PostConstruct
    private void generateAndSave() throws IOException {
        LOG.info("Generating {} users...", userAmount);
        List<User> users = generateUsers();
        LOG.info("Saving to repository...");
        saveToRepository(users);
    }

    private List<User> generateUsers() {
        List<User> users = new ArrayList<>(userAmount);
        for (int i = 0; i < userAmount; ++i) {
            User user = generateRandomUser();
            user.setId((long) i);
            user.setPhotoId(i % photoAmount);
            users.add(user);
        }
        return users;
    }

    private void saveToRepository(List<User> users) {
        userRepository.deleteAll();
        userRepository.save(users);
    }

    private User generateRandomUser() {
        String name = getRandomName();
        String occupation = getRandomName() + " at " + getRandomName();
        return new User(null, null, name, occupation);
    }

    private String getRandomName() {
        return getRandomItem(PREFIXES)
                + getRandomItem(SUFFIXES)
                + getRandomItem(POSTFIXES);
    }

    private <T> T getRandomItem(T[] items) {
        return items[RAND.nextInt(items.length)];
    }

    private <T> T getRandomItem(List<T> items) {
        return items.get(RAND.nextInt(items.size()));
    }
}
