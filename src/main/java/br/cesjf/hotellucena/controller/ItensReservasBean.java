/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.ItensReservasDAO;
import br.cesjf.hotellucena.model.ItensReservas;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import javax.faces.application.FacesMessage;
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
@ManagedBean(name = "itensreservasBean")
@ViewScoped
public class ItensReservasBean {

    ItensReservas itemreserva = new ItensReservas();

    List itensreservas = new ArrayList();

    //construtor
    public ItensReservasBean() {
        itensreservas = new ItensReservasDAO().buscarTodas();
        itemreserva = new ItensReservas();
    }

    //Métodos dos botões 
   public void record(ActionEvent actionEvent) {
        new ItensReservasDAO().persistir(itemreserva);
        itensreservas = new ItensReservasDAO().buscarTodas();
        itemreserva = new ItensReservas();
    }

    public void exclude(ActionEvent actionEvent) {
        new ItensReservasDAO().remover(itemreserva);
        itensreservas = new ItensReservasDAO().buscarTodas();
        itemreserva = new ItensReservas();

    }
    
    //getters and setters
    public ItensReservas getReservas() {
        return itemreserva;
    }

    public void setItensReservas(ItensReservas itemreserva) {
        this.itemreserva = itemreserva;
    }

    public List getItensReservass() {
        return itensreservas;
    }

    public void setItensReservass(List itensreservas) {
        this.itensreservas = itensreservas;
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
