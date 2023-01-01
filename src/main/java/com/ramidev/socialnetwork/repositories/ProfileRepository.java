package com.ramidev.socialnetwork.repositories;

import com.ramidev.socialnetwork.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id);
    Optional<Profile> findByUserId(Long id);
}
