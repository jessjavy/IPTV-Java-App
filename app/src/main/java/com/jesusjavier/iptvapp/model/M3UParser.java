package com.jesusjavier.iptvapp.model;

import java.util.ArrayList;
import java.util.List;

public class M3UParser {
    public List<Channel> parse(String m3uContent) {
        List<Channel> list = new ArrayList<>();
        String[] lines = m3uContent.split("\n");

        String nombre = "";
        String streamUrl = "";
        String logoUrl = "";
        String categoria = "Si categoria";

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("#EXTINF")) {
                //extraemos nombre
                if (line.contains(",")) {
                    nombre = line.substring(line.lastIndexOf(",") + 1).trim();
                }
                //extraemos el logo
                if (line.contains("tv-logo=\"")) {
                    int start = line.indexOf("tv-logo=\"") + 10;
                    int end = line.indexOf("\"", start);
                    logoUrl = line.substring(start, end);
                }
                //extraemos la categoria
                if (line.contains("group-title=\"")) {
                    int start = line.indexOf("group-title\"") + 13;
                    int end = line.indexOf("\"", start);
                    categoria = line.substring(start, end);
                }

            } else if (line.startsWith("http")) {
                streamUrl = line;
                list.add(new Channel(nombre, line.trim(), logoUrl, "Sin categoría"));

                nombre = "";
                streamUrl="";
                logoUrl = "";
                categoria = "Sin categoria";
            }
        }
        return list;
    }
}