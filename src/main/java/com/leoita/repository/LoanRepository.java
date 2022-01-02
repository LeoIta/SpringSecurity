package com.leoita.repository;

import com.leoita.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    @PreAuthorize("hasRole('ROOT')") // PreAuthorize will check the permissions before method execution
    List<Loan> findByCustomerIdOrderByStartDtDesc(long customerId);

}
