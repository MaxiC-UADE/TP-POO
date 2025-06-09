import java.util.Date;
import java.util.List;

public class PartidoTorneo extends Partido {
    private int resultado;
    private String fase;

    public PartidoTorneo(String horario, int cancha, Date fecha, List<Equipo> equipos) {
        this.horario = horario;
        this.cancha = cancha;
        this.fecha = fecha;
        this.equipos = equipos;
    }

    // GETS Y SETS
    public String getResultado() {
        return resultado;
    }
    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getFase() {
        return fase;
    }
    public void setFase(String fase) {
        this.fase = fase;
    }
}
