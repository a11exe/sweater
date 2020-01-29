package com.alllexe.sweater;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import com.alllexe.sweater.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.01.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@WithUserDetails("dru")
@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/messages-list-after.sql", "/create-user-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {

  @Autowired
  private MainController controller;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void mainPageTest() throws Exception {
    this.mockMvc.perform(get("/main"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("/html/body/nav/div[2]").string("dru"));

  }

  @Test
  public void messageListTest() throws Exception {
    this.mockMvc.perform(get("/main"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//div[@id='message-list']/div").nodeCount(4));

  }

  @Test
  public void filterMessageTest() throws Exception {
    this.mockMvc.perform(get("/main").param("filter", "my-tag"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//div[@id='message-list']/div").nodeCount(2));

  }

  @Test
  public void addMessageToListTest() throws Exception {
    MockHttpServletRequestBuilder multipart = multipart("/main")
        .file("file", "123".getBytes())
        .param("text", "fifth")
        .param("tag", "new one")
        .with(csrf());

    this.mockMvc.perform(multipart)
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//div[@id='message-list']/div").nodeCount(5))
        .andExpect(xpath("//div[@id='message-list']/div[@data-id=10]").exists())
        .andExpect(xpath("//div[@id='message-list']/div[@data-id=10]/div/span").string("fifth"))
        .andExpect(xpath("//div[@id='message-list']/div[@data-id=10]/div/i").string("#new one"));

  }

}
