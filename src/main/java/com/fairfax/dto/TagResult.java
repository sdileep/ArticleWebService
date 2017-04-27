package com.fairfax.dto;

import java.util.Set;

/**
 * Datastructure to hold results of looking up articles by tag name and date
 * @author dileep
 *
 */
public class TagResult {

	private String tag;
	private int count;
	private Set<String> articles;
	private Set<String> related_tags;
	
	public TagResult() {};
	
	public TagResult (String tag, int count, Set<String> articles, Set<String> related_tags) {
		this.tag = tag;
		this.count = count;
		this.articles = articles;
		this.related_tags = related_tags;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Set<String> getArticles() {
		return articles;
	}
	public void setArticles(Set<String> articles) {
		this.articles = articles;
	}
	public Set<String> getRelated_tags() {
		return related_tags;
	}
	public void setRelated_tags(Set<String> related_tags) {
		this.related_tags = related_tags;
	}
	
	
}
