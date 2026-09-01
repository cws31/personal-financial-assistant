package cs.sonu.financialAssitant.ai;

import cs.sonu.financialAssitant.tool.BudgetTools;
import cs.sonu.financialAssitant.tool.ExpenseTools;
import cs.sonu.financialAssitant.tool.IncomeTools;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${langchain4j.google-ai-gemini.chat-model.api-key}")
    private String apiKey;

    @Value("${langchain4j.google-ai-gemini.chat-model.model-name:gemini-2.5-flash}")
    private String modelName;

    @Bean
    public ChatModel chatModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.withMaxMessages(20);
    }

    @Bean
    public FinanceAssistant financeAssistant(
            ChatModel chatModel,
            ChatMemoryProvider chatMemoryProvider,
            ExpenseTools expenseTools,
            IncomeTools incomeTools,
            BudgetTools budgetTools) {
        return AiServices.builder(FinanceAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(chatMemoryProvider)
                .tools(expenseTools, incomeTools, budgetTools)
                .build();
    }
}