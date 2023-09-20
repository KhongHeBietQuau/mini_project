package com.ms.tourist_app.application.dai;

import com.ms.tourist_app.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Query("select u from User u " +
            "where upper(u.name) like upper(concat('%', ?1, '%'))or upper(u.email) like upper(concat('%', ?1, '%'))")
    List<User> search(String keyword, Pageable pageable);



//    @Modifying
//    @Query("Update User u set u.favoriteDestination = :favoriteDest where u.id = :idUser")
//    void updateFavoriteDestination(@Param("idUser") Long userId,
//                                  @Param("favoriteDest") List<Destination> favoriteDest);
}
