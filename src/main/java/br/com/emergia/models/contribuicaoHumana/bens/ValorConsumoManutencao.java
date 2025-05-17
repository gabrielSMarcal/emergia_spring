package br.com.emergia.models.contribuicaoHumana.bens;

import br.com.emergia.database.AtributosFixos;

public class ValorConsumoManutencao extends AtributosFixos {

    private double bens;
    private int anos;

    private double resulVCM;
    private double resulRefEmergiaSolarVCM;
    private double razaoVCM;

    private String unidadeVCM = "$";

    public ValorConsumoManutencao() {}

    public ValorConsumoManutencao (double bens, int anos) {
        this.bens = bens;
        this.anos = anos;
    }

    public double getResulRefEmergiaSolarVCM() {
        return resulRefEmergiaSolarVCM;
    }

    public String getUnidadeVCM() {
        return unidadeVCM;
    }

    public double calcBens() {

        double dolarProvisorio = 6.7;

        try {

            double cotacaoDolar = dolarProvisorio;
            resulVCM = (bens / anos) / cotacaoDolar;
            return resulVCM;
        } catch (Exception e) {

            throw new RuntimeException("Erro ao calcular Valor de consumo e manutenção: " + e.getMessage());
        }
    }

    public double calRefEmergiaSolarVCM() {

        resulRefEmergiaSolarVCM = calcBens() * getTransformidadeRacao();
        return resulRefEmergiaSolarVCM;
    }

    public double calcRazaoVCM() {

        double base = calcBens();
        if(base == 0) return 0;

        razaoVCM = calRefEmergiaSolarVCM() / base;

        return razaoVCM;
    }
}
