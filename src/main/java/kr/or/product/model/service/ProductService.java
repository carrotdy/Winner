package kr.or.product.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.product.model.dao.ProductDao;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao; 
}
