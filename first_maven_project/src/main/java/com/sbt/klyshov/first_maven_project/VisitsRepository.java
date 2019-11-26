package com.sbt.klyshov.first_maven_project;

/**
 * Created by SBT-Klyshov-DA on 16.05.2018.
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//interface VisitsRepository extends CrudRepository<Visit, Long> {
//}

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
}