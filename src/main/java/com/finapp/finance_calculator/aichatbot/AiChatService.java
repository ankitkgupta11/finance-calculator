package com.finapp.finance_calculator.aichatbot;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    private final ChatClient chatClient;

    public AiChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String askFinanceBot(String userMessage) {

        return chatClient.prompt()
                .system("""
                        You are a professional financial advisor.
                        Only answer questions related to EMI, FD, SIP, investments,
                        loan planning, interest calculation, and finance.
                        If question is unrelated to finance, politely refuse.
                        """)
                .user(userMessage)
                .call()
                .content();
    }
}
