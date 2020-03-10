package com.company.project.service.impl;

import com.company.project.dao.TServerInfoMapper;
import com.company.project.model.TServerInfo;
import com.company.project.service.TServerInfoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/02/28.
 */
@Service
@Transactional
public class TServerInfoServiceImpl extends AbstractService<TServerInfo> implements TServerInfoService {
    @Resource
    private TServerInfoMapper tServerInfoMapper;

}
