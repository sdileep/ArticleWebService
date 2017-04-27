package com.fairfax.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairfax.entity.Article;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom{
	private static Logger logger = LoggerFactory.getLogger(ArticleRepositoryImpl.class.getName());

	@PersistenceContext 
	private EntityManager entityManager;
	
	/**
	 * Configure the entity manager to be used.
	 * 
	 * @param em the {@link EntityManager} to set.
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Custom query to retrieve only specified number of latest records by tag name and date
	 */
	public List<Article> findLastestArticlesByTagNameAndDate(String tagName, Date date, int count) {
		logger.info("Inside findLastestArticlesByTagNameAndDate");
		
		logger.debug("About to query entityManager for articles with given date and tagName");
		
		List<Article> articles = entityManager
				.createQuery("SELECT a FROM Article a JOIN a.tags t WHERE t = :tagName and a.date = :date ORDER BY a.lastModified DESC")
			    .setParameter("tagName", tagName)
			    .setParameter("date", date)
			    .setMaxResults(count)
			    .getResultList();
		
		logger.info("About to return from findLastestArticlesByTagNameAndDate");
		
		return articles;
	}
}
