/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pdfimporter.util.msg;


import com.mycompany.pdfimporter.util.exception.ErroSistemaException;
import com.mycompany.pdfimporter.util.exception.ErroUsuarioException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil implements Serializable {
 
    public void addMensagem(FacesMessage.Severity severity, String resumo, String detalhe){
        FacesMessage fm = new FacesMessage(severity, resumo, detalhe);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public void addInfoMensagem(String resumo, String detalhe){
        addMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }
    public void addInfoMensagem(String mensagem){
        addInfoMensagem("Info", mensagem);
    }
    public void addAvisoMensagem(String resumo, String detalhe){
        addMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }
    public void addAvisoMensagem(String mensagem){
        addAvisoMensagem("Aviso", mensagem);
    }
    public void addAvisoMensagem(ErroUsuarioException ex) {
        addAvisoMensagem(ex.getMessage());
    }
    public void addErroMensagem(String resumo, String detalhe){
        addMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }
    public void addErroMensagem(String mensagem){
        addErroMensagem("Erro", mensagem);
    }
    public void addErroMensagem(ErroSistemaException ex) {
        addErroMensagem(ex.getMessage());
    }
}
