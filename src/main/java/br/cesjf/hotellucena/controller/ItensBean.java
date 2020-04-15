/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.ItensDAO;
import br.cesjf.hotellucena.model.Itens;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "itensBean")
@ViewScoped
public class ItensBean {

    Itens item = new Itens();

    List itens = new ArrayList();

    //construtor
    public ItensBean() {
        itens = new ItensDAO().buscarTodas();
        item = new Itens();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new ItensDAO().persistir(item);
        itens = new ItensDAO().buscarTodas();
        item = new Itens();
    }

    public void exclude(ActionEvent actionEvent) {
        new ItensDAO().remover(item);
        itens = new ItensDAO().buscarTodas();
        item = new Itens();
    }

    //getters and setters
    public Itens getItens() {
        return item;
    }

    public void setItens(Itens item) {
        this.item = item;
    }

    public List getItenss() {
        return itens;
    }

    public void setItenss(List itens) {
        this.itens = itens;
    }
    
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }
}
