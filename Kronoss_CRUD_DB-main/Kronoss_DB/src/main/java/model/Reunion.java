package model;

public class Reunion extends Recordatorio {
    private String ubicacion;
    private String enlaceVirtual;
    private String participantes;
    private String organizador;

    public Reunion() {
    }

    public Reunion(String ubicacion, String enlaceVirtual, String participantes, String organizador) {
        this.ubicacion = ubicacion;
        this.enlaceVirtual = enlaceVirtual;
        this.participantes = participantes;
        this.organizador = organizador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEnlaceVirtual() {
        return enlaceVirtual;
    }

    public void setEnlaceVirtual(String enlaceVirtual) {
        this.enlaceVirtual = enlaceVirtual;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }
}
