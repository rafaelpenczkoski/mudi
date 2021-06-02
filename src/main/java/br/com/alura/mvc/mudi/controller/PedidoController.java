package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
        if (result.hasErrors()) {
            return "pedido/formulario";
        }
        Pedido pedido = requisicaoNovoPedido.toPedido();
        pedidoRepository.save(pedido);
        return "redirect:/home";
    }
}
