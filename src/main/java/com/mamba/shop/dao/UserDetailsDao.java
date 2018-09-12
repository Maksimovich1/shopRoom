package com.mamba.shop.dao;

import com.mamba.shop.entity.User;

public interface UserDetailsDao {
    User findUserByUsername(String username);
}
