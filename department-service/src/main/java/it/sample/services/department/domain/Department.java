package it.sample.services.department.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department {

    private Long id;
    
    @NotNull
    private Long organizationId;
    
    @NotBlank
    private String name;
    
    // Other constructor:
    public Department(Long organizationId, String name) {
        super();
        this.organizationId = organizationId;
        this.name = name;
    }

}