package com.ideaco.dia;

import org.springframework.data.jpa.repository.JpaRepository;

// JobModel = class model name , Integer = data type primary key of JobModel
public interface JobRepository extends JpaRepository<JobModel, Integer> {
}
