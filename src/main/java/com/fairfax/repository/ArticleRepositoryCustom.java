package com.fairfax.repository;

import java.util.Date;
import java.util.List;

import com.fairfax.entity.Article;

public interface ArticleRepositoryCustom {
	/**
	 * Custom query to retrieve only specified number of latest records by tag name and date
	 */
	List<Article> findLastestArticlesByTagNameAndDate(String tagName, Date date, int count);
}
