package cs.sonu.financialAssitant.tool;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import cs.sonu.financialAssitant.entity.Expense;
import cs.sonu.financialAssitant.service.ExpenseService;
import dev.langchain4j.agent.tool.Tool;

@Component
@RequiredArgsConstructor
public class ExpenseTools {

    private final ExpenseService expenseService;

    @Tool("Record a new expense with the given amount, category, and optional description.")
    public String addExpense(Double amount, String category, String description) {
        Expense expense = expenseService.addExpense(amount, category, null, description);
        return String.format("Successfully recorded expense of ₹%.2f for %s.", expense.getAmount(), expense.getCategory());
    }

    @Tool("Get the total expenses spent in the current month.")
    public Double getTotalExpenseForCurrentMonth() {
        return expenseService.getTotalExpenseForCurrentMonth();
    }

    @Tool("Get the total expenses spent on a specific category in the current month.")
    public Double getTotalExpenseByCategoryForCurrentMonth(String category) {
        return expenseService.getTotalExpenseByCategoryForCurrentMonth(category);
    }

    @Tool("Get details of the biggest single expense in the current month.")
    public String getBiggestExpenseForCurrentMonth() {
        Expense expense = expenseService.getBiggestExpenseForCurrentMonth();
        if (expense == null) {
            return "No expenses recorded for this month yet.";
        }
        return String.format("Biggest expense was ₹%.2f for %s on %s.", 
                expense.getAmount(), expense.getCategory(), expense.getDate());
    }
}