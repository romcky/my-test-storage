package my.test.storage.repository;

import my.test.storage.entity.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageFileRepository extends JpaRepository<StorageFile, Long> {

}
