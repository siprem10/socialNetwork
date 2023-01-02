package com.ramidev.socialnetwork.dto.mapper;

import com.ramidev.socialnetwork.entities.Profile;

public interface Mapper<T> {
    Profile toEntity(T object);
    T toDto(Profile object);
}
