package com.accenture.ms_customers.dto;

import com.accenture.ms_customers.dto.ms_accounts.AccountDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
@Schema(
        name = "Cliente con cuentas",
        description = "Información de cliente con sus respectivas cuentas"
)
public class CustomersWithAccountsDto {
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
    @Schema(description = "Cuentas del cliente")
    private List<AccountDto> accounts;
}
