package com.recykal.ticketer.repository;

import com.recykal.ticketer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);

//    Users findUserById(Users id);
    @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery = true)
    Users findByEmailAndPasswordAndActions(@Param("email") String email, @Param("password") String password);
    Optional<Users> findUsersById(long id);

    @Query(value = "SELECT COUNT(CASE WHEN u.actions = true THEN 1 END) AS Active_Count, COUNT(CASE WHEN u.actions = false THEN 1 END) AS Inactive_Count FROM users u", nativeQuery = true)
    Map<String, Long> getCountByActions();

@Query(value = "SELECT * FROM users u WHERE TRIM(u.dept) LIKE %:dept%", nativeQuery = true)
    List<Users> findByDeptContaining(@Param("dept") String dept);
}
