package mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import model.Cliente;
import model.SexoCliente;
import util.EntityManagerTest;

public class MapeandoEnumeracoesTest extends EntityManagerTest{
	
	@Test
    public void testarEnum() {
        Cliente cliente = new Cliente();
        cliente.setNome("José Mineiro");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
}
