package model;
import java.time.LocalDateTime;

public class Evento extends Recordatorio {
    private String ubicacion;


    public Evento() {

    }

    public Evento(Integer id, String titulo, LocalDateTime fecha_Hora, String tipo, String ubicacion) {
        super(id, titulo, fecha_Hora, tipo);
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}