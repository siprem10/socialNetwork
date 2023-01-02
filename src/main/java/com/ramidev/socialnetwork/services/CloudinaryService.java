package com.ramidev.socialnetwork.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramidev.socialnetwork.dto.image.CloudinaryDto;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Value("${cloudinary.base.url}")
    private String clodinaryBaseUrl;

    @Autowired
    private ObjectMapper objectMapper;

    public CloudinaryDto submitImage(MultipartFile image) throws IOException {
        return submitImage(image, null);
    }

    public CloudinaryDto submitImage(MultipartFile image, String subfolder) throws IOException {

        if(!ImageUtil.isValidImage(image)) throw new NotFoundException("Imagen no válida!");

        try {
            Cloudinary cloudinary = new Cloudinary(clodinaryBaseUrl);
            cloudinary.config.secure = true;
            System.out.println(cloudinary.config.cloudName);

            final String saveInFolder = (subfolder == null) ? "socialnetwork/" : "socialnetwork/"+subfolder;

            Map params = ObjectUtils.asMap(
                    "use_filename", false,
                    "unique_filename", false,
                    "overwrite", true,
                    "folder", saveInFolder
            );
            CloudinaryDto dto = objectMapper.convertValue(cloudinary.uploader().upload(image.getBytes(), params), CloudinaryDto.class);
            return dto;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
