package in.sritaj.jpaandhibernate.controller;

import in.sritaj.jpaandhibernate.entity.restfulapis.Interest;
import in.sritaj.jpaandhibernate.entity.restfulapis.UserAccount;
import in.sritaj.jpaandhibernate.repository.restfulapis.InterestSpringDataRepository;
import in.sritaj.jpaandhibernate.repository.restfulapis.UserAccountSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * UserAccountController class for implementing REST APIs
 */
@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private UserAccountSpringDataRepository userAccountSpringDataRepository;

    @Autowired
    private InterestSpringDataRepository interestSpringDataRepository;

    /**
     * API to register new user
     *
     * @param userAccount - User details to send in Body
     * @return UserAccount - Account created
     */
    @PostMapping("users/register-user")
    public UserAccount registerUser(@RequestBody UserAccount userAccount) {
        return userAccountSpringDataRepository.save(userAccount);
    }

    /**
     * API to create Interest and associate with the User
     *
     * @param interest - Interest details for the specified User
     * @param userID - userID to be sent as path parameter for associating the created Interest with the user belonging to the ID
     * @return Interest - Interest created
     */
    @PostMapping("interests/update/{userID}")
    public Interest updateInterest(@RequestBody Interest interest, @PathVariable("userID") int userID) {
        Optional<UserAccount> userAccount = userAccountSpringDataRepository.findById(userID);
        interest.setUserAccount(userAccount.get());
        return interestSpringDataRepository.save(interest);
    }

    /**
     * API to create Interest and associate with the User
     *
     * @return List - Users list
     */
    @GetMapping("users/all-users")
    public List<UserAccount> getAllUsers() {
        return userAccountSpringDataRepository.findAll();
    }

    /**
     * API to delete Interest
     *
     */
    @DeleteMapping("interests/delete/{interestId}")
    public void deleteInterest(@PathVariable("interestId") int interestId) {
        interestSpringDataRepository.deleteById(interestId);
    }

    /**
     * API to get Users list with matching Age, City and Country as the Specified UserID(except the specified User itself)
     *
     * @param id - User ID
     * @return List - Users list
     */
    @GetMapping("users/user/{id}")
    public List<UserAccount> findMatches(@PathVariable("id") int id){
        UserAccount userAccount = userAccountSpringDataRepository.findById(id).get();
        return userAccountSpringDataRepository.findMatches(userAccount.getAge(), userAccount.getCity(), userAccount.getCountry(), userAccount.getId());
    }
}
