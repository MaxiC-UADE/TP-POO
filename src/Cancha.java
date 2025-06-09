import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Cancha {
    public String direccion;
    public int numero;
    public List<Time> horarios;

    public Cancha(String direccion, int numero, List<Time> horarios) {
        this.direccion = direccion;
        this.numero = numero;
        this.horarios = horarios;
    }

    // GETS Y SETS

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Time> getHorarios() {
        return horarios;
    }
    public void setHorarios(List<Time> horarios) {
        this.horarios = horarios;
    }
}
