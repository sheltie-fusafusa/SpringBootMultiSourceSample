package com.example.demo.repository.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.SecondaryDTO;

@Mapper
public interface SecondaryRepository {

	List<SecondaryDTO> selectAll2();
}
