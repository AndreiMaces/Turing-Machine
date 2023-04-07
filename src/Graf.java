import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Graf {
    private final ArrayList<Stare> _stari;
    private final ArrayList<TranzitieGraf> _tranzitii;
    private Stare _stareActuala;
    private final StivaBenzi _stiva;

    Graf(String path)
    {
        int _nrBenzi = this.calculeazaNrBenzi(path);
        this._stiva = new StivaBenzi(_nrBenzi);
        this._stari = new ArrayList<>();
        this._tranzitii = new ArrayList<>();
        this.incarcare(path);
        this._stareActuala = this._stari.get(0);
    }

    private int calculeazaNrBenzi(String path)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            br.readLine();
            br.readLine();
            return br.readLine().split(" ")[1].split(";").length;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    private void incarcare(String path)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] stariFinale = br.readLine().split(" ");
            while(br.ready())
            {
                String[] tr = br.readLine().split(" ");
                this._tranzitii.add(new TranzitieGraf(this.adaugaStarea(tr[0]), tr[1] , this.adaugaStarea(tr[2])));
            }

            for (String s : stariFinale)
                for (var tr : this._tranzitii) {
                    if (tr.getStareIn().nume.equals(s)) tr.getStareIn().esteFinala = true;
                    if (tr.getStareOut().nume.equals(s)) tr.getStareOut().esteFinala = true;
                }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deplasare(Stare q, String tranzitie)
    {
            boolean p = true;
            for(var tr : this._tranzitii)
            {
                if(tr.getTranzitie().equals(tranzitie) && q.nume.equals(this._stareActuala.nume) && tr.getStareIn().nume.equals(this._stareActuala.nume))
                {
                    boolean done = this._stiva.deplasare(tranzitie);
                    if(done) {
                        this._stareActuala = tr.getStareOut();
                        p = false;
                        break;
                    }
                }
            }
            return !p;
    }

    public void afisareContinut()
    {
        System.out.println("Stiva de benzi:");
        this._stiva.afisareContinut();
        System.out.println();
        System.out.println("Starea actuala: " + this._stareActuala.nume);
        System.out.println("Multimea de tranzitii: ");
        for (TranzitieGraf tr : this._tranzitii) {
            System.out.println(tr.getStareIn().nume + " " + tr.getTranzitie() + " " + tr.getStareOut().nume);
        }
        System.out.println("Multimea de stari: ");
        for (Stare stare : this._stari) {
            System.out.print(stare.nume + " ");
        }
        System.out.println("\nMultimea de stari finale: ");
        for (Stare stare : this._stari) {
            if (stare.esteFinala) System.out.print(stare.nume + " ");
        }
    }

    private int existaStarea(String nume)
    {
        for(int i = 0 ; i < this._stari.size(); i++)
            if(this._stari.get(i).nume.equals(nume)) return i;
        return -1;
    }

    private Stare adaugaStarea(String nume) {
        int index = this.existaStarea(nume);
        Stare s;
        if(index == -1)
        {
            s = new Stare(nume, false , false);
            this._stari.add(s);
        } else return this._stari.get(index);
        return s;
    }

    public boolean analiza(String input)
    {
        this._stiva.incarcaBanda(input, 0);
        boolean p = true;
        while(p)
        {
            p = false;
           for(var tr: this._tranzitii) if(this.deplasare(tr.getStareIn(), tr.getTranzitie())) {
               p = true;
            }
           this.afisareContinut();
        }
        return this._stareActuala.esteFinala;
    }
}
