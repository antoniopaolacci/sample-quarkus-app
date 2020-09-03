package it.sample.services.employee.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Employee representations:
 *
 * [
 *	{
 *		"id": 1,
 *		"organizationId": 1,
 *		"departmentId": 1,
 *		"name": "John Smith",
 *		"age": 30,
 *		"position": "Developer"
 *	},
 *	{
 *		"id": 2,
 *		"organizationId": 1,
 *		"departmentId": 1,
 *		"name": "Paul Walker",
*		"age": 40,
*		"position": "Architect"
*	}
* ]
*
*/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    private Long id;

    @NotNull
    private Long organizationId;

    @NotNull
    private Long departmentId;

    @NotBlank
    private String name;

    @Min(1)
    @Max(100)
    private int age;

    @NotBlank
    private String position;
    
    // Other constructor:
    public Employee(Long organizationId, Long departmentId, String name, int age, String position) {
        this.organizationId = organizationId;
        this.departmentId = departmentId;
        this.name = name;
        this.age = age;
        this.position = position;
    }

}//end class