package model;

import java.time.LocalDateTime;

public class Personalizado extends  Recordatorio {
    private String categoria;
    private String notas;

    public Personalizado() {
    }

    public Personalizado(Integer id, String titulo, LocalDateTime fecha_Hora, String tipo, String categoria, String notas) {
        super(id, titulo, fecha_Hora, tipo);
        this.categoria = categoria;
        this.notas = notas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
