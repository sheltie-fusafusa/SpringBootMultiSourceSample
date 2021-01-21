package com.example.demo.repository.primary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.PrimaryDTO;

@Mapper
public interface PrimaryRepository {

	List<PrimaryDTO> selectAll();
}
