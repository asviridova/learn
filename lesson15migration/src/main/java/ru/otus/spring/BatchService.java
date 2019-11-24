package ru.otus.spring;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    private JobLauncher jobLauncher;

    //@Autowired
    //@Qualifier("importUsers")
    //private JobLauncher importUsersJob;

    public void launchJob(Job job) {
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
