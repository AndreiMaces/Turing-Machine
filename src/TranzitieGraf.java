public class TranzitieGraf {
    private final Stare _stareIn;
    private final Stare _stareOut;
    private final String _tranzitie;

    public TranzitieGraf(Stare si, String tr , Stare sf)
    {
        this._stareIn = si;
        this._stareOut = sf;
        this._tranzitie = tr;
    }

    public String getTranzitie()
    {
        return this._tranzitie;
    }

    public Stare getStareOut()
    {
        return this._stareOut;
    }
    public Stare getStareIn()
    {
        return this._stareIn;
    }
}