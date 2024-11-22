package com.unicauca.rest.exceptions;

import com.unicauca.rest.service.EnumErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class RubricaError {
    public final EnumErrorCodes code;
    /**
     * Campo del error
     */
    public final String field;
    /**
     * Descripción del error
     */
    public final String description;

}
