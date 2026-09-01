package cs.sonu.financialAssitant.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import cs.sonu.financialAssitant.entity.Income;
import cs.sonu.financialAssitant.repository.IncomeRepository;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public Income addIncome(Double amount, String source, LocalDate date, String description) {
        Income income = new Income();
        income.setAmount(amount);
        income.setSource(source);
        income.setDate(date != null ? date : LocalDate.now());
        income.setDescription(description);
        return incomeRepository.save(income);
    }

    public Double getTotalIncomeForCurrentMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();
        Double total = incomeRepository.getTotalIncomeBetween(start, end);
        return total != null ? total : 0.0;
    }
}