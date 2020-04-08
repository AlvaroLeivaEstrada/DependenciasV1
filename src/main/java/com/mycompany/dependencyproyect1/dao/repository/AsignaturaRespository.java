/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dependencyproyect1.dao.repository;

import com.mycompany.dependencyproyect1.dao.entity.Asignatura;
import com.mycompany.dependencyproyect1.dao.entity.Directorio;
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
public class AsignaturaRespository extends DataBaseHandler {

    public boolean save(Asignatura asignatura) {
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO `ASIGNATURA`(`Nombre`,`Clave`,`Curso`,`Titulacion`) "
                    + "VALUES ("
                    + "'" + asignatura.getNombre() + "',"
                    + "'" + asignatura.getClave() + "',"
                    + "'" + asignatura.getCurso() + "',"
                    + "'" + asignatura.getTitulacion() + "'"
                    + ");";
            boolean result = stmt.execute(query);

            conn.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Asignatura> findAll() {
        List<Asignatura> asignaturas = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ASIGNATURA");
            while (rs.next()) {

                Asignatura asignatura = new Asignatura();
                asignatura.setIdASIGNATURA(rs.getInt(1));
                asignatura.setNombre(rs.getString(2));
                asignatura.setClave(rs.getString(3));
                asignatura.setCurso(rs.getString(4));
                asignatura.setTitulacion(rs.getString(5));

                asignaturas.add(asignatura);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return asignaturas;
    }

    public Asignatura findByNombre(String nombre) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ASIGNATURA where nombre='" + nombre + "'");
            while (rs.next()) {

                Asignatura asignatura = new Asignatura();

                asignatura.setIdASIGNATURA(rs.getInt(1));
                asignatura.setNombre(rs.getString(2));
                asignatura.setClave(rs.getString(3));
                asignatura.setCurso(rs.getString(4));
                asignatura.setTitulacion(rs.getString(5));

                return asignatura;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
     public int getIdByNameAsignatura(List<Asignatura> asignaturaList, String nombre) {
        int id = -1;

        for (Asignatura asignatura : asignaturaList) {
            
            if (asignatura.getNombre().equals(nombre)) {
                id = asignatura.getIdASIGNATURA();
            }
            
        }
        return id;
    }
}
