package com.fsnip.easypoi;

import java.awt.RenderingHints;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;
import org.jeecgframework.poi.word.entity.WordImageEntity;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.junit.Test;

import com.fsnip.jfreechart.ChartCreater;
import com.fsnip.model.PayeeEntity;

public class EasyPoiTest {

	@Test
	public void test2() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "李谷一");
		map.put("currDate", new Date());
		
		for (int i = 0; i < 5; i++) {
			map.put("paragraph" + i, "新华社广州6月17日电 2017年金砖国家运动会于6月17日晚在广州开幕。国家主席习近平致贺信，对运动会的召开表示热烈祝贺，向参加运动会的各国嘉宾、运动员、教练员们致以诚挚的欢迎。习近平指出，我们期待着以今年9月举行的金砖国家领导人厦门会议为契机，推动金砖国家人文交流合作取得新成果，为金砖国家合作夯实民意基础。习近平强调，金砖国家体育事业发展各具特色。本届运动会将为提高运动员竞技水平、普及传统体育项目、推动体育事业发展、促进人民友谊发挥积极作用。希望运动员们发扬风格、赛出水平、创造佳绩。");
		}
		
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
	    
	    WordImageEntity image = new WordImageEntity();
		image.setHeight(200);
		image.setWidth(500);
		image.setData(baos.toByteArray());
		image.setType(WordImageEntity.Data);
		map.put("jfreechartImg", image);
		
		for (int i = 0; i < 3; i++) {
			PayeeEntity payeeEntity = new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i);
			
			map.put("payee" + (i + 1), payeeEntity);
		}
//		String path = Class.class.getClass().getResource("/").getPath();
		String path = System.getProperty("user.dir") + "/template/测试用freemarker生成word-easypoi.docx";
		System.out.println("---------------path:" + path);
		try {
			XWPFDocument doc = WordExportUtil.exportWord07(path , map);
			FileOutputStream fos = new FileOutputStream(
					"D:/easypoi/easypoiReport.docx");
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
