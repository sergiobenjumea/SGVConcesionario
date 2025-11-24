package modelo.util;

import java.text.DecimalFormat;

public class Formato {
    // Formato: Signo pesos, separador de miles y 2 decimales
    private static final DecimalFormat DF = new DecimalFormat("$ #,##0.00");
    
    // Convierte número a texto bonito ($ 1,000,000.00)
    public static String moneda(double valor) {
        return DF.format(valor);
    }
    
    // Convierte texto bonito ($ 1,000,000.00) a número real (1000000.0)
    public static double remover(String valor) {
        try {
            if (valor == null || valor.isEmpty()) return 0.0;
            String limpio = valor.replace("$", "").replace(",", "").replace(" ", "");
            return Double.parseDouble(limpio);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}