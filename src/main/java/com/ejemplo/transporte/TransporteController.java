package com.ejemplo.transporte;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransporteController {

    private List<Vehiculo> flota = new ArrayList<>();
    private Map<String, String> conductores = new HashMap<>();

    public TransporteController() {
        flota.add(new Bus("BUS101", 40, "Centro-Norte"));
        flota.add(new Bus("BUS102", 35, "Sur-Occidente"));
        flota.add(new Taxi("TAX201", 4, "Taxis Bogotá"));
        flota.add(new Taxi("TAX202", 4, "Taxis Bogotá"));
        flota.add(new Vehiculo("VEH301", 8));

        conductores.put("BUS101", "Carlos");
        conductores.put("TAX201", "Marta");
        conductores.put("VEH301", "Luis");
    }

    @GetMapping("/vehiculos")
    public List<Vehiculo> listar() {
        return flota;
    }

    @GetMapping("/vehiculos/placa/{placa}")
    public String buscarPorPlaca(@PathVariable String placa) {
        for (Vehiculo v : flota) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v.descripcion();
            }
        }
        return "No se encontró el vehículo " + placa;
    }

    @GetMapping("/vehiculos/tarifa/{km}")
    public List<String> tarifas(@PathVariable double km) {
        List<String> resultado = new ArrayList<>();
        for (Vehiculo v : flota) {
            resultado.add(v.descripcion() + " -> $" + v.calcularTarifa(km));
        }
        return resultado;
    }

    @GetMapping("/vehiculos/capacidad/{minima}")
    public List<Vehiculo> porCapacidad(@PathVariable int minima) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : flota) {
            if (v.getCapacidad() >= minima) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    @GetMapping("/conductor/{placa}")
    public String conductor(@PathVariable String placa) {
        String clave = placa.toUpperCase();
        if (conductores.containsKey(clave)) {
            return "El conductor de " + clave + " es " + conductores.get(clave);
        } else {
            return "Sin conductor asignado";
        }
    }

    // Reto opcional
    @GetMapping("/vehiculos/mayor-capacidad")
    public Vehiculo mayorCapacidad() {
        Vehiculo mayor = flota.get(0);
        for (Vehiculo v : flota) {
            if (v.getCapacidad() > mayor.getCapacidad()) {
                mayor = v;
            }
        }
        return mayor;
    }

    // Reto extra
    @GetMapping("/vehiculos/total-capacidad")
    public String totalCapacidad() {
        int total = 0;
        for (Vehiculo v : flota) {
            total += v.getCapacidad();
        }
        return "Capacidad total de la flota: " + total + " pasajeros";
    }
}