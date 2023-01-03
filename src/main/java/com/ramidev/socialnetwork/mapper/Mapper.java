package com.ramidev.socialnetwork.mapper;

import com.ramidev.socialnetwork.entities.Profile;

public interface Mapper<T> {
    Profile toEntity(T object);
    T toDto(Profile object);
}
