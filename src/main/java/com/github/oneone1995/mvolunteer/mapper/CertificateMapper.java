package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.Certificate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CertificateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Certificate record);

    Certificate selectByPrimaryKey(Integer id);

    List<Certificate> selectAll();

    int updateByPrimaryKey(Certificate record);

    /**
     * 根据志愿者id更新证书状态
     * @param volunteerId   志愿者id
     * @return  更新记录数
     */
    Integer updateByVolunteerId(
            @Param("volunteerId") Integer volunteerId);
}