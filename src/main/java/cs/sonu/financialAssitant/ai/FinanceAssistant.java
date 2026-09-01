package cs.sonu.financialAssitant.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface FinanceAssistant {

    @SystemMessage({
        "You are a helpful, professional personal finance assistant.",
        "Your job is to help users track expenses, incomes, and budgets using the provided tools.",
        "Always respond in a natural, friendly, and concise conversational tone matching the currency (₹)."
    })
    String chat(@MemoryId Long userId, @UserMessage String userMessage);
}