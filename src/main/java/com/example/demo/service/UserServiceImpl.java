package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserDto;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<>();
            userDao.findAll().iterator().forEachRemaining(user -> {
                if (user != null) {
                    user.setContrat(null);
                }
                users.add(user);
            });
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de tous les utilisateurs.", e);
        }
    }

    @Override
    // Fonction pour trouver un utilisateur par nom d'utilisateur
    public User findOne(String username) {    
        try {
            User user = userDao.findByUsername(username);
            if (user != null) {
                user.setContrat(null);
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur par mail.", e);
        }
    }

    @Override
    // Fonction pour enregistrer un nouvel utilisateur
    public User save(UserDto user) {
        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if (nUser.getEmail().split("@")[1].equals("admin.edu")) {
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }

    public String supprimerUser(User user) {
        if (user == null) {
            return "Erreur : Utilisateur est null";
        }

        User existingUser = this.userDao.findByUsername(user.getUsername());

        if (existingUser == null) {
            return "Erreur : Le compte n'existe pas";
        }

        try {
            this.userDao.deleteById(user.getId());
            return "Succès : Suppression réussie";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String updateUser(int idUser, User updatedUser) {
        if (updatedUser == null) {
            return "Erreur : L'utilisateur est invalide";
        }

        User existingUser = this.userDao.findById(idUser);

        if (existingUser == null) {
            return "Erreur : L'utilisateur n'existe pas";
        }

        // Mettre à jour les champs de l'utilisateur existant avec les nouvelles valeurs
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setMatriculeUser(updatedUser.getMatriculeUser());
        existingUser.setContrat(updatedUser.getContrat());

        try {
            if (this.userDao.save(existingUser) != null) {
                return "Succès : Mise à jour réussie";
            } else {
                return "Erreur : Erreur de mise à jour";
            }
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }


}
