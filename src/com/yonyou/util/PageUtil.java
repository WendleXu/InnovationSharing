package com.yonyou.util;

public class PageUtil {

	private int currentPage;// 当前页  
    private int pageSize = 10;// 每页显示记录数 常量  
    private int totalRecord;// 总记录数  
    private int totalPage;// 总页数  
    private int firstPage;// 第一页  
    private int lastPage;// 最后一页  
    private int prePage;// 上一页  
    private int nextPage;// 下一页  
    private int position;// 从第几条信息记录 开始查询  
//  private Properties properties;  
  
//  public void initPageSize() {  
//      properties = new Properties();  
//      InputStream loadFile = this.getClass().getResourceAsStream(  
//              "/com/dada/config/conn.properties");  
//      try {  
//          properties.load(loadFile);  
//  
//          // 从配置文件读取 每页显示记录数 常量  
//          pageSize = Integer.parseInt(properties.getProperty("pageSize")  
//                  .trim());  
//          System.out.println("pagesize:" + pageSize);  
//      } catch (IOException e) {  
//          e.printStackTrace();  
//      }  
//  }  
  
    public PageUtil(int totalRecord) {  
//      initPageSize();// 一定放在此构造方法的第一行  
        this.totalRecord = totalRecord;  
    }  
  
    public PageUtil(int currentPage, int totalRecord) {  
//      initPageSize();// 一定放在此构造方法的第一行  
        this.totalRecord = totalRecord;  
        this.currentPage = currentPage;  
        // initPageSize();  
    }  
  
    public int getCurrentPage() {  
        if (this.currentPage < 1)  
            this.currentPage = 1;  
        if (this.currentPage > this.getTotalPage())  
            this.currentPage = this.getTotalPage();  
  
        return currentPage;  
    }  
  
    public void setCurrentPage(int currentPage) {  
        this.currentPage = currentPage;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getTotalRecord() {  
        return totalRecord;  
    }  
  
    public void setTotalRecord(int totalRecord) {  
        this.totalRecord = totalRecord;  
    }  
  
    public int getTotalPage() {  
        if (this.getTotalRecord() % pageSize == 0)  
            return this.getTotalRecord() / pageSize;  
        return this.getTotalRecord() / pageSize + 1;  
    }  
  
    public void setTotalPage(int totalPage) {  
        this.totalPage = totalPage;  
    }  
  
    public int getFirstPage() {  
        return 1;  
    }  
  
    public void setFirstPage(int firstPage) {  
        this.firstPage = firstPage;  
    }  
  
    public int getLastPage() {  
        return this.getTotalPage();  
    }  
  
    public void setLastPage(int lastPage) {  
        this.lastPage = lastPage;  
    }  
  
    public int getPrePage() {  
        if (this.getCurrentPage() - 1 <= 0)  
            return 1;  
        return this.getCurrentPage() - 1;  
    }  
  
    public void setPrePage(int prePage) {  
        this.prePage = prePage;  
    }  
  
    public int getNextPage() {  
        if (this.getCurrentPage() + 1 >= this.getTotalPage())  
            return this.getTotalPage();  
        return this.getCurrentPage() + 1;  
    }  
  
    public void setNextPage(int nextPage) {  
        this.nextPage = nextPage;  
    }  
  
    public int getPosition() {  
        return (this.getCurrentPage() - 1) * pageSize + 1;  
    }  
  
    public void setPosition(int position) {  
        this.position = position;  
    }  
	
}
