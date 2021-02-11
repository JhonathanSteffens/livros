package br.com.viasoft.livros.event;

import br.com.viasoft.livros.model.Cliente;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ClientePresave  extends ApplicationEvent {

    @Getter
    private Cliente cliente;

    public ClientePresave(Object source, Cliente cliente) {
        super(source);
        this.cliente = cliente;
    }
}
