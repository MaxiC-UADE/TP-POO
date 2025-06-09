import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private int dniCapitan;
    private String nombreEquipo;
    private int numeroIntegrantes;
    private String tipoEquipo;
    private List<String> preferencias;
    private List<String> integrantes;
    private List<int> rangoEdad;

    public Equipo(int dniCapitan, String nombreEquipo, int numeroIntegrantes, String tipoEquipo, List<String> preferencias, List<String> integrantes, List<int> rangoEdad) {
        this.dniCapitan = dniCapitan;
        this.nombreEquipo = nombreEquipo;
        this.numeroIntegrantes = numeroIntegrantes;
        this.tipoEquipo = tipoEquipo;
        this.preferencias = preferencias;
        this.integrantes = integrantes;
        this.rangoEdad = rangoEdad;
    }

    // SETS
    public void setDniCapitan(int dniCapitan) {
        this.dniCapitan = dniCapitan;
    }
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    public void setNumeroIntegrantes(int numeroIntegrantes) {
        this.numeroIntegrantes = numeroIntegrantes;
    }
    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }
    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }
    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }
    public void setRangoEdad(List<int> rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    /// GETS
    public int getDniCapitan() {
        return dniCapitan;
    }
    public String getNombreEquipo() {
        return nombreEquipo;
    }
    public int getNumeroIntegrantes() {
        return numeroIntegrantes;
    }
    public String getTipoEquipo() {
        return tipoEquipo;
    }
    public List<String> getPreferencias() {
        return preferencias;
    }
    public List<String> getIntegrantes() {
        return integrantes;
    }
    public List<int> getRangoEdad() {
        return rangoEdad;
    }
}
