package my.test.storage.repository;

import my.test.storage.entity.StorageUser;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface StorageUserRepository extends JpaRepository<StorageUser, Long> {

    List<StorageUser> findByName(String name);
}
