package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.Certificate;
import java.util.List;

public interface CertificateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Certificate record);

    Certificate selectByPrimaryKey(Integer id);

    List<Certificate> selectAll();

    int updateByPrimaryKey(Certificate record);
}