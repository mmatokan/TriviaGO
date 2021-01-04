package hr.fer.ruazosa.networkquiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where u.username = ?1 and u.password = ?2")
    List<User> findByUserNameAndPassword(String userName, String password);

    @Query("SELECT u FROM User u where u.username = ?1")
    List<User> findByUserName(String userName);

    @Query(value = "SELECT rank FROM (SELECT username, RANK() OVER(ORDER BY score DESC) AS rank FROM Users) " +
            "WHERE username = ?1", nativeQuery = true)
    Integer getUserRank(String username);

    @Query(value = "SELECT username FROM Users WHERE username <> ?1 ", nativeQuery = true)
    List<String> getAllUsernames(String usernameToExclude);
}
