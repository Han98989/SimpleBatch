package com.michaelcgood;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Import(CsvFileToDatabaseConfig.class)
public class Controller {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    CsvFileToDatabaseConfig jobCreator;

    @RequestMapping("/launchjob")
    public String handle() throws Exception {
        try {
            Job job = jobCreator.csvFileToDatabaseJob();
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", new Date().getTime()).toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {

        }

        return "Done";
    }
}
