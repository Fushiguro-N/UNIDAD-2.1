package pe.edu.upeu.lms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.uploads.dir}")
    private String uploadsDir;

    public String store(MultipartFile file) {
        try {
            Path dir = Path.of(uploadsDir);
            Files.createDirectories(dir);
            String original = file.getOriginalFilename() != null ? file.getOriginalFilename() : "archivo";
            String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
            String filename = UUID.randomUUID() + ext;
            Path target = dir.resolve(filename).normalize();
            file.transferTo(target);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el archivo", e);
        }
    }

    public Resource load(String filename) {
        try {
            Path dir = Path.of(uploadsDir);
            Path file = dir.resolve(filename).normalize();
            if (!file.startsWith(dir)) {
                throw new ResourceNotFoundException("Archivo no encontrado: " + filename);
            }
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new ResourceNotFoundException("Archivo no encontrado: " + filename);
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("Archivo no encontrado: " + filename);
        }
    }
}
