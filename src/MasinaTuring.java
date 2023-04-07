import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MasinaTuring {
    private final Graf _graf;
    MasinaTuring(String path)
    {
        this._graf = new Graf(path);
    }

    public void analiza(String input)
    {
        if(this._graf.analiza(input)) System.out.println("Cuvantul a fost analizat cu succes!");
        else System.out.println("Cuvantul nu a fost acceptat...");
    }

    public void afisareContinut()
    {
        System.out.println("\n\n\nGraful masinii turing: ");
        this._graf.afisareContinut();
    }

}