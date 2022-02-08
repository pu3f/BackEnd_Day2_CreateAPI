package com.ideaco.dia;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstService {

    private JobRepository jobRepository;

    //constructor
    public FirstService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //example method service
    public String sendMessage(String message) {
        return "Sending message "+message;
    }

    //function get job data by id
    public JobModel getJobById(int jobId) {
        return jobRepository.findById(jobId).get();
    }

    public List<JobModel> findAllJobs() {
        return jobRepository.findAll();
    }

    //create new job
    public JobModel createJob(String jobName,
                              String jobDesc,
                              int jobSalary) {

        //validate is jobName already exist
        JobModel newJob = new JobModel();
        newJob.setJobName(jobName);
        newJob.setJobDesc(jobDesc);
        newJob.setJobSalary(jobSalary);

        return jobRepository.save(newJob);
    }

}
