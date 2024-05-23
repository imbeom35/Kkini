package com.kkini.core.global.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Util {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final AmazonS3Client amazonS3Client;

    /**
     * S3 : 파일 업로드
     * fileType : 파일 폴더 구분(ex. post, recipe, badge, member)
     */
    public List<String> uploadFiles(String fileType, List<MultipartFile> multipartFiles) {

        List<String> filePaths = new ArrayList<>();

        // 포스트와 레시피만 폴더에 날짜를 붙인다.
        String uploadFilePath;
        if(fileType.equals("post") || fileType.equals("recipe")) {
            uploadFilePath = fileType + "/" + getFolderName();
        } else {
            uploadFilePath = fileType;
        }

        for (MultipartFile multipartFile : multipartFiles) {
            String originalFileName = multipartFile.getOriginalFilename();

            // 파일이름변경
            String uploadFileName = getUuidFileName(originalFileName);
            // ex) 구분(/년/월/일)/파일.확장자
            String filePath = uploadFilePath + "/" + uploadFileName;
            String fileFormat = multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1); //파일 확장자명 추출
            String uploadFileUrl = "";

            MultipartFile resizedImage = resizer(filePath, fileFormat, multipartFile, 400); //오늘의 핵심 메서드

            // 메타데이터 생성
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(resizedImage.getSize());
            objectMetadata.setContentType(resizedImage.getContentType());

            try (InputStream inputStream = resizedImage.getInputStream()) {

                // S3에 폴더 및 파일 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(bucketName, filePath, inputStream, objectMetadata)
                );

                // TODO : 외부에 공개하는 파일인 경우 Public Read 권한을 추가, ACL 확인
                // amazonS3Client.putObject(
                // new PutObjectRequest(bucket, s3Key, inputStream, objectMetadata)
                // .withCannedAcl(CannedAccessControlList.PublicRead));

                // S3에 업로드한 폴더 및 파일 URL
                uploadFileUrl = amazonS3Client.getUrl(bucketName, filePath).toString();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Filed upload failed", e);
            }

            // 저장된 파일 정보 반환
            filePaths.add(uploadFileUrl);
        }

        return filePaths;
    }

    /**
     * S3 : 파일 삭제
     */
    public List<String> deleteFile(List<String> filePaths) {

        List<String> result = new ArrayList<>();

        for(String filePath : filePaths) {
            String state = "success";

            try {
                boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, filePath);
                if (isObjectExist) {
                    amazonS3Client.deleteObject(bucketName, filePath);
                } else {
                    state = "file not found";
                }
            } catch (Exception e) {
                state = "failed";
                log.debug("Delete File failed", e);
            }

            result.add(state);
        }

        return result;
    }

    /**
     * S3 : 이미지 url 조합
     */
    public List<String> getImageUrl(List<String> filePaths) {
        List<String> urlList = new ArrayList<>();

        for(String filePath : filePaths) {
            try {
                urlList.add(amazonS3Client.getUrl(bucketName, filePath).toString());
            } catch (Exception e) {
                log.debug("file not found", e);
            }
        }

        return urlList;
    }

    /**
     * UUID 파일명 반환
     */
    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    /**
     * 년/월/일 폴더명 반환
     */
    private String getFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/");
    }

    public MultipartFile resizer(String fileName, String fileFormat, MultipartFile originalImage, int width) {

        try {
            BufferedImage image = ImageIO.read(originalImage.getInputStream());// MultipartFile -> BufferedImage Convert
            log.warn("{} / {}", image.getHeight(), image.getWidth());

            int originWidth = image.getWidth();
            int originHeight = image.getHeight();

            // origin 이미지가 400보다 작으면 패스
            if(originWidth < width)
                return originalImage;

            MarvinImage imageMarvin = new MarvinImage(image);

            Scale scale = new Scale();
            scale.load();
            scale.setAttribute("newWidth", width);
            scale.setAttribute("newHeight", width * originHeight / originWidth);//비율유지를 위해 높이 유지
            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);

            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imageNoAlpha, fileFormat, baos);
            baos.flush();

            return new CustomMultipartFile(fileName,fileFormat,originalImage.getContentType(), baos.toByteArray());

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 줄이는데 실패했습니다.");
        }
    }

}
