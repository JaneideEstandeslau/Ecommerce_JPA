package conhecendoEntityManager;

import org.junit.Test;

import model.Pedido;
import model.StatusPedido;
import util.EntityManagerTest;

public class FlushTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void chamarFlush() {
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

//            entityManager.flush();

            if (pedido.getPagamentoCartao() == null) {
                throw new RuntimeException("Pedido ainda não foi pago.");
            }

//            Uma consulta obriga o JPA a sincronizar o que ele tem na memória (sem usar o flush explicitamente).
//            Pedido pedidoPago = entityManager
//                    .createQuery("select p from Pedido p where p.id = 1", Pedido.class)
//                    .getSingleResult();
//            Assert.assertEquals(pedido.getStatus(), pedidoPago.getStatus());
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}