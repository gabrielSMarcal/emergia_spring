package br.com.emergia.models.subTotal;

public class SubTotal {
    private String category;
    private double totalCalc;
    private double totalRef;
    private double totalRazao;

    public SubTotal(String category) {
        this.category = category;
        this.totalCalc = 0;
        this.totalRef = 0;
        this.totalRazao = 0;
    }

    public void addValues(double calc, double ref, double razao) {
        this.totalCalc += calc;
        this.totalRef += ref;
        this.totalRazao += razao;
    }

    public String getCategory() {
        return category;
    }

    public double getTotalCalc() {
        return totalCalc;
    }

    public double getTotalRef() {
        return totalRef;
    }

    public double getTotalRazao() {
        return totalRazao;
    }

    @Override
    public String toString() {
        return "Subtotal{" +
                "category='" + category + '\'' +
                ", totalCalc=" + totalCalc +
                ", totalRef=" + totalRef +
                ", totalRazao=" + totalRazao +
                '}';
    }
}