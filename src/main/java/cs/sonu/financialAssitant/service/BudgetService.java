package cs.sonu.financialAssitant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import cs.sonu.financialAssitant.entity.Budget;
import cs.sonu.financialAssitant.repository.BudgetRepository;

import java.time.YearMonth;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public Budget setMonthlyBudget(Double monthlyLimit) {
        String monthYear = YearMonth.now().toString(); // e.g., "2026-09"
        Optional<Budget> existingBudget = budgetRepository.findByMonthYear(monthYear);

        Budget budget = existingBudget.orElse(new Budget());
        budget.setMonthYear(monthYear);
        budget.setMonthlyLimit(monthlyLimit);

        return budgetRepository.save(budget);
    }

    public Double getMonthlyBudgetLimit() {
        String monthYear = YearMonth.now().toString();
        return budgetRepository.findByMonthYear(monthYear)
                .map(Budget::getMonthlyLimit)
                .orElse(0.0);
    }
}