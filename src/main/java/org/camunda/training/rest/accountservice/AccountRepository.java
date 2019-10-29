package org.camunda.training.rest.accountservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

//@RepositoryRestResource only requiredto change export details
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    List<Account> findByLastname(String lastname);
    List<Account> findByLastnameContaining(String text);
}
