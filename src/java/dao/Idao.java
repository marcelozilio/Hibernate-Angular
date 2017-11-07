/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Marcelo
 * @since 21/06/2017
 * @param <T>
 */
public interface Idao<T> {

    public boolean salvar(T object);

    public T listar(int cod);

    public List<T> listar();

    public boolean deletar(int cod);

}
