package br.org.sp.fatec.lojavirtual.util;

public class UtilitarioTexto {

    public static String limparCaracteresEspeciais(String texto) {
        if(texto == null){ // Retorna caso nulo para não ter erro
            return texto;
        }
        texto = texto.replaceAll("\\.", ""); //Remover pontos
        texto = texto.replaceAll("\\(", ""); //Remover parenteses
        texto = texto.replaceAll("\\)", ""); //Remover parenteses
        texto = texto.replaceAll("\\s", ""); //Remover espaço, tab, etc
        texto = texto.replaceAll("-", ""); //Remover hífen
        return texto;
    }
}
