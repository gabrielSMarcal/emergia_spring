package br.com.emergia.models;

import br.com.emergia.api.ApiCotacaoDolar;
import br.com.emergia.database.AtributosFixos;

public class ValorConsumoManutencao extends AtributosFixos {

    private double bens;
    private String uBens = "R$";
    private int anos;
    private String uAnos ="Ano";
    private double resulVCM;
    private String uResulVCM = "unid/ano";
    private double resulRefEmergiaSolarVCM;
    private String uResulRefEmergiaSolarVCM = "seJ/unid";



    public ValorConsumoManutencao (double bens, int anos){
        this.bens = bens;
        this.anos = anos;
    }

    public double getBens() {
        return bens;
    }

    public int getAnos() {
        return anos;
    }

    public double calcBens(){
        try {
            double cotacaoDolar = ApiCotacaoDolar.getCotacaoDolar();
            resulVCM = (bens / anos) / cotacaoDolar;
            return resulVCM;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular Valor de consumo e manutenção: " + e.getMessage());
        }
    }

    public double calRefEmergiaSolarVCM(){
        resulRefEmergiaSolarVCM = calcBens() * getTransformidadeRacao();
        return resulRefEmergiaSolarVCM;
    }
}
