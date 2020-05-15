package com.spring.cloud.user.service;

import com.spring.cloud.user.Model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, User> userMap = new HashMap<>();


    public void addUser(final User person) {
        this.userMap.put(person.getEmail(),
                person);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(this.userMap.values());
    }

    public User getUser(final String emailParam) {
        return this.userMap.get(emailParam);

}
}

