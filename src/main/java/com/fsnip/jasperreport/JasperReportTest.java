package com.fsnip.jasperreport;

import java.awt.RenderingHints;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.junit.Test;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import com.fsnip.jfreechart.ChartCreater;
import com.fsnip.model.ChartValue;
import com.fsnip.model.DataSourceBean;
import com.fsnip.model.PayeeEntity;

public class JasperReportTest {
	
	@Test
	public void pdfStreamExporter() throws Exception {
		
		List<String> paragraphs = new ArrayList<String>();
		
		for (int i = 0; i < 5; i++) {
			paragraphs.add("新华社广州6月17日电 2017年金砖国家运动会于6月17日晚在广州开幕。国家主席习近平致贺信，对运动会的召开表示热烈祝贺，向参加运动会的各国嘉宾、运动员、教练员们致以诚挚的欢迎。习近平指出，我们期待着以今年9月举行的金砖国家领导人厦门会议为契机，推动金砖国家人文交流合作取得新成果，为金砖国家合作夯实民意基础。习近平强调，金砖国家体育事业发展各具特色。本届运动会将为提高运动员竞技水平、普及传统体育项目、推动体育事业发展、促进人民友谊发挥积极作用。希望运动员们发扬风格、赛出水平、创造佳绩。");
		}
		
		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 10; i++) {
			PayeeEntity payeeEntity = new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i, i + 1);
			
			List<ChartValue> chartValues = new ArrayList<ChartValue>();
			for (int j = 0; j < 5; j++) {
				ChartValue chartValue = new ChartValue("种类" + i + j, i+j);
				chartValues.add(chartValue);
			}
			payeeEntity.setChartValues(chartValues);
			payees.add(payeeEntity);
		}
		
		List<ChartValue> chartValues = new ArrayList<ChartValue>();
		for (int j = 0; j < 5; j++) {
			ChartValue chartValue = new ChartValue("种类" + j, j);
			chartValues.add(chartValue);
		}
		
	    List<DataSourceBean> dataSourceBeanList = new ArrayList<DataSourceBean>();
		DataSourceBean dataSourceBean = new DataSourceBean();
		dataSourceBean.setUserName("李国友");
		dataSourceBean.setCurrDate(new Date());
		dataSourceBean.setPayees(payees);
		dataSourceBean.setParagraphs(paragraphs);
		dataSourceBean.setChartValues(chartValues);
		
		dataSourceBeanList.add(dataSourceBean);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSourceBeanList);

		String sourceFileName = "F:/ireport-templates/eventwarning/reportdemo/reportDemo.jasper";
		String destFileName = "d:\\easypoi\\jasperReport.pdf";
		FileOutputStream fosRef = new FileOutputStream(new File(destFileName));
		
		InputStream isRef = new FileInputStream(new File(sourceFileName));
		JasperPrint jasperPrint = JasperFillManager.fillReport(isRef, new HashMap(), jrDataSource);
		JRAbstractExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
		
		JasperReportsUtils.render(exporter, jasperPrint, fosRef);
	}
	
	@Test
	public void pdfStreamExporter2() throws Exception {
		
		List<String> paragraphs = new ArrayList<String>();
		
		for (int i = 0; i < 5; i++) {
			paragraphs.add("新华社广州6月17日电 2017年金砖国家运动会于6月17日晚在广州开幕。国家主席习近平致贺信，对运动会的召开表示热烈祝贺，向参加运动会的各国嘉宾、运动员、教练员们致以诚挚的欢迎。习近平指出，我们期待着以今年9月举行的金砖国家领导人厦门会议为契机，推动金砖国家人文交流合作取得新成果，为金砖国家合作夯实民意基础。习近平强调，金砖国家体育事业发展各具特色。本届运动会将为提高运动员竞技水平、普及传统体育项目、推动体育事业发展、促进人民友谊发挥积极作用。希望运动员们发扬风格、赛出水平、创造佳绩。");
		}
		
		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 10; i++) {
			PayeeEntity payeeEntity = new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i, i + 1);
			
			List<ChartValue> chartValues = new ArrayList<ChartValue>();
			for (int j = 0; j < 5; j++) {
				ChartValue chartValue = new ChartValue("种类" + i + j, i+j);
				chartValues.add(chartValue);
			}
			payeeEntity.setChartValues(chartValues);
			payees.add(payeeEntity);
		}
		
	    List<DataSourceBean> dataSourceBeanList = new ArrayList<DataSourceBean>();
		DataSourceBean dataSourceBean = new DataSourceBean();
		dataSourceBean.setUserName("李国友");
		dataSourceBean.setCurrDate(new Date());
		dataSourceBean.setPayees(payees);
		dataSourceBean.setParagraphs(paragraphs);
		
		dataSourceBeanList.add(dataSourceBean);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSourceBeanList);

		String sourceFileName = "F:/ireport-templates/eventwarning/reportdemo/reportDemo.jrxml";
		String subRourceFileName = "F:/ireport-templates/eventwarning/reportdemo/reportDemo_subreport1.jrxml";
		String destRourceFileName = "F:/ireport-templates/eventwarning/reportdemo/main.jasper";
		String destFileName = "d:\\easypoi\\jasperReport.pdf";
		FileOutputStream fosRef = new FileOutputStream(new File(destFileName));
		
		JasperCompileManager.compileReportToFile(sourceFileName, destRourceFileName);
		JasperCompileManager.compileReportToFile(subRourceFileName, destRourceFileName);
		
		InputStream isRef = new FileInputStream(new File(destRourceFileName));
		JasperPrint jasperPrint = JasperFillManager.fillReport(isRef, new HashMap(), jrDataSource);
		JRAbstractExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
		
		JasperReportsUtils.render(exporter, jasperPrint, fosRef);
	}

}
