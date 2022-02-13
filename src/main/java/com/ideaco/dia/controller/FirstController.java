package com.ideaco.dia.controller;

import com.ideaco.dia.dto.JobDTO;
import com.ideaco.dia.model.JobModel;
import com.ideaco.dia.response.DataResponse;
import com.ideaco.dia.response.HandlerResponse;
import com.ideaco.dia.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class FirstController {

//    define new object without constructor
    @Autowired
    private FirstService firstService;

//    with constructor
//    public FirstController(FirstService firstService) {
//        this.firstService = firstService;
//    }

    @GetMapping("/helloWorld")
    public String helloWorld(/*request*/) {
        //response
        return "Hello World";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message) {
        return firstService.sendMessage(message);

    }

//    JobById from JobDTO
    @GetMapping("/job/{jobId}") //{} = path variable
    public JobDTO getJob(@PathVariable("jobId") int jobId) {
        return firstService.getJobById(jobId);
    }

//    get job by name
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

//    get job by specific salary
    @GetMapping("/job/salary/{salary}")
    public List<JobModel> getJobBySalary(@PathVariable("salary") int salary) {
        return firstService.getJobBySalary(salary);
    }

//    find all jobs from job dto
    @GetMapping("/jobs")
    public List<JobDTO> getAllJobs(){
        return firstService.findAllJobs();
    }

//    search jobs by name
    @GetMapping("/job/search")
    public List<JobModel> searchJob(@RequestParam("jobName") String jobName) {
        return firstService.searchJob(jobName);
    }

//    filter jobs by salary
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

    @GetMapping("/job/response")
    public void getJobWithResponse(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam("jobId") int jobId) {
        JobDTO jobDTO = firstService.getJobById(jobId);

//      standardization error
        DataResponse<JobDTO> data = new DataResponse<>();
        data.setData(jobDTO);
        HandlerResponse.responseSuccessWithData(response, data);

//       HandlerResponse.responseBadRequest(response, "001", "");
//       HandlerResponse.responseInternalServerError(response, "Something wrong");
//       HandlerResponse.responseSuccessOK(response,"Success get job");
//       HandlerResponse.responseUnauthorized(response, "User not authorized");
    }
}
