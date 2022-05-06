package in.sritaj.jpaandhibernate.entity.fileshandling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Image Entity defining the Table structure
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    public Image(String name) {
        this.name = name;
    }

    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
