package kr.or.product.model.vo;

import lombok.Data;

@Data
public class Product {
	private int productNo;
	private String productWriter;
	private String productContent;
	private int price;
	private int readCount;
	private String regDate;
	private String fileName;
	private String filePath;
}
