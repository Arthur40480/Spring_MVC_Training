package fr.ldnr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.Article;

import java.awt.print.Pageable;

public interface ArticleRepository extends JpaRepository<Article, Long>{
    Page<Article> findByDescriptionContains(String description, Pageable pageable);
}
