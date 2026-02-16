package com.finapp.finance_calculator.aichatbot;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/chat")
public class AiChatController {

    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @PostMapping
    public String chat(@RequestBody String message) {
        return aiChatService.askFinanceBot(message);
    }
}
