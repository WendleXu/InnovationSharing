package com.yonyou.base.form;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseForm {

	/** 验证数据重复token */
	private String doubleToken;
	
	private String token;

	@SuppressWarnings("unchecked")
	private List formBeanlist;

	private Map<String, String> options;

	private int pageSize = 10;

	private int currPage = 1;

	private int totalRecord = 0;

	private String tableName;
	
	private List<QueryArg> queryArgs;

	private boolean limit = true;

	private String erroMsg;

	private String infoMsg;

	private File file;

	private String key;

	
}
