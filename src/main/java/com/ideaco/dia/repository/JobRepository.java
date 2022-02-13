package com.ideaco.dia.repository;

import com.ideaco.dia.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// JobModel = class model name , Integer = data type primary key of JobModel
public interface JobRepository extends JpaRepository<JobModel, Integer> {

    //get object(s) using other parameter
    Optional<JobModel> findByJobName(String jobName);

    Optional<JobModel> findByJobNameAndJobSalary(String jobName, int salary);

    //get specific data using keywords
    List<JobModel> findByJobSalaryGreaterThan(int salary);

    //native query
    @Query(value = "select * from tab_job" + " where job_name like %:jobName%", nativeQuery = true)
    List<JobModel> searchJob(String jobName);

    //jpql
    @Query(value = "select j from JobModel j " +
            "where j.jobSalary >= :salary")
    List<JobModel> filterJob(@Param("salary") int jobSalary);

    //delete
    @Transactional
    void deleteByJobName(String jobName);
}
