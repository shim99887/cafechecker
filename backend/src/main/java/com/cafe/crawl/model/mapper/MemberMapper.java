package com.cafe.crawl.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe.crawl.model.MemberDto;

@Mapper
public interface MemberMapper {
	public MemberDto login(MemberDto member);
}
