package my.test.storage.service;

import my.test.storage.entity.StorageRole;
import my.test.storage.repository.StorageRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageRoleService {
    @Autowired
    StorageRoleRepository storageRoleRepository;

    public List<StorageRole> getRoles() {
        return (List<StorageRole>) storageRoleRepository.findAll();
    }

    public StorageRole addRole(String name) {
        return storageRoleRepository.save(StorageRole.builder().name(name).build());
    }

    public void deleteRole(Long id) {
        storageRoleRepository.deleteById(id);
    }
}
