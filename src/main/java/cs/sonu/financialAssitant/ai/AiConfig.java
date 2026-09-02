package cs.sonu.financialAssitant.ai;

import cs.sonu.financialAssitant.tool.BudgetTools;
import cs.sonu.financialAssitant.tool.ExpenseTools;
import cs.sonu.financialAssitant.tool.IncomeTools;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AiConfig {

    @Value("${ollama.base-url}")
    private String baseUrl;

    @Value("${ollama.model-name}")
    private String modelName;

    @Bean
    public ChatModel chatModel() {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(0.2) // Low temperature helps tools execute more reliably
                .timeout(Duration.ofSeconds(60))
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