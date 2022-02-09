package com.ideaco.dia;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class FirstController {

    //new object firstService
    private FirstService firstService;

    public FirstController(FirstService firstService) {
        this.firstService = firstService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld(/*request*/) {
        //response
        return "Hello World";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message) {
        return firstService.sendMessage(message);

    }

    @GetMapping("/job/{jobId}") //{} = path variable
    public JobModel getJob(@PathVariable("jobId") int jobId) {
        return firstService.getJobById(jobId);
    }

    //get job by name
    @GetMapping("/job/name/{jobName}")
    public JobModel getJobByName(@PathVariable("jobName") String jobName) {
        JobModel jobByName = firstService.getJobByName(jobName);
        if (jobByName != null) {
            return jobByName;
        }else {
            return new JobModel();
        }
    }

    @GetMapping("/job/name/salary")
    public JobModel getJobByNameAndSalary(@RequestParam("jobName") String jobName,
                                          @RequestParam("jobSalary") int salary) {
        JobModel jobByName = firstService.getJobBySalaryAndName(jobName, salary);
        if (jobByName != null) {
            return jobByName;
        } else {
            return new JobModel();
        }
    }

    //get job by specific salary
    @GetMapping("/job/salary/{salary}")
    public List<JobModel> getJobBySalary(@PathVariable("salary") int salary) {
        return firstService.getJobBySalary(salary);
    }

    //get all jobs
    @GetMapping("/jobs")
    public List<JobModel> getAllJobs() {
        return firstService.findAllJobs();
    }

    @GetMapping("/job/search")
    public List<JobModel> searchJob(@RequestParam("jobName") String jobName) {
        return firstService.searchJob(jobName);
    }

    @GetMapping("/job/filter")
    public List<JobModel> filterJob(@RequestParam("jobSalary") int jobSalary) {
        return firstService.filterJob(jobSalary);
    }

    @PostMapping("/job")
    public JobModel createJob(@RequestParam("jobName")String jobName,
                              @RequestParam("jobDesc")String jobDesc,
                              @RequestParam("jobSalary") int jobSalary) {

        return firstService.createJob(jobName, jobDesc, jobSalary);
    }

    @PostMapping("/jobs/body")
    public List<JobModel> createJobWithBody(@RequestBody List<JobModel> jobModel) {
        return firstService.createJobWithBody(jobModel);

    }

    @PutMapping("/job/{jobId}")
    public JobModel updateJob(@PathVariable("jobId") int jobId,
                              @RequestBody JobModel jobModel) {
        JobModel updateJob =  firstService.updateJob(jobId, jobModel);
        if(updateJob != null){
            return updateJob;
        }
        else {
            return new JobModel();
        }

    }

    @PatchMapping("/job/update")
    public JobModel updateJobName(@RequestParam("jobId") int jobId,
                                  @RequestParam ("jobName") String jobName) {
        JobModel updateJob = firstService.updateJobName(jobId, jobName);
        if (updateJob != null) {
            return updateJob;
        } else {
            return new JobModel();
        }
    }

    @DeleteMapping("/job/{jobId}")
    public boolean deleteJob(@PathVariable("jobId") int jobId){
        return firstService.delete(jobId);
    }

    @DeleteMapping("/job/name/{jobName}")
    public boolean deleteJob(@PathVariable("jobName") String jobName){
        return firstService.deleteJobByName(jobName);
    }

}
