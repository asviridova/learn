package ru.otus.spring.orm;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.spring.orm.models.AuthorModel;
import ru.otus.spring.orm.repository.AuthorRepositoryJpa;
import ru.otus.spring.orm.repository.AuthorRepositoryJpaImpl;
import java.util.Optional;
import org.h2.tools.Console;

@SpringBootApplication
public class OrmDemoApplication {

	
	public static void main(String[] args) throws Exception {
		//SpringApplication.run(OrmDemoApplication.class, args);
		 ApplicationContext context = SpringApplication.run(OrmDemoApplication.class);
	
		 AuthorRepositoryJpaImpl authorRepository = context.getBean(AuthorRepositoryJpaImpl.class);
		 
		 Optional<AuthorModel> author = authorRepository.findById(1);
        System.out.println("author: " + author);
		
		 AuthorModel am = new AuthorModel();
		 am.setId(10);
		 am.setName("O.Wild");
		 am.setNationality("English");
		 authorRepository.save(am);

		 Optional<AuthorModel> author2 = authorRepository.findById(10);

        System.out.println("author2: " + author2);
        //System.out.println("author.getname: " + author2.get().getName());
        Console.main(args);

	}

}
