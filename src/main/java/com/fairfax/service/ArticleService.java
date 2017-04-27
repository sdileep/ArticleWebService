package com.fairfax.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairfax.constants.ConfigConstants;
import com.fairfax.constants.ErrorConstants;
import com.fairfax.dto.TagResult;
import com.fairfax.entity.Article;
import com.fairfax.exception.ResourceNotFoundException;
import com.fairfax.repository.ArticleRepository;
import com.fairfax.utility.StringUtility;

/**
 * Service class for all functionalities related to articles. This class sits in between the request entry point and the data layer. It is
 * responsible for all business logic related to articles.
 * @author dileep
 *
 */
@Component
public class ArticleService {
	private static Logger logger = LoggerFactory.getLogger(ArticleService.class.getName());
	
    @Autowired
    private ArticleRepository articleRepository;
    
    /**
     * Service method to save article
     * @param anArticle
     * @return
     */
    public Article saveArticle (Article anArticle) {
    	logger.info("Inside saveArticle");
    	
    	Article savedArticle = null;
    	
    	if (anArticle == null || StringUtility.isStringEmpty(anArticle.getId())) {
    		logger.error("Id not present in the article to be persisted");
    		
    		//TODO: Generally the API should handle generation of id, 
    		// since the spec example had id in data, presuming that the id would come from outside
    		throw new IllegalArgumentException(ErrorConstants.ARTICLE_ID_SHOULDNT_BE_NULL);
    	}
    	
    	logger.debug("About to invoke articleRepository.save(anArticle)");
    	
    	//save the posted article to repository
    	savedArticle = articleRepository.save(anArticle);
    	
    	logger.info("About to return from saveArticle");
    	
        return savedArticle;
    }

    /**
     * Service method to find an article by its id
     * @param id
     * @return
     * @throws ResourceNotFoundException 
     */
    public Article findArticle (String id) throws ResourceNotFoundException {
    	logger.info("Inside findArticle");
    	
        Article anArticle = null;
        
        logger.debug("About to invoke articleRepository.findOne(id)");
        
        //find an article by its id
        anArticle = articleRepository.findOne(id);
        
        if (anArticle == null) {
        	logger.error("No article with given id found");
        	
        	//if an article is not found, throw a resource not found exception
        	throw new ResourceNotFoundException(ErrorConstants.NO_ARTICLES_FOUND);
        }
        
        logger.info("About to return from findArticle");
        
        return anArticle;
    }
    
    /**
     * Service method to find articles by tag name and given date
     * @param tagName
     * @param requestDateString
     * @return
     * @throws ResourceNotFoundException 
     */
    public TagResult findArticlesByTagAndDate (String tagName, Date date) throws ResourceNotFoundException {
    	logger.info("Inside findArticlesByTagAndDate");
    	
        List<Article> articles = null;
        
        logger.debug("About to invoke articleRepository.findLastestArticlesByTagNameAndDate");
        
    	//find all articles that match requested date and tagname
        //TODO: Ideally, ConfigConstants.MAX_NUMBER_OF_ARTICLES_FOR_TAG should come from mutable configuration
    	articles = articleRepository.findLastestArticlesByTagNameAndDate(tagName, date, ConfigConstants.MAX_NUMBER_OF_ARTICLES_FOR_TAG);
        
        TagResult tagResult = new TagResult();
        
        tagResult.setTag(tagName);
        
        if (articles == null || articles.size() == 0) {
        	logger.error("No articles for given tag name and date found!");
        	
        	//if no articles are found, throw a resource not found exception
        	throw new ResourceNotFoundException(ErrorConstants.NO_TAGS_FOUND);
        } else {
        	//total number of articles found
        	int numberOfArticles = articles.size(); 
        	
        	tagResult.setCount(numberOfArticles);
        	
        	if (numberOfArticles > 0) {
        		//Sets ensure that there are no duplicates
        		Set<String> articleIds = new HashSet<String>();
        		Set<String> relatedTags = new HashSet<String>();
        		
        		//For each returned article, gather their id and the related tags
        		articles
        			.parallelStream()
        			.forEach((article) -> {
        				articleIds.add(article.getId());
        				relatedTags.addAll(article.getTags());
        			});
        		
        		//set article ids and related tags to tag result
        		tagResult.setArticles(articleIds);
        		tagResult.setRelated_tags(relatedTags);
        	}
        	
        }
        
        logger.info("About to return from findArticlesByTagAndDate");
        
        return tagResult;
    }
}
