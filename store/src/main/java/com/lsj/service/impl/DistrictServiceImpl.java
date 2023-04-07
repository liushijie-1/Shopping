package com.lsj.service.impl;

import com.lsj.domain.District;
import com.lsj.mapper.DistrictMapper;
import com.lsj.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        /*
        * 在网络传输数据时，为了避免无效  数据传输，可以将无效数据设置为null
        * 可以节省流量，提高效率
        * */
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getByCode(String code) {
        String name = districtMapper.findByCode(code);
        return name;
    }
}
