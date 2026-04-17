package libreria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lisan
 */
public class SimuladorEnergia {
    // Tarifa promedio  en México
    private final double TARIFA_BASE = 0.85; 

    public double calcularConsumo(int watts, int horasUso) {
        return watts * horasUso;
    }

    public double calcularCostoMensual(double totalWatts) {
        double kwh = totalWatts / 1000;
        return kwh * TARIFA_BASE * 30; //30 días
    }
    
    public String obtenerEstadoEficiencia(double totalWatts) {
        if (totalWatts <= 0) return "Sin consumo";
        if (totalWatts < 500) return "Eficiente (A)";
        if (totalWatts < 1500) return "Moderado (B)";
        return "Alto Consumo (C)";
    }
}
