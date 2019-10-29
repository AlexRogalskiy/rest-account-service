package org.camunda.training.rest.accountservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long accountNumber;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private Integer balance;
}
