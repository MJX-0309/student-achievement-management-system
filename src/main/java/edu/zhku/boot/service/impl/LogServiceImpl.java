package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Log;
import edu.zhku.boot.service.LogService;
import edu.zhku.boot.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
implements LogService{

}




