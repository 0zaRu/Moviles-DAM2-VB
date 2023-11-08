package com.example.practica12_alberto_rodriguez;

import java.util.ArrayList;

public class ProductosDisponibles {
    public static ArrayList<Producto> rellenar () {
        ArrayList<Producto> array = new ArrayList<>();

        Producto o1 = new Producto("Pórtatil MSI", "msi", 1537.87, "MSI Raider GE73VR 7RF-258XES - Ordenador portátil de 17.3 FHD (Kabylake i7-7700HQ, RAM de 16 GB DDR4, HDD de 1 TB y SSD de 512 GB, Nvidia GeForce GTX 1070, sin Sistema operativo) Color Negro");
        Producto o2 = new Producto("Pórtatil MSI", "msi", 937.47, "MSI Raider GE73VR 7RF-258XES - Ordenador portátil de 17.3 FHD (Kabylake i5-5700HQ, RAM de 8 GB DDR4, HDD de 1 TB y SSD de 512 GB, Nvidia GeForce GTX 1070, sin Sistema operativo) Color Rojo");
        Producto o3 = new Producto ("Fuente de alimentación EVGA", "fuente", 114.00, "EVGA SuperNOVA 750 G3, 80 Plus Gold 750W, totalmente modular, modo Eco con nuevo ventilador HDB, incluye auto probador encendido, tamaño compacto de 150 mm, fuente de alimentación 220-G3-0750-X2");
        Producto o4 = new Producto ("Fuente de alimentación EVGA", "fuente", 112.00, "EVGA SuperNOVA 650 G3, 80 Plus Gold 750W, totalmente modular, modo Eco con nuevo ventilador HDB, incluye auto probador encendido, tamaño compacto de 150 mm, fuente de alimentación 220-G3-0750-X2");

        array.add(o1);
        array.add(o2);
        array.add(o3);
        array.add(o4);

        return array;
    }
}
