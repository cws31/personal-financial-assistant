package cs.sonu.financialAssitant.controller;



import cs.sonu.financialAssitant.ai.FinanceAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final FinanceAssistant financeAssistant;

    @PostMapping
    public ResponseEntity<String> chat(
            @RequestParam(defaultValue = "1") Long userId, 
            @RequestBody ChatRequest request) {
        String response = financeAssistant.chat(userId, request.message());
        return ResponseEntity.ok(response);
    }

    public record ChatRequest(String message) {}
}