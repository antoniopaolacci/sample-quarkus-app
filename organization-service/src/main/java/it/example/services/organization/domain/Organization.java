package it.example.services.organization.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Organization {

	@Id
	@GeneratedValue
    private Long id;
    
    @NotBlank
    @NotNull
    private String name;
    
    @NotBlank
    @NotNull
    private String address;
    
    // Other constructor:
    public Organization(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }
    
}