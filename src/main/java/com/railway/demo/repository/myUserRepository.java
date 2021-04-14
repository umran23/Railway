package com.railway.demo.repository;

import com.railway.demo.model.Train;

import java.util.UUID;

public interface myUserRepository {
    int insertUser(UUID id, Train user);

}
