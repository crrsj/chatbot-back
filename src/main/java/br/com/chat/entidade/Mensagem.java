package br.com.chat.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class Mensagem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String remetente;
    @Column(columnDefinition = "TEXT")
	private String conteudo;
	private LocalDateTime dataHora;
    private String etapa;
   
}