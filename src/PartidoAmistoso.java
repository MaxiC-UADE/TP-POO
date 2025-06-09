import java.util.Date;
import java.util.List;

public class PartidoAmistoso extends Partido {
    public PartidoAmistoso(String horario, int cancha, Date fecha, List<Equipo> equipos) {
        this.horario = horario;
        this.cancha = cancha;
        this.fecha = fecha;
        this.equipos = equipos;
    }
}
