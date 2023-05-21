package com.dev2win.iniciativas.faces;

import java.io.Serializable;

import com.dev2win.iniciativas.data.ideas.State;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.dev2win.iniciativas.data.ideas.Area;
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
    private BarChartModel barModel2;
    
    @PostConstruct
    public void init() {
        createBarModel();
        createBarModel2();
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

    public void createBarModel2() {
        barModel2 = new BarChartModel();

        ChartSeries series2 = new ChartSeries();
        series2.setLabel("Series2");
        series2.set(Area.ENVIRONMENT.getValue(), initiativeService.countByArea("Environment"));
        series2.set(Area.IA.getValue(), initiativeService.countByArea("Articial Intelligence"));
        series2.set(Area.UNDERTAKING.getValue(), initiativeService.countByArea("Undertaking"));
        series2.set(Area.ANIMALS.getValue(), initiativeService.countByArea("Animal rights"));
        series2.set(Area.HEALTHY.getValue(), initiativeService.countByArea("Healthy life"));
        barModel2.addSeries(series2);
        barModel2.setTitle("Number of initiatives by area");
        barModel2.setAnimate(true);
        Axis xAxis = barModel2.getAxis(AxisType.X);
        Axis yAxis = barModel2.getAxis(AxisType.Y);
        xAxis.setLabel("Initiative area");
        yAxis.setLabel("Number of initiatives");
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public BarChartModel getChartModel2() {
        return barModel2;
    }

    public BarChartModel refreshCharts(){
        createBarModel();
        return getBarModel();
    }

    public BarChartModel refreshChart2() {
        createBarModel2();
        return getChartModel2();
    }

}
