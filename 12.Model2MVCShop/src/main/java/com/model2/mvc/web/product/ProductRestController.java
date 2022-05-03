package com.model2.mvc.web.product;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/**")
public class ProductRestController {
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	public ProductRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());		
	}
	
	@GetMapping(value="json/getProduct/{prodNo}")
	public Product getProduct(@PathVariable int prodNo) throws Exception {
		System.out.println("/product/json/getProduct : GET");
		
		return productService.getProduct(prodNo);
	}
	
	//@PostMapping("proudct/addProduct")
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public Product addProduct(@RequestBody Product product, @RequestPart(value="file",required = false) MultipartFile file) throws Exception {
		System.out.println("/product/json/addPrudct : POST");
		System.out.println(" :: "+ product);
		
		
		if(file !=null) {
			String projectPath = "C:\\workspace\\07.Model2MVCShop(URI,pattern)\\src\\main\\webapp\\images\\uploadFiles";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid+"_"+file.getOriginalFilename();
			File saveFile = new File(projectPath,fileName);
					
			file.transferTo(saveFile);
			//product.setFileName(fileName);
		}
		
		System.out.println(product);
		productService.addProduct(product);
		
		return product;
	}
	
	
	//@RequestMapping(value="json/listProduct")
	@GetMapping(value="json/listProduct")
	public Map<String,Object> listProduct(Search search) throws Exception {
		System.out.println(search);
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		if(search.getSearchValue().equals("5")) {
			search.setSearchCondition("3");
		}
		Map<String,Object> map = new HashMap<String,Object>();		
		search.setPageSize(pageSize);
		map = productService.getProductList(map);
				
		List<Object> list = (List)map.get("list");
		
		for (Object object : list) {
			System.out.println(object);
		}
		
		return map;
	}
	
}
