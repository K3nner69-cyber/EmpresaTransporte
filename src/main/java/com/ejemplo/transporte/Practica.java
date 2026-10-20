package com.ejemplo.transporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practica {
    public static void main(String[] args) {

        // ===== Etapa 1: Vehiculo con encapsulación =====
        System.out.println("--- Etapa 1 ---");
        Vehiculo v = new Vehiculo("VEH301", 8);
        System.out.println(v.descripcion());
        v.setCapacidad(-5);
        System.out.println("Capacidad tras setCapacidad(-5): " + v.getCapacidad()); // sigue en 8
        v.setCapacidad(10);
        System.out.println("Capacidad tras setCapacidad(10): " + v.getCapacidad()); // ahora 10
        System.out.println("Tarifa 10 km: " + v.calcularTarifa(10));                // 15000.0

        // ===== Etapa 2: herencia y polimorfismo =====
        System.out.println("--- Etapa 2 ---");
        Vehiculo base = new Vehiculo("VEH301", 8);
        Vehiculo bus = new Bus("BUS101", 40, "Centro-Norte");
        Vehiculo taxi = new Taxi("TAX201", 4, "Taxis Bogotá");

        System.out.println(base.descripcion() + " | " + base.calcularTarifa(10));
        System.out.println(bus.descripcion() + " | " + bus.calcularTarifa(10));
        System.out.println(taxi.descripcion() + " | " + taxi.calcularTarifa(10));

        // ===== Etapa 3: colecciones en Java puro =====
        System.out.println("--- Etapa 3 ---");
        List<Vehiculo> flota = new ArrayList<>();
        flota.add(new Bus("BUS101", 40, "Centro-Norte"));
        flota.add(new Bus("BUS102", 35, "Sur-Occidente"));
        flota.add(new Taxi("TAX201", 4, "Taxis Bogotá"));
        flota.add(new Taxi("TAX202", 4, "Taxis Bogotá"));
        flota.add(new Vehiculo("VEH301", 8));
        System.out.println("Vehículos en la flota: " + flota.size());   // 5

        for (Vehiculo ve : flota) {
            System.out.println(ve.descripcion() + " -> $" + ve.calcularTarifa(10));
        }

        int grandes = 0;
        for (Vehiculo ve : flota) {
            if (ve.getCapacidad() > 10) {
                grandes++;
            }
        }
        System.out.println("Vehículos con capacidad mayor a 10: " + grandes);  // 2

        Map<String, String> conductores = new HashMap<>();
        conductores.put("BUS101", "Carlos");
        conductores.put("TAX201", "Marta");
        conductores.put("VEH301", "Luis");

        System.out.println("El conductor de BUS101 es " + conductores.get("BUS101"));
        String buscada = "XYZ999";
        if (conductores.containsKey(buscada)) {
            System.out.println("El conductor de " + buscada + " es " + conductores.get(buscada));
        } else {
            System.out.println("Sin conductor asignado para la placa " + buscada);
        }
    }
}