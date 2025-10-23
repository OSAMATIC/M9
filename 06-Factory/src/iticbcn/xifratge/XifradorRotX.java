public class XifradorRotX {

    public static final char[] LMIN = {
            'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm',
            'n', 'ñ', 'o', 'ó', 'ò', 'ö', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'x', 'y', 'z'
    };

    public static final char[] LMAY = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'Ó', 'Ò', 'Ö', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'X', 'Y', 'Z'
    };
    public static final int[] nombres = { 1, 5, 13 };
    public static final String[] senseXifra = { "larbi87", "El Zorro dorm zzzz", "M9 es molt divertit :)" };
    public String[] xifrat = { xifraRotX(senseXifra[0], nombres[0]), xifraRotX(senseXifra[1], nombres[1]),
            xifraRotX(senseXifra[2], nombres[2]) };

    
    public String xifraRotX(String cadena, int desplaçament) {
        // L= lletra C= Caracter
        String nCadena = "";
        boolean esMajuscula = false;
        int posicio = 0;

        for (int l = 0; l < cadena.length(); l++) {
            if (Character.isUpperCase(cadena.charAt(l)) && Character.isLetter(cadena.charAt(l))) {
                esMajuscula = true;
            } else if (Character.isLowerCase(cadena.charAt(l)) && Character.isLetter(cadena.charAt(l))) {
                esMajuscula = false;
            }
            if (Character.isLetter(cadena.charAt(l))) {
                if (esMajuscula) {
                    for (int c = 0; c < LMAY.length; c++) {
                        if (cadena.charAt(l) == LMAY[c]) {
                            posicio = c + desplaçament;
                            if (posicio >= LMAY.length) {
                                posicio = posicio % LMAY.length;
                            }
                            nCadena = nCadena + LMAY[posicio];
                            break;
                        }
                    }
                } else {
                    for (int c = 0; c < LMIN.length; c++) {
                        if (cadena.charAt(l) == LMIN[c]) {
                            posicio = c + desplaçament;
                            if (posicio >= LMIN.length) {
                                posicio = posicio % LMIN.length;
                            }
                            nCadena = nCadena + LMIN[posicio];
                            break;
                        }
                    }
                }
            } else {
                nCadena = nCadena + cadena.charAt(l);
            }
        }
        return nCadena;
    }

    public String desxifraRotX(String cadena, int desplaçament) {
        // L= lletra C= Caracter
        String nCadena = "";
        boolean esMajuscula = false;
        int posicio = 0;

        for (int l = 0; l < cadena.length(); l++) {
            if (Character.isUpperCase(cadena.charAt(l)) && Character.isLetter(cadena.charAt(l))) {
                esMajuscula = true;
            } else if (Character.isLowerCase(cadena.charAt(l)) && Character.isLetter(cadena.charAt(l))) {
                esMajuscula = false;
            }
            if (Character.isLetter(cadena.charAt(l))) {
                if (esMajuscula) {
                    for (int c = 0; c < LMAY.length; c++) {
                        posicio = c - desplaçament;
                        if (cadena.charAt(l) == LMAY[c]) {
                            if (posicio < 0) {
                                posicio = Math.abs(posicio) % LMAY.length;
                                nCadena = nCadena + LMAY[LMAY.length - posicio];
                            } else {
                                nCadena = nCadena + LMAY[posicio];
                            }
                            break;
                        }
                    }
                } else {
                    for (int c = 0; c < LMIN.length; c++) {
                        posicio = c - desplaçament;
                        if (cadena.charAt(l) == LMIN[c]) {
                            if (posicio < 0) {
                                posicio = Math.abs(posicio) % LMIN.length;
                                nCadena = nCadena + LMIN[LMIN.length - posicio];
                            } else {
                                nCadena = nCadena + LMIN[posicio];
                            }
                            break;
                        }
                    }
                }
            } else {
                nCadena = nCadena + cadena.charAt(l);
            }
        }
        return nCadena;
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 0; i < LMAY.length; i++) {
            System.out.println(desxifraRotX(cadenaXifrada, i) + " <-- " + i);
        }
        System.out.println();
    }
}
