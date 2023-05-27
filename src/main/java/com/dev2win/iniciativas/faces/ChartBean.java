package com.dev2win.iniciativas.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScaleTitle;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.bar.BarChartDataSet;
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
    private BarChartModel barModel2;
    
    @PostConstruct
    public void init() {
        createBarModel();
        createBarModel2();
    }
    
    //Crea el modelo con los datos de las iniciativas y los estados en que se encuentran para mostrar esta información por
    //pantalla en forma de histogramas.
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Initiatives vs State");
        // Colors
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgb(255, 99, 132, 0.2)");
        bgColor.add("rgb(255, 159, 64, 0.2)");
        bgColor.add("rgb(255, 205, 86, 0.2)");
        bgColor.add("rgb(75, 192, 192, 0.2)");
        barDataSet.setBackgroundColor(bgColor);
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        // Values
        List<Number> values = new ArrayList<>();
        values.add(initiativeService.countByState("Open"));
        values.add(initiativeService.countByState("Closed"));
        values.add(initiativeService.countByState("Aproved"));
        values.add(initiativeService.countByState("Review"));
        barDataSet.setData(values);
        data.addChartDataSet(barDataSet);
        // Labels
        List<String> labels = new ArrayList<>();
        labels.add("Open");
        labels.add("Closed");
        labels.add("Aproved");
        labels.add("Review");
        data.setLabels(labels);
        // Options
        BarChartOptions options = new BarChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Initiatives Vs State");
        title.setFontSize(18);
        title.setFontStyle("bold");
        options.setTitle(title);
        // Axes
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearYAxes = new CartesianLinearAxes();
        CartesianLinearAxes linearXAxes = new CartesianLinearAxes();
        CartesianScaleTitle scaleTitleY = new CartesianScaleTitle();
        CartesianScaleTitle scaleTitleX = new CartesianScaleTitle();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setStepSize(1);
        scaleTitleY.setDisplay(true);
        scaleTitleY.setText("Number of initiatives");
        scaleTitleY.setFontSize(15);
        scaleTitleX.setDisplay(true);
        scaleTitleX.setText("State");
        scaleTitleX.setFontSize(15);
        linearYAxes.setBeginAtZero(true);
        linearYAxes.setScaleTitle(scaleTitleY);
        linearYAxes.setTicks(ticks);
        linearYAxes.setOffset(true);
        linearXAxes.setScaleTitle(scaleTitleX);
        linearXAxes.setOffset(true);
        cScales.addYAxesData(linearYAxes);
        cScales.addXAxesData(linearXAxes);
        options.setScales(cScales);
        options.setBarPercentage(1.0);
        // Data & Configuration
        barModel.setData(data);
        barModel.setOptions(options);
    }

    //Crea el modelo con los datos de las iniciativas y las áreas a las que pertenecen para mostrar esta información por
    //pantalla en forma de histogramas.
    public void createBarModel2() {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Initiatives vs Area");
        // Colors
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgb(255, 99, 132, 0.2)");
        bgColor.add("rgb(255, 159, 64, 0.2)");
        bgColor.add("rgb(255, 205, 86, 0.2)");
        bgColor.add("rgb(75, 192, 192, 0.2)");
        bgColor.add("rgb(54, 162, 235, 0.2)");
        barDataSet.setBackgroundColor(bgColor);
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        // Values
        List<Number> values = new ArrayList<>();
        values.add(initiativeService.countByArea("Environment"));
        values.add(initiativeService.countByArea("Articial Intelligence"));
        values.add(initiativeService.countByArea("Undertaking"));
        values.add(initiativeService.countByArea("Animal rights"));
        values.add(initiativeService.countByArea("Healthy life"));
        barDataSet.setData(values);
        data.addChartDataSet(barDataSet);
        // Labels
        List<String> labels = new ArrayList<>();
        labels.add("Environment");
        labels.add("Articial Intelligence");
        labels.add("Undertaking");
        labels.add("Animal rights");
        labels.add("Healthy life");
        data.setLabels(labels);
        // Options
        BarChartOptions options = new BarChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Initiatives Vs Area");
        title.setFontSize(18);
        title.setFontStyle("bold");
        options.setTitle(title);
        // Axes
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearYAxes = new CartesianLinearAxes();
        CartesianLinearAxes linearXAxes = new CartesianLinearAxes();
        CartesianScaleTitle scaleTitleY = new CartesianScaleTitle();
        CartesianScaleTitle scaleTitleX = new CartesianScaleTitle();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setStepSize(1);
        scaleTitleY.setDisplay(true);
        scaleTitleY.setText("Number of initiatives");
        scaleTitleY.setFontSize(15);
        scaleTitleX.setDisplay(true);
        scaleTitleX.setText("Area");
        scaleTitleX.setFontSize(15);
        linearYAxes.setBeginAtZero(true);
        linearYAxes.setScaleTitle(scaleTitleY);
        linearYAxes.setTicks(ticks);
        linearYAxes.setOffset(true);
        linearXAxes.setScaleTitle(scaleTitleX);

        linearXAxes.setOffset(true);
        cScales.addYAxesData(linearYAxes);
        cScales.addXAxesData(linearXAxes);
        options.setScales(cScales);
        options.setBarPercentage(1.0);
        // Data & Configuration
        barModel2.setData(data);
        barModel2.setOptions(options);
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }

    public BarChartModel getChartModel2() {
        return barModel2;
    }

    //Vuelve a construir el modelo de las iniciativas por estado y actualiza la vista.
    public BarChartModel refreshCharts(){
        createBarModel();
        return getBarModel();
    }

    //Vuelve a construir el modelo de las iniciativas por área y actualiza la vista.
    public BarChartModel refreshChart2() {
        createBarModel2();
        return getChartModel2();
    }

}
