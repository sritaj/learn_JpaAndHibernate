package in.sritaj.jpaandhibernate.entity.restfulapis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Interest Entity defining the Table structure
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String likes;

    private String dislikes;

    private String hobbies;

    private String profileUrl;

    private String about;

    @Transient
    private int userAccountId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
}
