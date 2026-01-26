package com.dev.japanese_app.common.mapper;

import java.util.Collections;
import java.util.List;

public interface BaseResponseMapper<E, D, R> {
    /*
     *  E = Entity, D = Response DTO, R = Request DTO
     * */
    default void validate(E entity) {
        if (entity == null) throw new IllegalArgumentException("Entity must not be null");
    }

    /*
    * Entity to Response
    * */
    D toResponseDto(E entity);
    /*
    * Request DTO to Entity
    * */
    E toEntity(R request);

    default List<D> toResponseList(List<E> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(this::toResponseDto).toList();
    }
}
