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

    @Query(value =  "SELECT " +
            "user_id, first_name, last_name, e_mail, username, password, token, score, games_played, accuracy," +
            " (SELECT rank FROM (SELECT username, RANK() OVER(ORDER BY score DESC) AS rank FROM Users) " +
            " WHERE username = ?1) as rank FROM users where username = ?1", nativeQuery = true)
    User getUserStats(String username);

    @Query(value = "SELECT * FROM Users WHERE username <> ?1 ", nativeQuery = true)
    List<User> getAllOpponents(String usernameToExclude);

    @Query(value = "SELECT token FROM Users WHERE username = ?1", nativeQuery = true)
    String getUserToken(String username);

    @Query(value= "UPDATE Users SET token = ?2 WHERE username = ?1", nativeQuery = true)
    String setNewToken(String username, String token);

    @Query(value = "SELECT * FROM Users ORDER BY score, username DESC", nativeQuery = true)
    List<User> getAllUsers();

}
