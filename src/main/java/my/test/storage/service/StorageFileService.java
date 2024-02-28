package my.test.storage.service;

import my.test.storage.entity.StorageFile;
import my.test.storage.repository.StorageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageFileService {
    @Autowired
    private StorageFileRepository storageFileRepository;

    public List<StorageFile> getFiles() {
        return (List<StorageFile>) storageFileRepository.findAll();
    }

    public StorageFile getFileById(Long id) {
        return storageFileRepository.findById(id).orElseThrow(
                //no such file exception
        );
    }

    public StorageFile addFile(String name, String info, byte[] data) {
        return storageFileRepository.save(StorageFile.builder()
                .name(name)
                .info(info)
                .data(data)
                .build());
    }

    public void deleteFile(Long id) {
        storageFileRepository.deleteById(id);
    }
}
