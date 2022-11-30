/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pdfimporter.logic;

import com.mycompany.pdfimporter.util.exception.ErroSistemaException;
import com.mycompany.pdfimporter.util.exception.ErroUsuarioException;
import java.io.Serializable;
import java.util.List;

public interface CrudLogic<E> extends Serializable{
    
    public E salvar(E entidade) throws ErroUsuarioException, ErroSistemaException;
    public void deletar(E entidade);
    public List<E> buscar();
}
