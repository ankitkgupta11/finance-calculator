package com.finapp.finance_calculator.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class HistoryCleanupScheduler {

    private final JobLauncher jobLauncher;
    private final Job cleanupHistoryJob;

    public HistoryCleanupScheduler(JobLauncher jobLauncher,
                                   Job cleanupHistoryJob) {
        this.jobLauncher = jobLauncher;
        this.cleanupHistoryJob = cleanupHistoryJob;
    }

    @Scheduled(cron = "0 0 * 29 * ?") // daily at 2 AM
    public void runCleanupJob() throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addLong("runTime", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(cleanupHistoryJob, params);
    }
}
