/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pdfimporter.bean;


import com.mycompany.pdfimporter.logic.CrudLogic;
import com.mycompany.pdfimporter.util.exception.ErroSistemaException;
import com.mycompany.pdfimporter.util.exception.ErroUsuarioException;
import com.mycompany.pdfimporter.util.msg.JSFUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;

public abstract class CrudBean<E, L extends CrudLogic<E> > extends JSFUtil{
    
    public enum EstadoTela {
        BUSCANDO,
        INSERINDO,
        EDITANDO
    }
    
    @Getter
    private EstadoTela estadoTela = EstadoTela.BUSCANDO;

    @Getter @Setter
    private E entidade;
    private Class<E> classeEntidade;
    @Getter
    private List<E> entidades;
    
    
    public CrudBean(Class<E> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }
    
    public void novaInstancia(){
        try {
            entidade = classeEntidade.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void novo(){
        novaInstancia();
        estadoTela = EstadoTela.INSERINDO;
    }
    public void salvar(){
        try {
            entidade = getLogic().salvar(entidade);
            if(entidades == null){
                entidades = new ArrayList<>();
            }
            entidades.add(entidade);
            novaInstancia();
            addInfoMensagem("Cliente salvo com sucesso.");
            estadoTela = EstadoTela.BUSCANDO;
        } catch (ErroUsuarioException ex) {
            addAvisoMensagem(ex);
        } catch (ErroSistemaException ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addErroMensagem(ex);
        }
        
    }
    public void editar(E endidade){
        this.entidade = endidade;
        estadoTela = EstadoTela.EDITANDO;
    }
    
    public void buscar() {
        if(!estadoTela.equals(EstadoTela.BUSCANDO)) {
            estadoTela = EstadoTela.BUSCANDO;
            return;
        }
        this.entidades = getLogic().buscar();
    }

    public abstract L getLogic();
    
}
