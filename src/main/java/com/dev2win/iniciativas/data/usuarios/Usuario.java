package com.dev2win.iniciativas.data.usuarios;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usuarioId;
    private String nombre;
    private String contrasena;
    private String rol;
    private String estado;
    private String perfil;

    public Usuario(String nombre, String contrasena, String rol, String estado, String perfil) {
        this.nombre = nombre;
        this.rol = rol;
        // setRol(rol);
        this.estado = estado;
        this.perfil = perfil;
        // setPerfil(perfil);
        this.contrasena = contrasena;
    }

    public Usuario() {

    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        /*
         * switch (rol) {
         * case "Proponente":
         * this.rol = Rol.Proponente;
         * break;
         * case "Administrador":
         * this.rol = Rol.Administrador;
         * break;
         * default:
         * this.rol = Rol.Proponente;
         * break;
         * }
         */
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        /*
         * switch (perfil) {
         * case "Estudiante":
         * this.perfil = Perfil.Estudiante;
         * break;
         * case "Profesor":
         * this.perfil = Perfil.Profesor;
         * break;
         * case "Directivo":
         * this.perfil = Perfil.Directivo;
         * break;
         * default:
         * this.perfil = Perfil.Estudiante;
         * break;
         * }
         */
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
        result = prime * result + ((rol == null) ? 0 : rol.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario)) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(usuarioId, other.usuarioId) &&
               Objects.equals(nombre, other.nombre) &&
               Objects.equals(contrasena, other.contrasena) &&
               Objects.equals(rol, other.rol) &&
               Objects.equals(estado, other.estado) &&
               Objects.equals(perfil, other.perfil);
    }
    
    @Override
    public String toString() {
        return String.format("Usuario [usuarioId=%s, nombre=%s, contrasena=%s, rol=%s, estado=%s, perfil=%s]",
                             usuarioId, nombre, contrasena, rol, estado, perfil);
    }

}
