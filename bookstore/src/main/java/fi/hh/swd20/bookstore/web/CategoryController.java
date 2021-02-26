package fi.hh.swd20.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.hh.swd20.bookstore.domain.Category;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository cRepository;
	
	@RequestMapping(value="/categorylist", method=RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categories", cRepository.findAll());
		return "categorylist";
	}
	
    @RequestMapping(value="/addcategory")
    public String createCategory(Model model){
    model.addAttribute("category", new Category());
    return "newcategory";
    }
    
    @RequestMapping(value="/savecategory", method=RequestMethod.POST)
    public String save(Category category) {
    	cRepository.save(category);
    	return "redirect:categorylist";
    }
	
}
