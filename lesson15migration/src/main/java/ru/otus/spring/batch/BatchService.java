package ru.otus.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BatchService {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    JobOperator jobOperator;

    @Autowired
    @Qualifier("importBookJob")
    Job importBookJob;

    @Autowired
    @Qualifier("importAuthorJob")
    Job importAuthorJob;

    @Autowired
    @Qualifier("importGenreJob")
    Job importGenreJob;

    public void launchImportBookJob() {
        try {
            jobLauncher.run(importBookJob, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void launchImportGenreJob() {
        try {
            jobLauncher.run(importGenreJob, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void launchImportAuthorJob() {
        try {
            jobLauncher.run(importAuthorJob, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopImportBookJob() {
        try {
            Set<Long> executions = jobOperator.getRunningExecutions("importBookJob");
            jobOperator.stop(executions.iterator().next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopImportGenreJob() {
        try {
            Set<Long> executions = jobOperator.getRunningExecutions("importGenreJob");
            jobOperator.stop(executions.iterator().next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopImportAuthorJob() {
        try {
            Set<Long> executions = jobOperator.getRunningExecutions("importAuthorJob");
            jobOperator.stop(executions.iterator().next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
