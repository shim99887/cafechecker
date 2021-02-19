package com.cafe.crawl.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.crawl.model.MemberDto;
import com.cafe.crawl.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper dao;
	
	@Override
	public MemberDto login(MemberDto member) {
		// TODO Auto-generated method stub
		return dao.login(member);
	}

}
