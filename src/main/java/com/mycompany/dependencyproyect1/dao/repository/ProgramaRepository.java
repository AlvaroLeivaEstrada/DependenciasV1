/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dependencyproyect1.dao.repository;

import com.mycompany.dependencyproyect1.dao.entity.Asignatura;
import com.mycompany.dependencyproyect1.dao.entity.Disco;
import com.mycompany.dependencyproyect1.dao.entity.Programa;
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
public class ProgramaRepository extends DataBaseHandler {

    public boolean save(String nombre, String nCorto, String version, String fabricante) {
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            boolean result = stmt.execute("INSERT INTO `PROGRAMA`(`Nombre`,`Nombre_corto`,`Version`,`Fabricante`) VALUES ('" + nombre + "','" + nCorto + "','" + version + "','" + fabricante + "');");
            conn.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Programa> findAll() {
        List<Programa> programas = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from PROGRAMA");
            while (rs.next()) {

                Programa programa = new Programa();
                programa.setIdPROGRAMA(rs.getInt(1));
                programa.setNombre(rs.getString(2));
                programa.setNombrecorto(rs.getString(3));
                programa.setVersion(rs.getString(4));
                programa.setFabricante(rs.getString(5));

                programas.add(programa);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return programas;
    }

    public Programa findByNombre(String nombre) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from PROGRAMA where nombre='" + nombre + "'");
            while (rs.next()) {

                Programa programa = new Programa();
                programa.setIdPROGRAMA(rs.getInt(1));
                programa.setNombre(rs.getString(2));
                programa.setNombrecorto(rs.getString(3));
                programa.setVersion(rs.getString(4));
                programa.setFabricante(rs.getString(5));

                return programa;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
       public int getIdByNamePrograma(List<Programa> programaList, String nombre) {
        int id = -1;

        for (Programa programa : programaList) {
            
            if (programa.getNombre().equals(nombre)) {
                id = programa.getIdPROGRAMA();
            }
            
        }
        return id;
    }
    

}
