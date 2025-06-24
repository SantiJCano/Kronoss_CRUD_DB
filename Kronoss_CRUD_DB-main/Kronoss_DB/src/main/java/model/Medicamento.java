package model;

import java.time.LocalDateTime;

public class Medicamento extends Recordatorio{
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private Integer duracionDias;

    public Medicamento() {
    }

    public Medicamento(Integer id, String titulo, LocalDateTime fecha_Hora, String tipo, String medicamento, String dosis, String frecuencia, Integer duracionDias) {
        super(id, titulo, fecha_Hora, tipo);
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracionDias = duracionDias;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }
}
