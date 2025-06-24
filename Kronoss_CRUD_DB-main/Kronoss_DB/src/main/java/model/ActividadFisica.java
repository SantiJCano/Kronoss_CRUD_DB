package model;

public class ActividadFisica extends Recordatorio {
    private String actividad;
    private Integer duracion;
    private String nivelIntensidad;

    public ActividadFisica() {
    }

    public ActividadFisica(String actividad, Integer duracion, String nivelIntensidad) {
        this.actividad = actividad;
        this.duracion = duracion;
        this.nivelIntensidad = nivelIntensidad;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getNivelIntensidad() {
        return nivelIntensidad;
    }

    public void setNivelIntensidad(String nivelIntensidad) {
        this.nivelIntensidad = nivelIntensidad;
    }
}
