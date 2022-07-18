package com.idf.idftestproject.dto.converter;

public interface DtoConverter <E,D>{

    D convertToDto(E e);

    D convertToShortDto(E e);

    E convertToEntity(D d);

}
