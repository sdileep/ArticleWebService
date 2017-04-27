package com.fairfax.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    //TODO: Write more test cases!
    
    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
    	//TODO: Use stub to build json
    	String json = "{\"id\":\"1\",\"title\": \"latest science shows that potato chips are better for you than sugar\","
    			+ "\"date\":\"2016-09-22\",\"body\":\"some text, potentially containing simple markup about how potato chips are great\","
    			+ "\"tags\":[\"health\",\"fitness\",\"science\"]}";
    	
    	mockMvc.perform(post("/articles")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
    			.andDo(print());
    }
    
    /**
     * Test case to check if post persists data
     * @throws Exception
     */
    @Test
    public void postShouldPersistData() throws Exception {
    	//TODO: Use stub to build json
    	String json = "{\"id\":\"2\",\"title\": \"latest science shows that potato chips are better for you than sugar\","
    			+ "\"date\":\"2016-09-23\",\"body\":\"some text, potentially containing simple markup about how potato chips are great\","
    			+ "\"tags\":[\"health\",\"life\",\"balance\"]}";
    	
    	mockMvc.perform(post("/articles")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
    			.andDo(print())
    			.andExpect(status().isOk());
    }
    
    /**
     * Test case to check if that invalid post data responds with an error
     * @throws Exception
     */
    @Test
    public void postWithInvalidDataShouldNotPersistData() throws Exception {
    	//TODO: Use stub to build json
    	String json = "{\"title\": \"latest science shows that potato chips are better for you than sugar\","
    			+ "\"date\":\"2016-09-23\",\"body\":\"some text, potentially containing simple markup about how potato chips are great\","
    			+ "\"tags\":[\"health\",\"life\",\"balance\"]}";
    	
    	mockMvc.perform(post("/articles")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
    			.andDo(print())
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.errorCode").value("409"));
    }

    /**
     * Test case to check if get fetches data
     * @throws Exception
     */
    @Test ()
    public void paramArticleGetShouldFetchInsertedData() throws Exception {
        this.mockMvc.perform(get("/articles/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
    
    /**
     * Test case to check if get fetches data
     * @throws Exception
     */
    @Test ()
    public void paramArticleGetOnWrongIdShouldNotFetchData() throws Exception {
        this.mockMvc.perform(get("/articles/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value("404"));
    }
    
    /**
     * Test case to check if get fetches data
     * @throws Exception
     */
    @Test ()
    public void testRetrievalOfArticlesBasedOnTagNameAndDate() throws Exception {
        this.mockMvc.perform(get("/tags/health/20160922"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tag").value("health"))
                .andExpect(jsonPath("$.count").value("1"));
    }
    
    /**
     * Test case to check if get fetches data
     * @throws Exception
     */
    @Test ()
    public void testResourceNotFoundForNonExistingTag() throws Exception {
        this.mockMvc.perform(get("/tags/health2/20160922"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value("404"));
    }
}
