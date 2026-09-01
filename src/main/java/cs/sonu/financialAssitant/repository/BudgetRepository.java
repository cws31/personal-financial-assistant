package cs.sonu.financialAssitant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs.sonu.financialAssitant.entity.Budget;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByMonthYear(String monthYear);
}