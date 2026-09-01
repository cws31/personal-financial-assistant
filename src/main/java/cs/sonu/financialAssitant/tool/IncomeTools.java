package cs.sonu.financialAssitant.tool;


import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import cs.sonu.financialAssitant.entity.Income;
import cs.sonu.financialAssitant.service.IncomeService;

@Component
@RequiredArgsConstructor
public class IncomeTools {

    private final IncomeService incomeService;

    @Tool("Record a new income source with the given amount and source description.")
    public String addIncome(Double amount, String source, String description) {
        Income income = incomeService.addIncome(amount, source, null, description);
        return String.format("Successfully recorded income of ₹%.2f from %s.", income.getAmount(), income.getSource());
    }

    @Tool("Get the total income earned in the current month.")
    public Double getTotalIncomeForCurrentMonth() {
        return incomeService.getTotalIncomeForCurrentMonth();
    }
}