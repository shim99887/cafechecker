package com.cafe.crawl.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe.crawl.model.CafeDto;

@Mapper
public interface CrawlingMapper {
	
	public void writeList(CafeDto dto);
	
	public List<CafeDto> getAllList();
	
	public void truncTable();
	
	public List<CafeDto> getTitleError();
	public List<CafeDto> getImageError();
	public List<CafeDto> getFileError();
	
	public int getLength();
}
