package com.accenture.ms_customers.controllers;

import com.accenture.ms_customers.dto.CustomerDto;
import com.accenture.ms_customers.dto.ErrorResponseDto;
import com.accenture.ms_customers.dto.ResponseDto;
import com.accenture.ms_customers.dto.SupportInfoDto;
import com.accenture.ms_customers.repository.CustomerRepository;
import com.accenture.ms_customers.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(
        name = "Gestión de clientes de MyAccentureBank",
        description = "Proporciona operaciiones crud y de consulta sobre la base de datos de clientes del banco"
)
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class CustomerController {
    @NonNull
    private final ICustomerService customerService;

    @NotNull
    private Environment environment;
    @NotNull
    private SupportInfoDto supportInfoDto;

    @Value(value = "${build.version}")
    private String buildVersion;

    @GetMapping(value = "/support-info")
    public ResponseEntity<SupportInfoDto> getSupportInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(supportInfoDto);
    }
    @GetMapping(value = "/build-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), buildVersion));
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return "Hello from ms-customer micro service";
    }

    @GetMapping(value = "/now", produces = MediaType.TEXT_PLAIN_VALUE)
    public String now() {
        return LocalDateTime.now().toString();
    }

    @Operation(summary = "Buscar Cliente por email", description = "Consulta la base de datos de clientes buscando coincidencias por el email proporcionado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT_FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping(value = "/customer/by/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> findById(
            @PathVariable
            Long id) {
        CustomerDto customerDto = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> findByDocument(
            @RequestParam
            @NotEmpty(message = "El campo documento es obligatorio")
            @Size(min = 5, max = 20, message = "El campo documento debe contener entre 5 y 20 caracteres")
            String document) {
        CustomerDto customerDto = customerService.fetchCustomerByDocument(document);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @GetMapping(value = "/customer/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> findByEmail(
            @PathVariable
            @NotEmpty(message = "El campo documento es obligatorio")
            @Size(min = 5, max = 20, message = "El campo documento debe contener entre 5 y 20 caracteres")
            String email) {
        CustomerDto customerDto = customerService.fetchCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "crear nuevo cliente", description = "Registra un nuevo cliente con las reglas de negocio definidas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD_REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(), "Customer created successfully"));
    }

    @PutMapping(value = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = customerService.updateCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedCustomerDto);
    }

    @DeleteMapping(value = "/customer/document/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteCustomerByDocument(@PathVariable String document) {
        customerService.deleteCustomerByDocument(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Customer deleted successfully"));
    }

    @DeleteMapping(value = "/customer/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteCustomerByEmail(@PathVariable String email) {
        customerService.deleteCustomerByEmail(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Customer deleted successfully"));
    }

}
