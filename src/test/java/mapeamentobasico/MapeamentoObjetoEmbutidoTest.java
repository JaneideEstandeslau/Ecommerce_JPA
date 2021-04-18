package mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import model.Cliente;
import model.EnderecoEntregaPedido;
import model.Pedido;
import model.StatusPedido;
import util.EntityManagerTest;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {
    	
    	Cliente cliente = entityManager.find(Cliente.class, 1);
    	
        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("00000-00");
        endereco.setLogradouro("Rua das Laranjeiras");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Uberlāndia");
        endereco.setEstado("MG");

        Pedido pedido = new Pedido();
//        pedido.setId(1);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(endereco);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega());
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());
    }
}