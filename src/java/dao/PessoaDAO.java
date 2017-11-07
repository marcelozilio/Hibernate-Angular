/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Pessoa;

/**
 * @author Marcelo
 * @since 20/06/2017
 */
public class PessoaDAO implements Idao<Pessoa> {

    EntityManagerFactory factory;
    EntityManager manager;

    public PessoaDAO() {
        factory = Persistence.createEntityManagerFactory("teste");
        manager = factory.createEntityManager();
    }

    /**
     * @param pessoa
     * @return
     */
    @Override
    public boolean salvar(Pessoa pessoa) {
        try {
            manager.getTransaction().begin();
            manager.merge(pessoa);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            factory.close();
        }
        return false;
    }

    /**
     * @return lista de pessoas
     */
    @Override
    public List<Pessoa> listar() {
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select pessoa from Pessoa pessoa");
            List<Pessoa> pessoas = query.getResultList();
            manager.getTransaction().commit();
            return pessoas;
        } catch (Exception e) {
            System.out.println("Erro ao listar pessoas: " + e.getMessage());
            manager.getTransaction().rollback();
        } finally {
            manager.close();
            factory.close();
        }
        return null;
    }

    /**
     * @param cod
     * @return pessoa
     */
    @Override
    public Pessoa listar(int cod) {
        try {
            manager.getTransaction().begin();
            Pessoa pessoa = manager.find(Pessoa.class, cod);
            manager.getTransaction().commit();
            return pessoa;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        } finally {
            manager.close();
            factory.close();
        }
        return null;
    }

    @Override
    public boolean deletar(int cod) {
        try {
            manager.getTransaction().begin();
            Pessoa pessoa = manager.find(Pessoa.class, cod);
            manager.remove(pessoa);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        } finally {
            manager.close();
            factory.close();
        }
        return false;
    }
}
