package com.demo.repository;

import com.demo.model.Song;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByActiveTrue();
    Optional<Song> findByIdAndActiveTrue(Long id);

    @Query("""
        select s from Song 
        where (:duration IS NULL OR s.duration > 0)
        and (:title IS NULL OR :title = '' OR LOWER(r.name) LIKE LOWER(CONCAT('%', :title, '%')))
        and (:artist IS NULL OR :title = '' OR LOWER(r.name) LIKE LOWER(CONCAT('%', :title, '%')))
        """)
    List<Song> findActiveFiltering(
            @Param("title") String title,
            @Param("artist") String artist,
            @Param("duration") Integer duration
    );
}