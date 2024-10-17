package com.bay.genericai;

import com.bay.aiclient.AiClient;
import com.bay.aiclient.AiClientJava;
import com.bay.aiclient.domain.GenerateTextRequest;
import com.bay.aiclient.domain.GenerateTextResponse;
import kotlin.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AiClient.Builder clientBuilder = AiClient.Companion.getBuilder(AiClient.Type.OPEN_AI);
        clientBuilder.setApiAky("...");
        clientBuilder.setDefaultModel("gpt-4o");
        AiClient client = clientBuilder.build();
        AiClientJava javaClient = new AiClientJava(client);

        GenerateTextRequest.Builder requestBuilder = client.textGenerationRequestBuilder();
        requestBuilder.setPrompt("When the first LLM were released?");
        CompletableFuture<Result<GenerateTextResponse>> response = javaClient.generateText(requestBuilder.build());
        response.join();
        if (response.isDone()) {
            System.out.println(response.get());
        }
    }
}
