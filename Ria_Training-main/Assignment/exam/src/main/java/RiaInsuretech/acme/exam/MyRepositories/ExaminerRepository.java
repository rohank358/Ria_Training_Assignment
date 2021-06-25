package RiaInsuretech.acme.exam.MyRepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import RiaInsuretech.acme.exam.Entities.MyExaminer;

@Repository
public interface ExaminerRepository extends MongoRepository<MyExaminer, String> {

}
//