/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pdfimporter.bean;


import com.mycompany.pdfimporter.entity.Documento;
import com.mycompany.pdfimporter.logic.DocumentoLogic;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import org.primefaces.event.FileUploadEvent;

@Named
@SessionScoped
public class DocumentoBean extends CrudBean<Documento, DocumentoLogic>{

    @Inject
    private DocumentoLogic logic;
    
    
    @Getter
    private Documento docVisualiza;
    
    public DocumentoBean() {
        super(Documento.class);
    }
    
    public void enviarArquivo(FileUploadEvent event){
	String nome = event.getFile().getFileName();
	byte[] documento = event.getFile().getContent();
	getEntidade().setDocumento(documento);
	getEntidade().setNome(nome);
    }
    
    public void visuzaliaDoc(Documento doc){
	docVisualiza = doc;
    }
    
    public void fechaVisualizacao(){
	System.out.println("\n\n\n Fechando visualização \n\n\n");
	docVisualiza = null;
    }
    
    @Override
    public DocumentoLogic getLogic() {
        return logic;
    }
    
    
}