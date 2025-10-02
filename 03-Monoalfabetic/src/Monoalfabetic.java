
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    

    static final String ABC = "aàábcçdeèééfghiíjklmnoòópqrstuúüvwxyz";
    static char[] abc = ABC.toCharArray();
    private char [] permutat = permutaAlfabet(abc);


public static char[] permutaAlfabet(char[] caracters) {
    List<Character> llista = new ArrayList<>();

     for (char c : caracters) {
        llista.add(c);
    }

    Collections.shuffle(llista);// mezclado ! 
    char [] charVacioPerumutado = new char[caracters.length];
    for (int i = 0; i< caracters.length; i++){

        charVacioPerumutado[i] = llista.get(i);
    }



    return charVacioPerumutado;
   
}



public static void main(String[] args) {
    // Tu alfabeto original
    System.out.println("Alfabeto original: " + new String(abc));

    // Generamos la permutación
    char[] permutat = permutaAlfabet(abc);

    // Mostramos el alfabeto permutado
    System.out.println("Alfabeto permutado: " + new String(permutat));

  
}



}




/*
 * 
 * 
 * 
 * 
 */