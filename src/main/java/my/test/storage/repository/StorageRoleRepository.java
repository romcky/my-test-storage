package my.test.storage.repository;

import my.test.storage.entity.StorageRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRoleRepository extends JpaRepository<StorageRole, Long> {

    List<StorageRole> findByName(String name);
}
