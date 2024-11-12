package bgaebalja.bsherpa.file.service;

import bgaebalja.bsherpa.file.domain.AddFileRequest;
import bgaebalja.bsherpa.file.domain.TargetType;
import bgaebalja.bsherpa.file.domain.UserFile;
import bgaebalja.bsherpa.file.exception.S3UploadFailedException;
import bgaebalja.bsherpa.file.repository.FileRepository;
import bgaebalja.bsherpa.util.FormatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static bgaebalja.bsherpa.file.exception.ExceptionMessage.S3_UPLOAD_FAILED_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final S3Client s3Client;

    private static final String TLS_HTTP_PROTOCOL = "https://";
    private static final String S3_ADDRESS = "s3.amazonaws.com";
    private static final String DEFAULT_FILE_URL
            = "https://ddipddipddip.s3.amazonaws.com/post/1817/1729057414653_-2024-07-02-111008.png";

    @Value("${cloud.aws.s3.bucket-name}")
    private String s3BucketName;

    @Override
    @Transactional(isolation = READ_COMMITTED, timeout = 10)
    public UserFile createFile(AddFileRequest addFileRequest) {
        MultipartFile fileToUpload = addFileRequest.getFile();
        TargetType targetType = FormatConverter.parseToTargetType(addFileRequest.getTargetType());
        Long targetId = FormatConverter.parseToLong(addFileRequest.getTargetId());

        List<UserFile> existingImages
                = fileRepository.findByTargetTypeAndTargetIdAndDeleteYnFalseOrderByCreatedAtDesc(targetType, targetId);
        UserFile userFile = UserFile.of(targetType, targetId, uploadToS3(fileToUpload, targetType, targetId));
        fileRepository.save(userFile);

        return userFile;
    }

    private String uploadToS3(MultipartFile file, TargetType targetType, Long productId) {
        String sanitizedFilename = FormatConverter.sanitizeFileName(file.getOriginalFilename());
        long ms = System.currentTimeMillis();
        String key
                = String.format("%s/%d/%s_%s", targetType.toString().toLowerCase(), productId, ms, sanitizedFilename);

        try {
            File tempFile = convertMultipartFileToFile(file);
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(key)
                    .build(), Paths.get(tempFile.getPath()));
            tempFile.delete();
        } catch (IOException ie) {
            throw new S3UploadFailedException(S3_UPLOAD_FAILED_EXCEPTION_MESSAGE, ie);
        }

        return String.format("%s%s.%s/%s", TLS_HTTP_PROTOCOL, s3BucketName, S3_ADDRESS, key);
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile
                = new File(String.format("%s/%s", System.getProperty("java.io.tmpdir"), file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    @Transactional(isolation = READ_COMMITTED, readOnly = true, timeout = 10)
    @Override
    public List<UserFile> getFiles(TargetType targetType, Long targetId) {
        return fileRepository.findByTargetTypeAndTargetIdAndDeleteYnFalseOrderByCreatedAtDesc(targetType, targetId);
    }
}
