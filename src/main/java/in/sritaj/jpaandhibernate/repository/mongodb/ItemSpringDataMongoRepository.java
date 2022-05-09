package in.sritaj.jpaandhibernate.repository.mongodb;

import in.sritaj.jpaandhibernate.entity.mongodb.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemSpringDataMongoRepository extends MongoRepository<Item, String> {
}
