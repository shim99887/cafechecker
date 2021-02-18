package com.cafe.crawl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.crawl.model.CafeDto;
import com.cafe.crawl.model.Pagination;
import com.cafe.crawl.model.mapper.CrawlingMapper;

@Service
public class CrawlingServiceImpl implements CrawlingService{
	
	@Autowired
	private CrawlingMapper dao;
	
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return dao.getLength();
	}
	
	@Override
	public void writeList(List<CafeDto> list) {
//		dao.truncTable();
		for(CafeDto dto : list) {
			dao.writeList(dto);
		}
	}
	
	@Override
	public List<CafeDto> getAllList(int page) {
		page = (page-1)*10;
		return dao.getAllList(page);
	}
	
	@Override
	public List<CafeDto> getTitleError() {
		// TODO Auto-generated method stub
		return dao.getTitleError();
	}
	
	@Override
	public List<CafeDto> getFileError() {
		// TODO Auto-generated method stub
		return dao.getFileError();
	}
	@Override
	public List<CafeDto> getImageError() {
		// TODO Auto-generated method stub
		return dao.getImageError();
	}
}
