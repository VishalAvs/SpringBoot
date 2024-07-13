package com.example.user.repo;

import com.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO users (email, password) VALUES (?, ?)",
                user.getEmail(), user.getPassword());
    }

    public User findByEmail(String email) {
        @SuppressWarnings("deprecation")
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email = ?",
                new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? null : users.get(0);
    }
}