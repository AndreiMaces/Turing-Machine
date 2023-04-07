import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StivaBenzi {
    private final ArrayList<Banda> _benzi;
    private final int _nrBenzi;

    StivaBenzi(int nrBenzi)
    {
        this._nrBenzi = nrBenzi;
        _benzi = new ArrayList<>();
        while(nrBenzi != 0) {
            _benzi.add(new Banda("BB"));
            nrBenzi--;
        }
    }

    public boolean deplasare(String tranzitie) {
        try {
            String[] tranzitii = tranzitie.split(";");
            if(tranzitii.length != this._benzi.size()) throw new Exception("Tranzitia " + tranzitie + " nu este valida pentru " + this._nrBenzi + " benzi.");
            if(this.sePoateDeplasa(tranzitie))
            {
                for (int i = 0; i < tranzitii.length; i++)
                    this._benzi.get(i).deplasare(tranzitii[i]);
                return true;
            }
            else return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean sePoateDeplasa(String tranzitie)
    {
        String[] tranzitii = tranzitie.split(";");
        for (int i = 0; i < tranzitii.length; i++) if(!this._benzi.get(i).sePoateDeplasa(tranzitii[i])) return false;
        return true;
    }

    public void afisareContinut()
    {
        for(int i = 0 ; i < this._benzi.size() ; i++)
        {
            System.out.print("Banda " + i + ": ");
            this._benzi.get(i).afisareContinut();
            System.out.println();
        }
    }

    public void incarcaBanda(String input, int index)
    {
        this._benzi.get(index).incarca(input);
    }
}
