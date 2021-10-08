package org.eunu.board.user.repository;

import java.util.List;
import java.util.Optional;
import org.eunu.board.model.User;

public interface UsersRepository {
    Optional<User> findById(Integer userId);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    Optional<User> insert(User user);

    Optional<User> update(User user);

}
