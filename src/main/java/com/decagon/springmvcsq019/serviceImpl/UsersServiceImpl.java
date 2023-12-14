package com.decagon.springmvcsq019.serviceImpl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.decagon.springmvcsq019.dtos.PasswordDTO;
import com.decagon.springmvcsq019.models.Users;
import com.decagon.springmvcsq019.repositories.UsersRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.function.Function;

@Service
public class UsersServiceImpl {

    private UsersRepositories usersRepositories;

    @Autowired
    public UsersServiceImpl(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }

    public Function<String, Users> findUsersByUsername = (username)->
        usersRepositories.findByUsername(username)
                .orElseThrow(()->new NullPointerException("User not found with username: "+username));
    public Function<Long, Users> findUsersById = (id)->
            usersRepositories.findById(id)
                    .orElseThrow(()->new NullPointerException("User not found with ID: "+id));

    public Function<Users, Users> saveUser = (user)->usersRepositories.save(user);

    public Function<PasswordDTO, Boolean> verifyUserPassword = passwordDTO -> BCrypt.verifyer()
            .verify(passwordDTO.getPassword().toCharArray(),
                    passwordDTO.getHashPassword().toCharArray())
            .verified;
}
