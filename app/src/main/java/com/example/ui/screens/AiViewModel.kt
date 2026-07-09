package com.example.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BuildConfig
import com.example.ai.Content
import com.example.ai.GenerateContentRequest
import com.example.ai.Part
import com.example.ai.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AiViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AiUiState>(AiUiState.Idle)
    val uiState: StateFlow<AiUiState> = _uiState.asStateFlow()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    fun sendMessage(prompt: String) {
        if (prompt.isBlank()) return
        
        val userMsg = ChatMessage(prompt, isUser = true)
        _messages.value = _messages.value + userMsg
        _uiState.value = AiUiState.Loading

        viewModelScope.launch {
            try {
                val apiKey = BuildConfig.GEMINI_API_KEY
                
                val historyContents = _messages.value.map {
                    Content(parts = listOf(Part(text = it.text)))
                }

                val systemInst = Content(parts = listOf(Part(text = "You are MOONLEMP AI, an advanced AI study assistant. Answer accurately and directly.")))

                val request = GenerateContentRequest(
                    contents = historyContents,
                    systemInstruction = systemInst
                )

                val response = RetrofitClient.service.generateContent(apiKey, request)
                val responseText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "No response"
                
                _messages.value = _messages.value + ChatMessage(responseText, isUser = false)
                _uiState.value = AiUiState.Success
            } catch (e: Exception) {
                _uiState.value = AiUiState.Error(e.message ?: "Unknown error")
                _messages.value = _messages.value + ChatMessage("Error: ${e.message}", isUser = false)
            }
        }
    }
}

data class ChatMessage(val text: String, val isUser: Boolean)

sealed class AiUiState {
    object Idle : AiUiState()
    object Loading : AiUiState()
    object Success : AiUiState()
    data class Error(val message: String) : AiUiState()
}
