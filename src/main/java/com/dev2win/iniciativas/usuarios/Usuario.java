package com.dev2win.iniciativas.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (usuarioId == null) {
            if (other.usuarioId != null)
                return false;
        } else if (!usuarioId.equals(other.usuarioId))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (contrasena == null) {
            if (other.contrasena != null)
                return false;
        } else if (!contrasena.equals(other.contrasena))
            return false;
        if (rol == null) {
            if (other.rol != null)
                return false;
        } else if (!rol.equals(other.rol))
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (perfil == null) {
            if (other.perfil != null)
                return false;
        } else if (!perfil.equals(other.perfil))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [usuarioId = " + usuarioId + ", nombre = " + nombre + ", contrasena = " + contrasena
                + ", rol = "
                + rol
                + ", estado = " + estado + ", perfil = " + perfil + "]";
    }

}
