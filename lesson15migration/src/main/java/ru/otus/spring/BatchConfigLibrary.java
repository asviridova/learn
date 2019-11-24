package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.jpa.domain.AuthorJpa;
import ru.otus.spring.jpa.domain.GenreJpa;
import ru.otus.spring.repostory.AuthorRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

//https://www.concretepage.com/spring-5/spring-batch-h2-database

@EnableBatchProcessing
@Configuration
public class BatchConfigLibrary {
    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Author> mongoReaderAuthors(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Author>()
                .name("mongoReaderAuthors")
                .sorts(new HashMap<>())
                .jsonQuery("{}")
                .template(mongoTemplate)
                .collection("author")
                .targetType(Author.class)
                .build();
    }


    /*@Bean
    public ItemReader<Person> mongoReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Person>()
                .name("mongoReader")
                .sorts(new HashMap<>())
                .jsonQuery("{}")
                .template(mongoTemplate)
                .collection("person")
                .targetType(Person.class)
                .build();
    }*/


    /*@Bean
    public ItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileSystemResource("entries.csv"))
                .delimited()
                .names(new String[]{"name", "age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }*/

    /*@Bean
    public ItemProcessor processor() {
        return (ItemProcessor<Person, Person>) person -> {
            person.onBirthDay();
            return person;
        };
    }*/

    @Bean
    public ItemProcessor<Author, AuthorJpa> processorAuthor() {
        return (ItemProcessor<Author, AuthorJpa>) author -> {
            AuthorJpa authorJpa = new AuthorJpa();
            authorJpa.setId(Long.parseLong(author.getId()));
            authorJpa.setName(author.getName());
            authorJpa.setNationality(author.getNationality());
            return authorJpa;
        };
    }


    /*@Bean
    public FlatFileItemWriter writer() {
        return new FlatFileItemWriterBuilder<>()
                .name("personItemWriter")
                .resource(new FileSystemResource("output.csv"))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }*/

    @Bean
    public JdbcBatchItemWriter<AuthorJpa> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AuthorJpa>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AuthorJpa>())
                .sql("INSERT INTO author (id, name, nationality) VALUES (:id, :name, :nationality)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importAuthorJob(Step stepAuthors) {
        return jobBuilderFactory.get("importAuthorJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepAuthors)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job authors");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job authors");
                    }
                })
                .build();
    }

    @Bean
    public Step stepAuthors(JdbcBatchItemWriter<AuthorJpa> writer, ItemReader<Author> reader, ItemProcessor processorAuthor) {
        return stepBuilderFactory.get("stepAuthors")
                .chunk(5)
                .reader(reader)
                .processor(processorAuthor)
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения Author"); }
                    public void afterRead(Object o) { logger.info("Конец чтения Author"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения Author"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { logger.info("Начало записи Author"); }
                    public void afterWrite(List list) { logger.info("Конец записи Author"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи Author"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки Author");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки Author");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки Author");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки Author");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки Author");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки Author");}
                })
//                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    //-----------------------

    @Bean
    public Job importGenreJob(Step stepGenres) {
        return jobBuilderFactory.get("importGenreJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepGenres)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job genres");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job genres");
                    }
                })
                .build();
    }

    @Bean
    public ItemReader<Genre> mongoReaderGenres(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Genre>()
                .name("mongoReaderGenres")
                .sorts(new HashMap<>())
                .jsonQuery("{}")
                .template(mongoTemplate)
                .collection("genre")
                .targetType(Genre.class)
                .build();
    }

    @Bean
    public ItemProcessor<Genre, GenreJpa> processorGenre() {
        return (ItemProcessor<Genre, GenreJpa>) genre -> {
            GenreJpa genreJpa = new GenreJpa();
            genreJpa.setId(Long.parseLong(genre.getId()));
            genreJpa.setName(genre.getName());
            return genreJpa;
        };
    }

    @Bean
    public JdbcBatchItemWriter<GenreJpa> writerGenre(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<GenreJpa>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<GenreJpa>())
                .sql("INSERT INTO genre (id, name) VALUES (:id, :name)")
                .dataSource(dataSource)
                .build();
    }


    @Bean
    public Step stepGenres(JdbcBatchItemWriter<GenreJpa> writer, ItemReader<Genre> reader, ItemProcessor processorGenre) {
        return stepBuilderFactory.get("stepGenres")
                .chunk(5)
                .reader(reader)
                .processor(processorGenre)
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения Genre"); }
                    public void afterRead(Object o) { logger.info("Конец чтения Genre"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения Genre"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { logger.info("Начало записи Genre"); }
                    public void afterWrite(List list) { logger.info("Конец записи Genre"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи Genre"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки Genre");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки Genre");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки Genre");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки Genre");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки Genre");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки Genre");}
                })
//                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

}
