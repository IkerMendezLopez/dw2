package beans;

public class LineaPedido {
    private int id;
    private int cantidad;
    private Item itempedido;
    private Pedido pedido;

    public LineaPedido() {
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Item getItempedido() {
        return itempedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setItempedido(Item itempedido) {
        this.itempedido = itempedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


}
