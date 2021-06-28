package RiaInsuretech.acme.exam.MyRepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import RiaInsuretech.acme.exam.Entities.*;

@Repository
public interface MyUserRepository extends MongoRepository<MyUser, String> {

}
