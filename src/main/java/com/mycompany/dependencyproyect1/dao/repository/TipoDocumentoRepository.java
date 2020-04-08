/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dependencyproyect1.dao.repository;

import com.mycompany.dependencyproyect1.dao.entity.TipoDisco;
import com.mycompany.dependencyproyect1.dao.entity.TipoDocumento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fercholeiva
 */
public class TipoDocumentoRepository extends DataBaseHandler {

    public boolean save(String nombre) {
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            boolean result = stmt.execute("INSERT INTO `TIPO_DOCUMENTO`(`nombre`) VALUES ('" + nombre + "');");

            conn.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<TipoDocumento> findAll() {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from TIPO_DOCUMENTO");
            while (rs.next()) {

                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setIdTIPODOCUMENTO(rs.getInt(1));
                tipoDocumento.setNombre(rs.getString(2));

                tipoDocumentos.add(tipoDocumento);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tipoDocumentos;
    }

    public TipoDocumento findByNombre(String nombre) {

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from TIPO_DOCUMENTO where nombre='" + nombre + "'");
            while (rs.next()) {

                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setIdTIPODOCUMENTO(rs.getInt(1));
                tipoDocumento.setNombre(rs.getString(2));

                return tipoDocumento;
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
