package com.model2.mvc.web.product;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="addProduct", method = RequestMethod.POST)
	public String add(@ModelAttribute("product") Product product,MultipartHttpServletRequest mRequest,MultipartFile file, HttpSession session) throws Exception {
		System.out.println("/addProduct");
		System.out.println(file);
		System.out.println("\n\n"+mRequest+"\n\n");
		String projectPath = "C:\\workspace\\12.Model2MVCShop\\src\\main\\webapp\\images\\uploadFiles";		
		
		List<MultipartFile> fileList = mRequest.getFiles("file");
		
		for(MultipartFile mf : fileList) {
			String originName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			
			System.out.println("원본이름 : "+ originName);
			System.out.println("파일사이즈 : "+fileSize);
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid+"_"+originName;
			
			File saveFile = new File(projectPath,fileName);
			mf.transferTo(saveFile);
		}		
		
		User user = (User)session.getAttribute("user");
		product.setUser(user);
		
		System.out.println(product);
		productService.addProduct(product);
		return "redirect:/product/listProduct?searchValue=0";
	}
	
	@RequestMapping(value="getProduct",method=RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("/getProduct");
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product",product);		
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie =null;
		String history = null;		
		
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				
				cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					history =URLDecoder.decode(cookie.getValue(), "euc_kr")+","+request.getParameter("prodNo");
				}
			}
			if (history != null) {
				String[] h = history.split(",");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < h.length; i++) {
					if (!h[i].equals("null")) {					
						if(!list.contains(h[i])){
							list.add(h[i]);
						}
					}
				}
				history = "";
				for(int i=list.size()-1; i >0; i--){
					history += list.get(i)+",";
				}
				history += list.get(0);
				cookie = new Cookie("history", URLEncoder.encode(history,"EUC_KR"));
				System.out.println(history);
			} else {
				cookie = new Cookie("history", URLEncoder.encode(Integer.toString(product.getProdNo()),"EUC_KR"));
			}
		}
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.GET)
	public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
		System.out.println("/updateProductView");
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product",product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product,MultipartHttpServletRequest mRequest ,Model model,MultipartFile file) throws Exception{
		System.out.println("/updateProduct");
		System.out.println(product);
		
		String projectPath = "C:\\workspace\\09.Model2MVCShop(jQuery)\\src\\main\\webapp\\images\\uploadFiles";
//		if(file !=null) {
//			File oldFile = new File(projectPath+"\\"+product.getFileName());
//			if(oldFile.exists()) {
//				oldFile.delete();
//			}
//		}
		
		List<MultipartFile> fileList = mRequest.getFiles("file");
		
		for(MultipartFile mf : fileList) {
			String originName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			
			System.out.println("원본이름 : "+ originName);
			System.out.println("파일사이즈 : "+fileSize);
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid+"_"+originName;
			
			File saveFile = new File(projectPath,fileName);
			mf.transferTo(saveFile);
			//product.setFileName(fileName);
		}	
		
		
		productService.updateProduct(product);
		
		System.out.println(product.getProdNo());
		
		return "redirect:/product/getProduct?prodNo="+product.getProdNo();
	}
	
	@RequestMapping(value="listProduct")
	public String listProduct(@ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception {
		System.out.println("/listProduct");
		
		System.out.println(search);
		//System.out.println(search);
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search", search);
		// Business logic 수행
		map=productService.getProductList(map);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}

}
