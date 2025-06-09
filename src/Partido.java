import java.util.Date;
import java.util.List;

public abstract class Partido {
    public String horario;
    public int cancha;
    public Date fecha;
    public List<Equipo> equipos;

    // GETS Y SETS
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCancha() {
        return cancha;
    }
    public void setCancha(int cancha) {
        this.cancha = cancha;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
