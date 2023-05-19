package com.dev2win.iniciativas.faces;

import java.io.Serializable;

import com.dev2win.iniciativas.data.ideas.State;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.ideas.InitiativeService;


@Component
@ManagedBean(value = "chartBean")
@SessionScope
public class ChartBean implements Serializable {

    @Autowired
    FacesContextWrapper facesContextWrapper;

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;

    @Autowired
    InitiativeService initiativeService;
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void init() {
        createBarModel();
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();

        ChartSeries series = new ChartSeries();
        series.setLabel("Series 1");
        series.set(State.OPEN, initiativeService.countByState("Open"));
        series.set(State.CLOSED, initiativeService.countByState("Closed"));
        series.set(State.APROVED, initiativeService.countByState("Aproved"));
        series.set(State.REVIEW, initiativeService.countByState("Review"));
        
        barModel.addSeries(series);
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void refreshCharts(){
       createBarModel();
    }
}
