package com.example.demo.controller;


import com.example.demo.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //TODO: metodo POST****
    // Constructor (para inyección de dependencias)
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        try {
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            System.err.println("Error de integridad de datos al crear usuario: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Método GET para obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    // TODO: Método PUT (Actualizar)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setPictureLarge(userDetails.getPictureLarge());
            existingUser.setNameTitle(userDetails.getNameTitle());
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setPhone(userDetails.getPhone());
            existingUser.setGender(userDetails.getGender());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setState(userDetails.getState());
            existingUser.setCity(userDetails.getCity());
            existingUser.setPostcode(userDetails.getPostcode());


            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO: Método DELETE (Eliminar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        if (userRepository.existsById(id)) {

            userRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Con este método sencillo, aseguramos que el contexto se cargue.
    @GetMapping("/test")
    public String testConnection() {
        // Esto solo devolverá un mensaje si el contexto arranca
        return "Estoy cansado jefe!";
    }

}
