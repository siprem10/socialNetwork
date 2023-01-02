package com.ramidev.socialnetwork.dto.image;

import lombok.Data;

@Data
public class CloudinaryDto {
    private String url;
    private String secure_url;
    private String folder;
    private String created_at;
    private Integer bytes;
    private String format;
    private Integer height;
    private Integer width;
}
