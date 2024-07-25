package com.pavvels.assessment.repository;

import com.pavvels.assessment.model.Testee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteeRepository extends MongoRepository<Testee, String> {

}
