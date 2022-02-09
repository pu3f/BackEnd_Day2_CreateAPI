package com.ideaco.dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirstService {
    @Autowired
    private JobRepository jobRepository;

    //example method service
    public String sendMessage(String message) {
        return "Sending message "+message;
    }

    //function get job data by id
    public JobModel getJobById(int jobId) {
        return jobRepository.findById(jobId).get();
    }

    public JobModel getJobByName(String jobName) {
        Optional<JobModel> jobOpt = jobRepository.findByJobName(jobName);
        if(jobOpt.isEmpty()) {
            return null;
        }
        return jobOpt.get();
    }

    public JobModel getJobBySalaryAndName(String jobName, int salary) {
        Optional<JobModel> jobOpt = jobRepository.findByJobNameAndJobSalary(jobName, salary);
        if(jobOpt.isEmpty()) {
            return null;
        }
        return jobOpt.get();
    }

    public List<JobModel> findAllJobs() {
        return jobRepository.findAll();
    }

    public List<JobModel> getJobBySalary(int salary) {
        return jobRepository.findByJobSalaryGreaterThan(salary);
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

    //post multiple data
    public List<JobModel> createJobWithBody(List<JobModel> jobModel) {
        return jobRepository.saveAll(jobModel);
    }

     public List<JobModel> searchJob(String jobName) {
        return jobRepository.searchJob(jobName);
     }

    public List<JobModel> filterJob(int jobSalary) {
        return jobRepository.filterJob(jobSalary);
    }

    public JobModel updateJob(int jobId, JobModel jobmodel) {
        Optional<JobModel> currentJobOpt = jobRepository.
                findById(jobId);

        if(currentJobOpt.isEmpty()) {
            return null;
        }
        return jobRepository.save(jobmodel);
    }

    public JobModel updateJobName(int jobId, String jobName) {
        Optional<JobModel> currentJobOpt = jobRepository.
                findById(jobId);

        if(currentJobOpt.isEmpty()) {
            return null;
        }

        JobModel currentJob = currentJobOpt.get();
        currentJob.setJobName(jobName);

        return jobRepository.save(currentJob);
    }

    public boolean delete(int jobId){
        Optional<JobModel> currentJobOpt = jobRepository.
                findById(jobId);

        if(currentJobOpt.isEmpty()) {
            return false;
        }

        //jobRepository.deleteById(jobId);
        jobRepository.delete(currentJobOpt.get());

        return true;
    }

    public boolean deleteJobByName(String jobName){
        Optional<JobModel> currentJobOpt = jobRepository.
                findByJobName(jobName);

        if(currentJobOpt.isEmpty()) {
            return false;
        }

        jobRepository.deleteByJobName(jobName);
        return true;
    }



}
