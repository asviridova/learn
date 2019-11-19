package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.*;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//https://spring.io/guides/gs/testing-web/
//https://reflectoring.io/spring-boot-web-controller-test/

@WebMvcTest(controllers = LibraryController.class)
@ComponentScan({"ru.otus.spring", "ru.otus.spring.domain"})
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private String AUTHOR_SHAKESPEARE = "W.Shakespeare";
    private String BOOK_OTELLO = "Otello";
    private String GENRE_TRAGEDY = "tragedy";



    @Test
    public void check(){
        try {
            mockMvc.perform(post("/add"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Author shakespeare = null;
    private Genre tragedy = null;
    private Book otello = null;

    private Genre comedy = null;
    private Book midsummer = null;

    private List<Book> expectedBooks = null;
    private List<Author> expectedAuthors = null;
    private List<Genre> expectedGenres = null;
    Optional<Book> otelloOptional = null;

    private void initTestData(){
        shakespeare = new Author("1", AUTHOR_SHAKESPEARE, "English");
        tragedy = new Genre("1", GENRE_TRAGEDY);
        comedy = new Genre("1", "comedy");
        otello = new Book("1", BOOK_OTELLO, shakespeare, tragedy);
        otelloOptional =  Optional.of(otello);

        midsummer = new Book("2", "Midsummer nights dream", shakespeare, comedy);

        expectedBooks = new ArrayList<>();
        expectedBooks.add(otello);
        expectedBooks.add(midsummer);

        expectedAuthors = Collections.singletonList(shakespeare);
        expectedGenres = Collections.singletonList(tragedy);

//        given(bookService.getAll()).willReturn(expectedBooks);
//        given(authorService.getAll()).willReturn(expectedAuthors);
    }


    @Test
    @DisplayName("Основная стартовая страница")
    public void testMainPage() throws Exception {
        //подготовка данных
        initTestData();
        //тестирование ответа
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("books", expectedBooks))
                .andExpect(model().attribute("authors", expectedAuthors))
                .andExpect(model().attribute("genres", expectedGenres));
        verify(bookService, times(1)).getAll();
        verify(authorService, times(1)).getAll();
    }

    @Test
    @DisplayName("Страница редактирования книги")
    public void testEditPage() throws Exception {
        initTestData();
//        given(bookService.getById("1")).willReturn(otelloOptional);

        mockMvc.perform(get("/edit")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(view().name("edit"));
        verify(bookService, times(1)).getById("1");
    }

    @Test
    @DisplayName("Основная стартовая страница после удаления")
    public void testRemovePage() throws Exception {
        //подготовка данных
        initTestData();
        //тестирование ответа
        mockMvc.perform(post("/remove").param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    @DisplayName("Страница добавления")
    public void testAddPage() throws Exception {
        initTestData();
        mockMvc.perform(post("/add")
                )
                .andExpect(view().name("create"));
    }

    @Test
    @DisplayName("Сохранение книги и переход на основную страницу")
    public void  testSaveBook() throws Exception{
        mockMvc.perform(
                post("/save")
                        .param("id", "1")
                        .param("name", "New book")
                        .param("authorId", "1")
                        .param("genreId", "1")
        ).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        verify(bookService, times(1)).update("1", "New book", "1", "1");

    }





}
