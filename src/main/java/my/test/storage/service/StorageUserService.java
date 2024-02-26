package my.test.storage.service;

import my.test.storage.entity.StorageRole;
import my.test.storage.entity.StorageUser;
import my.test.storage.repository.StorageRoleRepository;
import my.test.storage.repository.StorageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class StorageUserService implements UserDetailsService {
    @Autowired
    private StorageUserRepository storageUserRepository;
    @Autowired
    private StorageRoleRepository storageRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<StorageUser> getUsers() {
        return (List<StorageUser>) storageUserRepository.findAll();
    }

    public StorageUser addUser(String name, String password, String role) {
        return storageUserRepository.save(StorageUser.builder()
                .name(name)
                .pwd(bCryptPasswordEncoder.encode(password))
                .roles(new HashSet<>(storageRoleRepository.findByName(role)))
                .build());
    }

    public void deleteUser(Long id) {
        storageUserRepository.deleteById(id);
    }

    public Optional<StorageUser> getUserByUsername(String username) {
        var lst = storageUserRepository.findByName(username);
        return lst.isEmpty() ? Optional.empty() : Optional.of(lst.get(0));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return getUserByUsername(username).orElseThrow(
                // no such user exception
        );
    }
}
