package org.example.userservice.service;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.entity.User;
import org.example.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository repository,
                       RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }


    public User save(User user) {
        return this.repository.save(user);
    }

    public User getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public User getUser(Long id) {
        User user = this.getById(id);
// goij qua service khac
      //  User user = restTemplate.getForObject("http://service01/service01/" + user.getDepartmentId(), Department.class);
return user;
    }
}
