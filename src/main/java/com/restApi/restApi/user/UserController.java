package com.restApi.restApi.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "유저", description = "user API입니다.")
@RestController
public class UserController {
    private UserDaoService service;

    // 의존성 주입
    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String allUsers() {
        return "service.findAll()";
    }

    // 전체 조회
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /users/1 or /user/10 -> String
    // 1건 조회
    @GetMapping("/users/{id}")
    //public User retrieveUser(@PathVariable int id) {
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",  id));
        }
        // User 객체를 EntityModel로 래핑하고 링크를 추가
        EntityModel<User> model = EntityModel.of(user);

        // 다른 링크도 추가할 수 있습니다.
        Link allUsersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                this.getClass()).retrieveAllUsers()).withRel("all-users");
        model.add(allUsersLink);

        return model;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user ==  null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",  id));
        }
    }

   /* @PutMapping("/users/{id}")
    public Resource<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = service.update(id, user);

        if (updatedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));
        }

        return User;
    }*/

}
