package com.accenture.ms_customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Cliente",
        description = "Esquema para administrar los datos de clietnes bancarios"
)
public class CustomerDto {
    //    private Long customerId;
    @Schema(description = "Número de doucumento de identidad")
    @NotEmpty(message = "El campo documento es obligatorio")
    @Size(min = 5, max = 20, message = "El campo documento debe contener entre 5 y 20 caracteres")
    private String document;

    @Schema(description = "Nombre completo")
    @NotEmpty(message = "El campo nombre es obligatorio")
    @Size(min = 3, max = 20, message = "El campo name debe contener entre 3 y 20 caracteres")
    private String name;

    @Schema(description = "Correo electróico")
    @NotEmpty(message = "El campo correo es obligatorio")
    @Email(message = "El campo correo debe tener un formato válido")
    private String email;

    @Schema(description = "Número de contacto")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "El campo telefono debe contener máximo 10 digitos")
    private String phone;

    @Schema(description = "Dirección de residencia")
    @Size(min = 10, max = 50, message = "El campo name debe contener entre 3 y 20 caracteres")
    private String address;
}
