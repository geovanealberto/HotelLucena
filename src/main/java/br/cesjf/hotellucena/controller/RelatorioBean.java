package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.util.Relatorio;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RelatorioBean {
    public void gerarQuartoReservado() {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio("RelQuartoReservado");
    }
    public void gerarQuartoOcupado() {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio("RelQuartoOcupado");
    }
    public void gerarQuartoDesocupado() {
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio("RelQuartoDesocupado");
    }
}
