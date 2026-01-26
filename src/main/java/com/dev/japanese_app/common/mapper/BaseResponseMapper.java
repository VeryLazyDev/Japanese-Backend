package com.dev.japanese_app.common.mapper;

public interface BaseResponseMapper<E, D> {

    default void validate(E entity){
        if (entity == null) throw new IllegalArgumentException("Entity must not be null");
    }
    default D toDto(E entity){
        throw new UnsupportedOperationException("toDto not implemented");
    }

}
