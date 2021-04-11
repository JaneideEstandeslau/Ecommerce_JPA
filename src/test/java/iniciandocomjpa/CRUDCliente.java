package iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import model.Cliente;
import util.EntityManagerTest;

public class CRUDCliente extends EntityManagerTest{

	@Test
	public void inserirCliente() {
		
		Cliente cliente = new Cliente();
//		cliente.setId(3);
		cliente.setNome("Janeide Estandeslau");

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
	}
	
	@Test
	public void atualizarObjetoCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("João Fernandes");

		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
		Assert.assertEquals("João Fernandes", clienteVerificacao.getNome());
	}
	
	@Test
	public void atualizarClienteGerenciado() {
		
		Cliente cliente = entityManager.find(Cliente.class, 2);

		entityManager.getTransaction().begin();
		cliente.setNome("Marcos Almeida");
		entityManager.getTransaction().commit();

		entityManager.clear();

		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertEquals("Marcos Almeida", clienteVerificacao.getNome());
	}
	
	@Test
	public void removerCliente() {
		Cliente cliente = entityManager.find(Cliente.class, 3);

		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();

		Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
		Assert.assertNull(clienteVerificacao);
	}
}
