package dev.binarycoders.thermae.core.mapper;

public interface Mapper<API, ENTITY> { // TODO Check https://mapstruct.org/ (remember lombok)

    API toApi(ENTITY entity);

    ENTITY toEntity(API api);
}
