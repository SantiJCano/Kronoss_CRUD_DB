package model;

import java.time.LocalDateTime;

public class Tarea extends Recordatorio{
    private int prioridad;

    public Tarea() {
    }

    public Tarea(Integer id, String titulo, LocalDateTime fecha_Hora, String tipo, int prioridad) {
        super(id, titulo, fecha_Hora, tipo);
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
