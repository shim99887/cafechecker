package com.cafe.crawl.service;

import java.util.List;

import com.cafe.crawl.model.CafeDto;
import com.cafe.crawl.model.Pagination;

public interface CrawlingService {
	public void writeList(List<CafeDto> list);
	
	public List<CafeDto> getAllList(int page);
	
	public List<CafeDto> getTitleError();
	public List<CafeDto> getImageError();
	public List<CafeDto> getFileError();
	
	public int getLength();
}
