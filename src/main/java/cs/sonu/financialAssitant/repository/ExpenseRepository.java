package cs.sonu.financialAssitant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cs.sonu.financialAssitant.entity.Expense;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Expense> findByCategoryIgnoreCase(String category);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
    Double getTotalExpenseBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE LOWER(e.category) = LOWER(:category) AND e.date BETWEEN :startDate AND :endDate")
    Double getTotalExpenseByCategoryAndDateBetween(@Param("category") String category, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Expense findTopByDateBetweenOrderByAmountDesc(LocalDate startDate, LocalDate endDate);
}