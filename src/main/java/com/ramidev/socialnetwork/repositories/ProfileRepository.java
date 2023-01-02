package com.ramidev.socialnetwork.repositories;

import com.ramidev.socialnetwork.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    boolean existsById(Long id);
    Optional<Profile> findById(Long id);
    boolean existsByUserId(Long id);
    Optional<Profile> findByUserId(Long id);
//    Optional<Profile> findByUserFirstname(String name); interesante por si quiero buscar por la relacion
}
