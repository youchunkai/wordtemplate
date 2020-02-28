package com.fsnip.freemarker;

import java.awt.RenderingHints;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateExceptionHandler;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.junit.Test;

import sun.misc.BASE64Encoder;

import com.fsnip.jfreechart.ChartCreater;
import com.fsnip.model.PayeeEntity;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTest {

	static String templatePath = "d:/test";
	
	@Test
	public void test1() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 10; i++) {
			payees.add(new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i));
		}
		map.put("payees", payees);
		
		try {
			//通过Freemaker的Configuration读取相应的ftl
	        Configuration cfg = new Configuration();
	        //设定去哪里读取相应的ftl模板文件
	        cfg.setClassForTemplateLoading(this.getClass(),"/ftl");
	        //在模板文件目录中找到名称为name的文件
	        Template temp = cfg.getTemplate("测试用freemarker生成excel.xml");
	        
	        FileWriter out = new FileWriter(new File("d:\\easypoi\\freemarkerexel.xls"));
		    temp.process(map, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "李谷一");
		map.put("currDate", new Date());
		
		List<String> paragraphs = new ArrayList<String>();
		
		for (int i = 0; i < 5; i++) {
			paragraphs.add("新华社广州6月17日电 2017年金砖国家运动会于6月17日晚在广州开幕。国家主席习近平致贺信，对运动会的召开表示热烈祝贺，向参加运动会的各国嘉宾、运动员、教练员们致以诚挚的欢迎。习近平指出，我们期待着以今年9月举行的金砖国家领导人厦门会议为契机，推动金砖国家人文交流合作取得新成果，为金砖国家合作夯实民意基础。习近平强调，金砖国家体育事业发展各具特色。本届运动会将为提高运动员竞技水平、普及传统体育项目、推动体育事业发展、促进人民友谊发挥积极作用。希望运动员们发扬风格、赛出水平、创造佳绩。");
		}
		
		map.put("paragraphs", paragraphs);
		
		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 10; i++) {
			payees.add(new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i));
		}
		map.put("payees", payees);
		
		JFreeChart Chart = ChartCreater.createCategoryChart();
		
		Chart.getRenderingHints().put (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		
		Chart.getPlot().setBackgroundAlpha(1.0f);
		Chart.getPlot().setNoDataMessage("当前没有有效的数据");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ChartUtilities.writeChartAsJPEG(baos, Chart, 700, 500);
	    
	    BASE64Encoder encoder = new BASE64Encoder();
	    String encode = encoder.encode(baos.toByteArray());
		map.put("jfreechartImg", encode);
		
		try {
			//通过Freemaker的Configuration读取相应的ftl
	        Configuration cfg = getConfiguration();

	        //在模板文件目录中找到名称为name的文件
	        Template temp = cfg.getTemplate("123.xml");
	        
	        FileWriter out = new FileWriter(new File("d:\\easypoi\\freemarker.doc"));
		    temp.process(map, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 10; i++) {
			payees.add(new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i));
		}
		map.put("payees", payees);
		map.put("size", payees.size() + 2);
		
		try {
			//通过Freemaker的Configuration读取相应的ftl
	        Configuration cfg = new Configuration();
	        //设定去哪里读取相应的ftl模板文件
	        cfg.setClassForTemplateLoading(this.getClass(),"/ftl");
	        //在模板文件目录中找到名称为name的文件
	        Template temp = cfg.getTemplate("测试用freemarker生成word-easypoi4.xml");
	        
	        FileWriter out = new FileWriter(new File("d:\\easypoi\\easypoi4.xls"));
		    temp.process(map, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**初始化FreeMarker环境变量*/
	public static Configuration getConfiguration() {

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
		try {
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
		} catch (IOException e) {
			System.out.println("模板文件夹 未找到");
			e.printStackTrace();
		}
		return cfg;
	}


	@Test
	public void test4 () throws IOException {
        JFreeChart Chart = ChartCreater.createCategoryChart();

        Chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        Chart.getPlot().setBackgroundAlpha(1.0f);
        Chart.getPlot().setNoDataMessage("当前没有有效的数据");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtilities.writeChartAsJPEG(baos, Chart, 700, 500);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("d:/test/1.jpeg");
            fos.write(baos.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
            baos.close();
        }

    }

}
