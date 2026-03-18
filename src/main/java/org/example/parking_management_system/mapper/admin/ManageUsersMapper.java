package org.example.parking_management_system.mapper.admin;

import org.example.parking_management_system.entity.User;

import java.util.Arrays;
import java.util.List;

public interface ManageUsersMapper {
    List<User> findByIds(List<Long> userIds);

    User findById(Long userId);
}
