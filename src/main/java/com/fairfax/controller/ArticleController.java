package com.fairfax.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fairfax.dto.TagResult;
import com.fairfax.entity.Article;
import com.fairfax.exception.ResourceNotFoundException;
import com.fairfax.service.ArticleService;

/**
 * The class handles all requests to Article Service.
 * 
 * @author dileep
 *
 */
@RestController
public class ArticleController {
	
	private static Logger logger = LoggerFactory.getLogger(ArticleController.class.getName());

	@Autowired
	private ArticleService articleService;

	private DateFormat requestDateFormat = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * Post method to accept article data in JSON format
	 * 
	 * @param anArticle
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	@ResponseBody
	public Article saveArticle(@RequestBody Article anArticle) {
		logger.info("Inside saveArticle");
		
		Article savedArticle = null;

		logger.debug("About to invoke from articleService.saveArticle");
		
		savedArticle = articleService.saveArticle(anArticle);

		logger.info("About to return from saveArticle");
		
		return savedArticle;
	}

	/**
	 * GET method to retrieve specific article
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping("/articles/{id}")
	@ResponseBody
	public Article findArticle(@PathVariable("id") String id) throws ResourceNotFoundException {
		logger.info("Inside findArticle");
		
		Article article = null;

		logger.debug("About to invoke from articleService.findArticle");
		
		article = articleService.findArticle(id);
		
		logger.info("About to return from findArticle");

		return article;
	}

	/**
	 * Method to find articles based on tag name and date
	 * 
	 * @param tagName
	 * @param requestDateString
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping("/tags/{tagName}/{date}")
	@ResponseBody
	public TagResult findArticlesByTag(@PathVariable("tagName") String tagName,
			@PathVariable("date") String requestDateString) throws ResourceNotFoundException {
		logger.info("Inside findArticlesByTag");
		
		Date date = null;
		String errorMessage = null;
		
		TagResult tagResult = null;

		try {
			logger.debug("About to parse string to date");
			
			//Parse date from yyyyMMdd format
			date = requestDateFormat.parse(requestDateString);
		} catch (ParseException e) {
			//collect error message if there is an exception parsing the date
			errorMessage = e.getMessage();
		}
        
        if (date == null) {
        	logger.error("Date parse failed!");
        	
        	//date would only be null if the requestDateString is not in appropriate format
        	throw new IllegalArgumentException(errorMessage);
        } else {
        	logger.debug("About to invoke from articleService.findArticlesByTagAndDate");
        	
        	tagResult = articleService.findArticlesByTagAndDate(tagName, date);
        }
        
        logger.info("About to return from findArticlesByTag");

		return tagResult;
	}

}
