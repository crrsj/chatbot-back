package br.com.chat.controle;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.chat.dto.MensagemDto;
import br.com.chat.entidade.Mensagem;
import br.com.chat.servico.ChatbotServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatbotControle {
	
	private final ChatbotServico chatbotServico;

	@PostMapping
	@Operation(summary = "Endpoint responsável por enviar e salvar mensagens.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
    public ResponseEntity<String> receberMensagem(@RequestBody MensagemDto mensagemDto) {
        var resposta = chatbotServico.processandoMensagem(mensagemDto);
        return ResponseEntity.ok(resposta);
    }
	
	@GetMapping("/historico")
	@Operation(summary = "Endpoint responsável por buscar histórico de mensagens, se passar parâmetro busca por remetente.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	public ResponseEntity<List<Mensagem>> listarMensagens(
	        @RequestParam(required = false) String remetente
	) {
	    if (remetente != null) {
	        return ResponseEntity.ok(chatbotServico.listarPorRemetente(remetente));
	    }
	    return ResponseEntity.ok(chatbotServico.listarHistorico()); 
	}
}
