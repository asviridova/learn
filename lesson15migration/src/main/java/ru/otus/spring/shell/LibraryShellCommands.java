package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.batch.BatchService;

@ShellComponent
public class LibraryShellCommands {
    private final BatchService batchService;

    boolean migrateGenres = false;
    boolean migrateAuthors = false;

    @Autowired
    public LibraryShellCommands(BatchService batchService){
        this.batchService = batchService;
    }

    @ShellMethod(value = "migrateAll",  key ={ "all" })
    public void callMigrateAll(){
        batchService.launchImportGenreJob();
        batchService.launchImportAuthorJob();
        batchService.launchImportBookJob();
    }

    @ShellMethod(value = "migrateGenres",  key ={ "genres" , "g"})
    public void callMigrateGenres(){
        migrateGenres = true;
        batchService.launchImportGenreJob();
    }

    @ShellMethod(value = "migrateAuthors",  key ={ "authors" , "a"})
    public void callMigrateAuthors(){
        migrateAuthors = true;
        batchService.launchImportAuthorJob();
    }

    @ShellMethod(value = "migrateBooks",  key ={ "books" , "b"})
    @ShellMethodAvailability("booksAvailability")
    public void callMigrateBooks(){
        batchService.launchImportBookJob();
    }

    public Availability booksAvailability() {
        return (migrateGenres && migrateAuthors) ? Availability.available() : Availability.unavailable("You should migrate genres and authors first");
    }

    @ShellMethod(value = "stopGenres",  key ={ "genres-stop" , "gs"})
    public void stopGenres(){
        batchService.stopImportGenreJob();
    }

    @ShellMethod(value = "stopAuthors",  key ={ "authors-stop" , "as"})
    public void stopAuthors(){
        batchService.stopImportAuthorJob();
    }

    @ShellMethod(value = "stopBooks",  key ={ "books-stop" , "bs"})
    public void stopBooks(){
        batchService.stopImportBookJob();
    }


}
