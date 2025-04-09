package br.com.chat.servico;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.chat.dto.MensagemDto;
import br.com.chat.entidade.Mensagem;
import br.com.chat.repositorio.MensagemRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatbotServico {
	
	
	private final MensagemRepositorio mensagemRepositorio;

	public String processandoMensagem(MensagemDto mensagemDto) {
		var mensagemdoUsuario = Mensagem.builder()
				.conteudo(mensagemDto.getConteudo())
				.remetente("usuario")
				.dataHora(LocalDateTime.now())
				.etapa("EM PROCESSAMENTO!")
				.build();
		    mensagemRepositorio.save(mensagemdoUsuario);    
		
		String respostaChatbot= gerarResposta(mensagemDto.getConteudo());
		   // 3. Salva resposta do bot
           var resposta = Mensagem.builder()
                .conteudo(respostaChatbot)
                .remetente("bot")
                .dataHora(LocalDateTime.now())
                .etapa("RESPONDIDO")
                .build();

        mensagemRepositorio.save(resposta);

        // 4. Retorna resposta
        return respostaChatbot;
	}
	
	private String gerarResposta(String entrada) {
        // Aqui é onde se define a lógica real do bot.
        // Pode ser por etapa, palavra-chave, IA, etc.
        if (entrada.equalsIgnoreCase("oi") || entrada.equalsIgnoreCase("olá")) {
            return "Olá! Como posso te ajudar hoje?";
        } else if (entrada.toLowerCase().contains("horário")) {
            return "Nosso horário de atendimento é das 8h às 18h, de segunda a sexta.";
        } else if (entrada.toLowerCase().contains("tchau")) {
            return "Até logo! Se precisar, estou por aqui.";
        } else {
            return "Desculpe, não entendi. Pode reformular?";
        }
    }
	
	
	public List<Mensagem> listarPorRemetente(String remetente) {
	    return mensagemRepositorio.findByRemetente(remetente);
	}
	
	public List<Mensagem> listarHistorico() {
	    return mensagemRepositorio.findAll();
	}
	
}
