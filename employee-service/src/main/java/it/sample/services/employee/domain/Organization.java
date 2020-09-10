package it.sample.services.employee.domain;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Organization {

    private Long id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String address;
    
    // Other constructor:
    public Organization(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }
    
}