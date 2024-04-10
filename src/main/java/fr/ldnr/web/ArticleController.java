package fr.ldnr.web;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    //@RequestMapping(value="/index", method=RequestMethod.GET)
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="keyword", defaultValue= "") String kw) {
        Page<Article> articles = articleRepository.findByDescriptionContains(kw, (Pageable) PageRequest.of(page, 5));
        model.addAttribute("keyword", kw);
        model.addAttribute("listArticle", articles.getContent());
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "articles";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        articleRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }
}
