import java.util.Date;
import java.util.List;

public class Torneo {
    public Date fechainicio;
    public Date fechafinal;
    public List<Equipo> equipostorneo;
    public String tipotorneo;
    public String campeon;

    public Torneo(Date fechainicio, Date fechafinal, List<Equipo> equipostorneo, String tipotorneo, String campeon) {
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
        this.equipostorneo = equipostorneo;
        this.tipotorneo = tipotorneo;
        this.campeon = campeon;
    }

    // gets y sets
    public Date getFechainicio() {
        return fechainicio;
    }
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }
    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public List<Equipo> getEquipostorneo() {
        return equipostorneo;
    }
    public void setEquipostorneo(List<Equipo> equipostorneo) {
        this.equipostorneo = equipostorneo;
    }

    public String getTipotorneo() {
        return tipotorneo;
    }
    public void setTipotorneo(String tipotorneo) {
        this.tipotorneo = tipotorneo;
    }

    public String getCampeon() {
        return campeon;
    }
    public void setCampeon(String campeon) {
        this.campeon = campeon;
    }


}
