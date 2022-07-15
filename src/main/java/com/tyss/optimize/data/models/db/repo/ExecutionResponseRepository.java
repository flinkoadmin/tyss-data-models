package com.tyss.optimize.data.models.db.repo;

import com.tyss.optimize.data.models.dto.results.ExecutionEntityResponse;
import com.tyss.optimize.data.models.dto.results.ExecutionResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ExecutionResponseRepository extends MongoRepository<ExecutionResponse, String> {

}
