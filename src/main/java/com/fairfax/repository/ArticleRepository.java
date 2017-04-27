package com.fairfax.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fairfax.entity.Article;

/**
 * Persistence layer for the Article Entity
 * @author dileep
 *
 */
public interface ArticleRepository extends CrudRepository<Article, String>, ArticleRepositoryCustom {
    
	/**
	 * method to find article by identifier
	 * @param id
	 * @return
	 */
	List<Article> findById(String id);
    
//    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t = :tagName and a.date = :date")
//    List<Article> findByTagAndDate(@Param("tagName") String tagName, @Param("date") Date date);
    
    /**
     * Method to find article by date
     * @param date
     * @return
     */
    List<Article> findByDate(@Param("date") Date date);
    
//    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t = :tagName")
//    List<Article> findByTag(@Param("tagName") String tagName);
}
