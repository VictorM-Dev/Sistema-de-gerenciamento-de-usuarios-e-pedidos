package Pedidos;

public class Item {
    private String nomeDoProduto;
    private double precoDoProduto;
    private String codigoDoProduto;

    public Item(String nomeDoProduto, double precoDoProduto, String codigoDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
        setPrecoDoProduto(precoDoProduto);
        this.codigoDoProduto = codigoDoProduto;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public double getPrecoDoProduto() {
        return precoDoProduto;
    }

    public void setPrecoDoProduto(double precoDoProduto) {
        this.precoDoProduto = precoDoProduto < 0 ? 0 : precoDoProduto;
    }

    public String getCodigoDoProduto() {
        return codigoDoProduto;
    }

    private void setCodigoDoProduto(String codigoDoProduto) {
        this.codigoDoProduto = codigoDoProduto;
    }

    @Override
    public String toString(){
        return String.format(
                "Nome do produto: %s\nPreço do Produto: %.2f\nCódigo do produto: %s",
                nomeDoProduto, precoDoProduto, codigoDoProduto
        );
    }
}
