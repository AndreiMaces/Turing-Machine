public class Banda {
    private int _pozitieCursor = 0;
    private String _continut;

    private static int nrBenzi = 0;

    private int _nrBandaCurenta = 0;

    Banda(String continut) {
        this._continut = continut;
        this._nrBandaCurenta = nrBenzi;
        nrBenzi++;
    }

    public void deplasare(char curr, char next , char pos) {
        try {
            if(curr == this._continut.charAt(this._pozitieCursor))
            {
                this._continut = this._continut.substring(0 , this._pozitieCursor) + next + this._continut.substring(this._pozitieCursor + 1);
                this.deplasareCursor(pos);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sePoateDeplasa(String tranzitie)
    {
        return tranzitie.charAt(0) == this._continut.charAt(this._pozitieCursor);
    }

    public void deplasare(String tranzitie)
    {
        try {
            this.deplasare(tranzitie.charAt(0), tranzitie.charAt(1) ,tranzitie.charAt(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deplasareCursor(char pos)
    {
        trimBlankSpaces();
        try {
            if(pos == 'L')
                this._pozitieCursor--;
            else if(pos == 'R')
            {
                this._pozitieCursor++;
            }
            else if(pos != 'S') throw new Exception("Sensul de deplasare " + pos + " nu exista.");
            if(this._pozitieCursor < 0) throw new Exception("A sarit banda cu numarul " + this._nrBandaCurenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void trimBlankSpaces()
    {
//        boolean p = true;
//        for(int i = 0 ; i < this._continut.length(); i++)
//            if (this._continut.charAt(i) != 'B') {
//                p = false;
//                break;
//            }
//        int pozFin = this._continut.length() - 1;
//        if(p) {
//            for(int i = this._continut.length() - 1; i >= 0 ; i--)
//                if(this._continut.charAt(i) == 'B' && this._pozitieCursor < pozFin) {
//                    pozFin--;
//                }
//                else {
//                    break;
//                }
//            this._continut = this._continut.substring(0 , pozFin) + "BB";
//            System.out.println(this._continut);
//        }
        this._continut += "B";
    }

    public void afisareContinut() {
        for(int i = 0 ; i < this._continut.length() ; i++)
            if(i == this._pozitieCursor) System.out.print("->" + this._continut.charAt(i) + "<-");
            else System.out.print(this._continut.charAt(i));
    }

    public void incarca(String input)
    {
        this._continut = input + "BB";
    }
}
