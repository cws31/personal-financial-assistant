package cs.sonu.financialAssitant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cs.sonu.financialAssitant.entity.Income;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.date BETWEEN :startDate AND :endDate")
    Double getTotalIncomeBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}