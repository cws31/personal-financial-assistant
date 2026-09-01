package cs.sonu.financialAssitant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import cs.sonu.financialAssitant.entity.Expense;
import cs.sonu.financialAssitant.repository.ExpenseRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense addExpense(Double amount, String category, LocalDate date, String description) {
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(date != null ? date : LocalDate.now());
        expense.setDescription(description);
        return expenseRepository.save(expense);
    }

    public Double getTotalExpenseForCurrentMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();
        Double total = expenseRepository.getTotalExpenseBetween(start, end);
        return total != null ? total : 0.0;
    }

    public Double getTotalExpenseByCategoryForCurrentMonth(String category) {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();
        Double total = expenseRepository.getTotalExpenseByCategoryAndDateBetween(category, start, end);
        return total != null ? total : 0.0;
    }

    public Expense getBiggestExpenseForCurrentMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();
        return expenseRepository.findTopByDateBetweenOrderByAmountDesc(start, end);
    }
}