package com.dev2win.iniciativas.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ManagedBean(name = "chartBean")
@SessionScope
public class ChartBean implements Serializable {

    @Autowired
    FacesContextWrapper facesContextWrapper;

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void init() {
        createBarModel();
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        
        ChartSeries series = new ChartSeries();
        series.setLabel("Series 1");
        series.set("Category 1", 10);
        series.set("Category 2", 20);
        series.set("Category 3", 15);
        
        barModel.addSeries(series);
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public String redirectToNewPage() {
        try {
            facesContextWrapper.getCurrentInstance().getExternalContext().redirect("viewInitiatives.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
