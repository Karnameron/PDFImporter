package com.mycompany.pdfimporter.dao;

import com.mycompany.pdfimporter.entity.Documento;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DocumentoDAO implements Serializable {
    
    public Documento salvar(Documento documento) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "ppw2_trab03" );
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        documento = em.merge(documento);
        em.getTransaction().commit();
        em.close();
        return documento;
    }
    
    public void remover(Documento documento) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "ppw2_trab03" );
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        documento = em.find(Documento.class, documento.getId());
        em.remove(documento);
        em.getTransaction().commit();
        em.close();
    }
    
    public Documento buscarPorID(Integer id) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory( "ppw2_trab03" );
        EntityManager em = factory.createEntityManager();
	Documento doc = em.find(Documento.class, id);
	em.close();
	return doc;
    }
    
    public List<Documento> listar(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "ppw2_trab03" );
        EntityManager em = factory.createEntityManager();
        List<Documento> documentos = em.createQuery("select documento from Documento documento").getResultList();
        return documentos;
    }
}
