package in.sritaj.jpaandhibernate.entity.mongodb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document
public class Item {

    @Id
    private String id;

    private String name;

    private float price;
}
