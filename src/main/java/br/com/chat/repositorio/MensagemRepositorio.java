package br.com.chat.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.chat.entidade.Mensagem;

public interface MensagemRepositorio extends JpaRepository<Mensagem, Long> {
	
	List<Mensagem> findByRemetente(String remetente);

}
