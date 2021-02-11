package br.com.viasoft.livros.listener;

import br.com.viasoft.livros.event.ClientePresave;
import org.springframework.context.ApplicationListener;

import java.util.Optional;

public class CpfComecaComNumeroPar implements ApplicationListener<ClientePresave> {

    @Override
    public void onApplicationEvent(ClientePresave clientePresave) {
        var entidade = clientePresave.getCliente();
        Optional<Character> primeiroDigito = Optional.
                ofNullable(entidade.getCpf().charAt(0));

        if (primeiroDigito.isPresent()) {
            String texto = Integer.parseInt(String.valueOf(primeiroDigito.get()))
                   % 2 == 0 ? "É Par" : "É Impar";
            System.out.println(texto);
        }
    }
}
