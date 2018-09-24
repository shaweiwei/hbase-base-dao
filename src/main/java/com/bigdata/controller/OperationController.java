package com.bigdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bigdata.bean.HbaseData;
import com.bigdata.bean.RestResult;
import com.bigdata.util.HbaseOpt;

@RestController
@RequestMapping("/v1/bigdata")
public class OperationController {
	
	private Logger logger = LoggerFactory.getLogger(OperationController.class);

	@RequestMapping(value = "/putdata", method = RequestMethod.GET)
	public RestResult putdata(String tableName, int version){
		
		RestResult restResult = null;
		
		String[] columnFamilys = { "info", "work", "money" };
        try {
            HbaseOpt.createTable(tableName, version, columnFamilys);
        } catch (Exception e) {
            logger.error("创建表"+tableName+"出错");
            logger.error(e.getMessage());
            restResult = new RestResult();
            restResult.setSuccess(false);
            restResult.setMsg("创建表"+tableName+"出错");
        }

        String rowKey = "fghij456789";
        if (restResult == null) {
        	try {
    			HbaseOpt.deleteDataByRowKey(tableName, rowKey);
    		} catch (Exception e) {
    			logger.error("删除"+rowKey+"出错");
                logger.error(e.getMessage());
                restResult = new RestResult();
                restResult.setSuccess(false);
                restResult.setMsg("删除"+rowKey+"出错");
    		}
		}
        
        if (restResult == null) {
        	HashMap<String, String> infoMap = new HashMap<>();
        	infoMap.put("name", "lee");
        	infoMap.put("age", "18");
        	infoMap.put("gender", "man");
        	HashMap<String, String> workMap = new HashMap<>();
        	workMap.put("addr", "HangZhou");
        	workMap.put("company", "awifi");
        	HashMap<String, String> moneyMap = new HashMap<>();
        	moneyMap.put("pay", "15k");
        	List<HbaseData> hbaseDataList = new ArrayList<HbaseData>();
        	hbaseDataList.add(new HbaseData("info", infoMap));
        	hbaseDataList.add(new HbaseData("work", workMap));
        	hbaseDataList.add(new HbaseData("money", moneyMap));
        	int q = 0;
        	while (q++ < 5) {
        		try {
        			HbaseOpt.insertData(tableName, rowKey, hbaseDataList);
        		} catch (Exception e) {
        			logger.error("插入"+rowKey+"出错");
        			logger.error(e.getMessage());
        			restResult = new RestResult();
                    restResult.setSuccess(false);
                    restResult.setMsg("插入"+rowKey+"出错");
        		}
        	}
        }
        
        if (restResult == null) {
        	restResult = new RestResult();
        	restResult.setSuccess(true);
        }
        
        return restResult;
	}
	
}
