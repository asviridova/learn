package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.LibraryController;
import ru.otus.spring.service.*;

import java.util.Collection;
import java.util.Map;

//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;


//https://spring.io/guides/gs/testing-web/
//https://reflectoring.io/spring-boot-web-controller-test/

@WebMvcTest(controllers = LibraryController.class)
//@Import({BookServiceImpl.class, AuthorServiceImpl.class, GenreServiceImpl.class, CommentServiceImpl.class})
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;
   // @MockBean
   // private LibraryUseCase libraryUseCase;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void check(){
        try {
            mockMvc.perform(post("/add"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test!")));
    }


    @Test
    public void checkAdd(){
        try {
            BookResource expectedResponseBody = new BookResource("123", "N1");

            MvcResult mvcResult = mockMvc.perform(post("/add")
                    //.param("id", "1")
                    //.content(objectMapper.writeValueAsString(expectedResponseBody))
                    )
                    .andReturn();

            //String actualResponseBody = mvcResult.getResponse().getContentAsString();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            String viewName = modelAndView.getViewName();
            Map<String, Object> model = modelAndView.getModel();

            Assertions.assertThat(viewName).isEqualTo("create");

            //String orig = objectMapper.writeValueAsString(expectedResponseBody);
            System.out.println("model="+model);
            //Возвращает пустоту
            System.out.println(bookService.getAll());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
