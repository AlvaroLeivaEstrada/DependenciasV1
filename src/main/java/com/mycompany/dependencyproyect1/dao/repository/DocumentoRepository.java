/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dependencyproyect1.dao.repository;

import com.mycompany.dependencyproyect1.dao.entity.Documento;
import com.mycompany.dependencyproyect1.dao.entity.TipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fercholeiva
 */
public class DocumentoRepository extends DataBaseHandler {

    //public int save(int Disco, int Directorio, int tipoDocumento, int programa, int asignatura, String nombre, String creacion, String modificaion, String comentario, String bytes) {
    public Documento save(Documento documento) {
      
    try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String insert = ""
                    + "INSERT INTO `solucion_ejercicio_1`.`DOCUMENTO` "
                    + "("
                    + " `DISCO_idDISCO`,"
                    + " `DIRECTORIO_idDIRECTORIO`,"
                    + " `TIPO_DOCUMENTO_idTIPO_DOCUMENTO`,"
                    + " `PROGRAMA_idPROGRAMA`,"
                    + "  `ASIGNATURA_idASIGNATURA`,"
                    + " `Nombre`,"
                    + " `Fecha_creacion`,"
                    + " `Fecha_modificacion`,"
                    + " `Comentario`,"
                    + " `Tamaño_bytes`"
                    + ") "
                    + "VALUES ( "
                    + " '" + documento.getDisco().getIdDISCO() + "',"
                    + " '" + documento.getDirectorio().getIdDIRECTORIO() + "',"
                    + " '" + documento.getTipoDocumento().getIdTIPODOCUMENTO() + "', "
                    + " '" + documento.getPrograma().getIdPROGRAMA() + "',"
                    + " '" + documento.getAsignatura().getIdASIGNATURA() + "',"
                    + " '" + documento.getNombre() + "',"
                    + " '" + documento.getFechacreacion() + "',"
                    + " '" + documento.getFechamodificacion() + "',"
                    + " '" + documento.getComentario() + "',"
                    + " '" + documento.getTamañobytes()+ "'"
                    + ");";
            PreparedStatement ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            conn.close();
            documento.setIdDOCUMENTO(id);
            return documento;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public List<Documento> findAll() {
        List<Documento> documentos = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from DOCUMENTO");
            while (rs.next()) {

                Documento documento = new Documento();
                documento.setNombre(rs.getString(6));
                documentos.add(documento);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return documentos;
    }
    
    public List<Documento> findByName(String name) {
        List<Documento> documentos = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from DOCUMENTO where name='"+name+"'");
            while (rs.next()) {

                Documento documento = new Documento();
                documento.setNombre(rs.getString(6));
                documentos.add(documento);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return documentos;
    }


}
