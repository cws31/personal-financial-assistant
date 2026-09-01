package cs.sonu.financialAssitant.tool;


import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import cs.sonu.financialAssitant.entity.Budget;
import cs.sonu.financialAssitant.service.BudgetService;
import cs.sonu.financialAssitant.service.ExpenseService;

@Component
@RequiredArgsConstructor
public class BudgetTools {

    private final BudgetService budgetService;
    private final ExpenseService expenseService;

    @Tool("Set or update the monthly budget limit.")
    public String setMonthlyBudget(Double monthlyLimit) {
        Budget budget = budgetService.setMonthlyBudget(monthlyLimit);
        return String.format("Monthly budget has been set to ₹%.2f.", budget.getMonthlyLimit());
    }

    @Tool("Get how much of the monthly budget is left by comparing the set limit with total current month expenses.")
    public String getRemainingBudget() {
        Double limit = budgetService.getMonthlyBudgetLimit();
        if (limit == 0.0) {
            return "No monthly budget has been set yet.";
        }
        Double spent = expenseService.getTotalExpenseForCurrentMonth();
        Double remaining = limit - spent;
        return String.format("You've spent ₹%.2f out of your ₹%.2f budget, so you have ₹%.2f remaining.", spent, limit, remaining);
    }
}