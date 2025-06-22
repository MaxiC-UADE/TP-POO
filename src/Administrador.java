public class Administrador {
    public String emailAdmin;
    public String contraseñaAdmin;

    public Administrador(String emailAdmin, String contraseñaAdmin) {
        this.emailAdmin = emailAdmin;
        this.contraseñaAdmin = contraseñaAdmin;
    }

    // GETS Y SETS

    public String getEmailAdmin() {
        return emailAdmin;
    }
    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getContraseñaAdmin() {
        return contraseñaAdmin;
    }
    public void setContraseñaAdmin(String contraseñaAdmin) {
        this.contraseñaAdmin = contraseñaAdmin;
    }

    // METODOS
    public void crearAmistoso() {

    }
    public void crearTorneo() {

    }
}
