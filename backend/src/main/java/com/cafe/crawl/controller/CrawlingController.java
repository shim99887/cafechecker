package com.cafe.crawl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.crawl.model.CafeDto;
import com.cafe.crawl.model.Pagination;
import com.cafe.crawl.service.CrawlingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/crawl")
public class CrawlingController {
//as
	@Autowired
	private CrawlingService service;
	
	public List<CafeDto> crawling() throws Exception {
		List<CafeDto> list = new ArrayList<CafeDto>();

		int pageNo = 1;

		while (true) {

			String url = "https://cafe.naver.com/ArticleList.nhn?search.clubid=15633871&search.menuid=294&search.boardtype=L&search.totalCount=151&search.page="
					+ pageNo;
			int cnt = 0;
			Document doc = new Document(url);
			
			try {
				doc = Jsoup.connect(url).timeout(30000).get();
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}

			
			Element sel = doc.select("html body div").get(0);

			sel = sel.select("div#main-area div.article-board").get(1);
			
			Elements sels = sel.select("table tr td:not(.p-nick)"); 

			Elements articles = sels.select("td.td_article div.inner_list");
//			System.out.println(sels);
			for(int i=0; i < articles.size(); i++) {
				if(sels.select("td.td_date").get(i).text().length() == 5) {
					CafeDto cafe = new CafeDto();
					cafe.setNo(Integer.parseInt(sels.select("div.inner_number").get(i).text()));
					cafe.setTitle(articles.get(i).select("a.article").text());
					cafe.setWriter(sels.select("a.m-tcol-c").get(i).text());
					cafe.setDate(sels.select("td.td_date").get(i).text());
					cafe.setImg(articles.get(i).select("span.list-i-img").hasText());
					cafe.setFile(articles.get(i).select("span.list-i-upload").hasText());
					list.add(cafe);
					cnt++;
				}else {
					if(sels.select("td.td_date").get(i).text().charAt(6) == '2') {
						CafeDto cafe = new CafeDto();
						cafe.setNo(Integer.parseInt(sels.select("div.inner_number").get(i).text()));
						cafe.setTitle(articles.get(i).select("a.article").text());
						cafe.setWriter(sels.select("a.m-tcol-c").get(i).text());
						cafe.setDate(sels.select("td.td_date").get(i).text());
						cafe.setImg(articles.get(i).select("span.list-i-img").hasText());
						cafe.setFile(articles.get(i).select("span.list-i-upload").hasText());
						list.add(cafe);
						cnt++;
					}
				}
			}
			if(cnt == 0) {
				break;
			}
			
			pageNo++;
		}

		return list;
	}
	
	@GetMapping("/title")
	public ResponseEntity<List<CafeDto>> getTitleError(){
		List<CafeDto> list = service.getTitleError();
		
//		System.out.println(list);
		
		return new ResponseEntity<List<CafeDto>>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/img")
	public ResponseEntity<List<CafeDto>> getImageError(){
		List<CafeDto> list = service.getImageError();
		
		System.out.println(list);
		
		return new ResponseEntity<List<CafeDto>>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/file")
	public ResponseEntity<List<CafeDto>> getFileError(){
		List<CafeDto> list = service.getFileError();
		
		System.out.println(list);
		
		return new ResponseEntity<List<CafeDto>>(list, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Boolean> insertList() throws Exception{
		
		List<CafeDto> list = crawling();
		
		service.writeList(list);
		
		return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<List<CafeDto>> getAllList(@PathVariable int page){
		List<CafeDto> list = service.getAllList(page);
		
		System.out.println(list);
		
		return new ResponseEntity<List<CafeDto>>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list/size")
	public ResponseEntity<Integer> getSize(){
		int size = service.getLength();
		
		System.out.println(size);
		
		return new ResponseEntity<Integer>(size, HttpStatus.OK);
	}
}
